package ems.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ems.common.ConnectionMgr;
import ems.common.JavaMailwithAttachment;
import ems.pojo.EMSQuotationData;
import ems.pojo.EMSQuotationDataPDName;
import ems.pojo.EMSQuotationFile;
import ems.pojo.EMSSendReminderProperties;

public class EMSSetStatORSendReminder {

	public static void main(String[] args) throws ParseException, IOException {

		// Get properties file
		EMSSendReminderProperties emsProp = new EMSSendReminderProperties();
		try {
			emsProp.getProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create connection to Oracle DB
		ConnectionMgr conMgr = new ConnectionMgr();
		Connection connOracle = conMgr.connectToOracle(emsProp.getOracleurl(), emsProp.getOracleid(),
				emsProp.getOraclepw());

		// If connOracle is null or is not connected to Oracle DB end the program
		if (connOracle == null) {
			System.out.println("Connection failed.");
			return;
		}

		System.out.println("Connection: " + connOracle);

		//If connOracle is connected, execute checking
		EMSSetStatORSendReminder emsProc = new EMSSetStatORSendReminder();
		emsProc.checkEMSStatus(connOracle);

	}

	public void checkEMSStatus(Connection connOracle) throws ParseException, IOException {

		/* Variables declaration */
		String status = " ";
		boolean withoutPDReply = false;
		EMSQuotationFileMgr emsFileMgr = new EMSQuotationFileMgr();
		List<EMSQuotationFile> emsQFileArr = new ArrayList<EMSQuotationFile>();
		EMSQuotationDataMgr emsQDataMgr = new EMSQuotationDataMgr();
		List<EMSQuotationDataPDName> emsQDataPDArr = new ArrayList<EMSQuotationDataPDName>();
		EMSPDInchargeMgr pdMgr = new EMSPDInchargeMgr();

		/* Get all those file with status = *blanks and check against USSO_EMS_QUOTATION_DATA, 
		 * if all records are with PD reply send the list to the registered i4 User id (QFUSERID), 
		 * otherwise, based on the deadline date check if need to send a reminder to PD or not.*/

		emsQFileArr.addAll(emsFileMgr.getQuoteFile(status, connOracle));

		if(emsQFileArr != null){
			for(EMSQuotationFile ems : emsQFileArr){

				/* Variables Declaration */
				int qfYear = ems.getQfYear();
				int qfMonth = ems.getQfMonth();
				String qfCust = ems.getQfCust();
				String qfFileName = ems.getQfFileName();
				int qfDeadline = ems.getQfDeadline();
				String qfUserId = ems.getQfUserId();
				int todaysDate = 0;
				int dayBeforeDeadline = 0;
				String emailAdd = " ";

				/* Check whether all records are with PD reply */
				withoutPDReply = emsQDataMgr.chkWithoutPDReply(qfYear, qfMonth, qfCust, qfFileName,  
						qfDeadline, qfUserId, status, connOracle);

				/* If no PD reply check whether to send a reminder to PD or not based on the deadline date 
				 * otherwise, send the list with PD replies to i4 User id*/
				if(withoutPDReply == true){
		
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
					
					/* Get today's Date */
					todaysDate = Integer.parseInt(format2.format(cal.getTime()));
					System.out.println("Todays Date: " + todaysDate);
					
					/* Get one day before the deadline */
					Date qfDeadlineFormat = format2.parse(String.valueOf(qfDeadline));
					cal.setTime(qfDeadlineFormat);
					cal.add(Calendar.DATE, -1);  
					dayBeforeDeadline = Integer.parseInt(format2.format(cal.getTime()));
					System.out.println("One day before the deadline: " + dayBeforeDeadline + " Deadline: " + qfDeadlineFormat);
					
					/* One day before the deadline send an email to PD for a reminder if there�s no reply received from them */
					if(todaysDate == dayBeforeDeadline){
						
						emsQDataPDArr.addAll(emsQDataMgr.getQuoteDataPDName(qfYear, qfMonth, qfCust, qfFileName, 
								qfDeadline, qfUserId, status, connOracle));
						
						if(emsQDataPDArr != null){
							for(EMSQuotationDataPDName emsPd : emsQDataPDArr){
								
								if(emailAdd.equals(" ")){
									emailAdd = pdMgr.getPDEmailAdd(emsPd.getQdPdName(), connOracle);
								}else{
									emailAdd = emailAdd + ',' + pdMgr.getPDEmailAdd(emsPd.getQdPdName(), connOracle);
								}
							}
							
							/* If the emailAdd variable is not empty or *blanks, send the email reminder to PD */
							if(!emailAdd.equals(" ")){
								
								/* Form the subject. Example: EMS Nego File-Jabil-EMIBLO-2017/1-Deadline:2017/01/31 - PD Replies */
								SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
								SimpleDateFormat format3 = new SimpleDateFormat("MM");
								Date qfDeadlineFormat2 = format2.parse(String.valueOf(qfDeadline));
								String qfDeadlineFormatted = format1.format(qfDeadlineFormat2);
								String qfMonthFormatted = format3.format(qfMonth);
														
								String subject = "<REMINDER> EMS Nego File-" + qfCust + "-" + "CATEGORY-" + qfYear + "/" + qfMonthFormatted + 
										         "-Deadline:" + qfDeadlineFormatted;
								System.out.println("Subject: " + subject);
								
								/* Send the PD Replies to registered i4 User id */		
								boolean isSent = sendExcelFile("", subject, emailAdd);
								
								if(isSent == true){
									System.out.println("Reminder was successfully sent using subject as: " + subject);
								}else{
									System.out.println("Reminder was NOT send successfully using subject as: " + subject);
								}
								
							}
						}				
						
					}
					
				}else{

					/* Update the status to C=COMPLETED in USSO_EMS_QUOTATION_FILE */
					boolean isUpdated = emsFileMgr.updateQuoteFile(qfMonth, qfYear, qfCust, qfFileName, qfDeadline, qfUserId, 
							"C", connOracle);

					/* If the status is updated to C, create the excel file and send to registered i4 User id */
					if(isUpdated == true){

						boolean isCreated = crtPDReplyExcelFile(qfYear, qfMonth, qfCust, qfFileName,  
								qfDeadline, qfUserId, connOracle);

						if(isCreated == true){
							System.out.println("The file " + qfFileName + " has been successfully updated as COMPLETED and sent to"
									+ " registered i4 User id: " + qfUserId);
						}else{
							System.out.println("The file " + qfFileName + " cannot be updated as COMPLETED nor sent to"
									+ " registered i4 User id: " + qfUserId + " because of an error. Please contact MES-USSO Team");
						}
					}
				}

			}
		}		
	}

	/* This method will create an excel file of those PD replies and send it to registered i4 user id */
	private boolean crtPDReplyExcelFile(int qfYear, int qfMonth, String qfCust, String qfFileName, int qfDeadline, 
			String qfUserId, Connection connOracle) throws IOException, ParseException{

		boolean isCreated = false;
		int counter = 1;
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("EMS Nego File"); 

		EMSQuotationDataMgr emsQDataMgr = new EMSQuotationDataMgr();
		List<EMSQuotationData> emsQDataArr = new ArrayList<EMSQuotationData>();
		SpiritsXrefProdMgr spiritsMgr = new SpiritsXrefProdMgr();

		emsQDataArr = emsQDataMgr.getQuoteData(qfYear, qfMonth, qfCust, qfFileName, qfDeadline, qfUserId, connOracle);

		if(emsQDataArr != null){

			/*Create Excel Header */
			XSSFRow rowhead = sheet.createRow((short)0);
			rowhead.createCell(0).setCellValue("Unique No.");
			rowhead.createCell(1).setCellValue("Site");
			rowhead.createCell(2).setCellValue("SPT");
			rowhead.createCell(3).setCellValue("EMS CPN");
			rowhead.createCell(4).setCellValue("OEM CPN");
			rowhead.createCell(5).setCellValue("EMS MPN");
			rowhead.createCell(6).setCellValue("OEM");
			rowhead.createCell(7).setCellValue("Site+EMS CPN+EMS MPN");
			rowhead.createCell(8).setCellValue("EMS CPN+EMS MPN");
			rowhead.createCell(9).setCellValue("PD in Charge");
			rowhead.createCell(10).setCellValue("H30");
			rowhead.createCell(11).setCellValue("H40");
			rowhead.createCell(12).setCellValue("Current Quarter Correct MPN");
			rowhead.createCell(13).setCellValue("Current Quarter Price");
			rowhead.createCell(14).setCellValue("Current Quarter SP Currency");
			if(qfCust.equals("JABIL")){
				rowhead.createCell(15).setCellValue("Current Quarter OEM Price (Jabil-EMS CPN+EMS MPN)");
				rowhead.createCell(16).setCellValue("Current Quarter OEM Price (Y/N) (Jabil)");
			}else{
				rowhead.createCell(15).setCellValue("Current Quarter OEM Price (Non Jabil -OEM PN)");
				rowhead.createCell(16).setCellValue("Current Quarter OEM Price (Y/N) (Non Jabil-OEM PN)");
			}	
			rowhead.createCell(17).setCellValue("Remarks");
			rowhead.createCell(18).setCellValue("Current Quarter Share %");
			rowhead.createCell(19).setCellValue("Worldwide CCF (MPN with +)");
			rowhead.createCell(20).setCellValue("WorldWide CCF Cur");
			rowhead.createCell(21).setCellValue("Worldwide CCF SP");
			rowhead.createCell(22).setCellValue("PM Reply Next Quarter Full MPN include");
			rowhead.createCell(23).setCellValue("PM Reply Next Quarter Packing Code");
			rowhead.createCell(24).setCellValue("PM Reply Next Quarter S/P 1st Bid");
			rowhead.createCell(25).setCellValue("PM Reply Next Quarter S/P Bottom price");
			rowhead.createCell(26).setCellValue("Next OEM Quarter Price (CPN+MPN)");
			rowhead.createCell(27).setCellValue("Next Quarter OEM Price (Y/N)(CPN+MPN)");
			rowhead.createCell(28).setCellValue("PM Reply H30");
			rowhead.createCell(29).setCellValue("PM Reply H40");
			rowhead.createCell(30).setCellValue("PM Reply Promotion category (promoted or legacy)");
			rowhead.createCell(31).setCellValue("PM Reply Currency");
			rowhead.createCell(32).setCellValue("PM Reply No-Bid Reason");
			rowhead.createCell(33).setCellValue("PM Reply Comments");
			rowhead.createCell(34).setCellValue("PM Reply Next Quarter PD Lead Time (weeks)");
			rowhead.createCell(35).setCellValue("PM Reply Next Quarter MOQ (Pcs)");
			rowhead.createCell(36).setCellValue("PM Reply Packaging QTY (pcs)");
			rowhead.createCell(37).setCellValue("PM Reply Country of Origin");
			rowhead.createCell(38).setCellValue("PM Reply Next Quarter Part type Std/Non-Std/Custom");
			rowhead.createCell(39).setCellValue("PM Reply NCNR (Yes / No)");

			for(EMSQuotationData emsDt : emsQDataArr){

				/* Create Excel Rows*/
				System.out.println(counter);
				XSSFRow row = sheet.createRow((short)counter);
				row.createCell(0).setCellValue(emsDt.getQdUniNum());
				row.createCell(1).setCellValue(emsDt.getQdSite());
				row.createCell(2).setCellValue(emsDt.getQdSpt());
				row.createCell(3).setCellValue(emsDt.getQdEmsCpn());
				row.createCell(4).setCellValue(emsDt.getQdOemCpn());
				row.createCell(5).setCellValue(emsDt.getQdEmsMpn());
				row.createCell(6).setCellValue(emsDt.getQdOem());
				row.createCell(7).setCellValue(emsDt.getQdSiteCpnMpn());
				row.createCell(8).setCellValue(emsDt.getQdCpnMpn());
				row.createCell(9).setCellValue(emsDt.getQdPdName());
				row.createCell(10).setCellValue(emsDt.getQdH30d());
				row.createCell(11).setCellValue(emsDt.getQdH40d());
				row.createCell(12).setCellValue(emsDt.getQdCurMpn());
				row.createCell(13).setCellValue(emsDt.getQdCurPrice());
				row.createCell(14).setCellValue(emsDt.getQdCurCurrency());
				row.createCell(15).setCellValue(emsDt.getQdCurOemPrice());
				row.createCell(16).setCellValue(emsDt.getQdCurOemPrcYn());
				row.createCell(17).setCellValue(emsDt.getQdRemarks());
				row.createCell(18).setCellValue(emsDt.getQdCurShare());
				row.createCell(19).setCellValue(emsDt.getQdWwCcfMpn());
				row.createCell(20).setCellValue(emsDt.getQdWwCcfCur());
				row.createCell(21).setCellValue(emsDt.getQdWwCcfSp());
				row.createCell(22).setCellValue(emsDt.getQdPmrMpn());
				row.createCell(23).setCellValue(emsDt.getQdPmrPack());
				row.createCell(24).setCellValue(emsDt.getQdPmrSp());
				row.createCell(25).setCellValue(emsDt.getQdPmrSpb());
				row.createCell(26).setCellValue(emsDt.getQdNxtPrice());
				row.createCell(27).setCellValue(emsDt.getQdNxtPrcYn());
				row.createCell(28).setCellValue(emsDt.getQdPmrH30d());
				row.createCell(29).setCellValue(emsDt.getQdPmrH40d());
				row.createCell(30).setCellValue(emsDt.getQdPmrProCat());
				row.createCell(31).setCellValue(emsDt.getQdPmrCurrency());
				row.createCell(32).setCellValue(emsDt.getQdPmrNoBid());
				row.createCell(33).setCellValue(emsDt.getQdPmrComment());
				row.createCell(34).setCellValue(emsDt.getQdPmrNxtPdLt());
				row.createCell(35).setCellValue(emsDt.getQdPmrNxtMoq());
				row.createCell(36).setCellValue(emsDt.getQdPmrPackQty());
				row.createCell(37).setCellValue(emsDt.getQdPmrCnor());
				row.createCell(38).setCellValue(emsDt.getQdPmrPartType());
				row.createCell(39).setCellValue(emsDt.getQdPmrNcNr());
				counter = counter + 1;	
			}
		}

		if(counter > 1){

			/* Create excel file based on the original filename */
			String excelFile = "D:/" + qfFileName;
			
			FileOutputStream fileOut = new FileOutputStream(excelFile);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			/* Get the email address from USSO_SPIRITS_XREF_PROD*/
			String emailAdd = spiritsMgr.getEmailbySpiritsId(qfUserId, connOracle);
			
			/* Form the subject. Example: EMS Nego File-Jabil-EMIBLO-2017/1-Deadline:2017/01/31 - PD Replies */
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format3 = new SimpleDateFormat("MM");
			Date qfDeadlineFormat2 = format2.parse(String.valueOf(qfDeadline));
			String qfDeadlineFormatted = format1.format(qfDeadlineFormat2);
			String qfMonthFormatted = format3.format(qfMonth);
			
			String subject = "EMS Nego File-" + qfCust + "-" + "CATEGORY-" + qfYear + "/" + qfMonthFormatted + "-Deadline:" +
							 qfDeadlineFormatted + " - PD Replies";
			System.out.println("Subject: " + subject);
			
			/* Send the PD Replies to registered i4 User id */		
			isCreated = sendExcelFile(excelFile, subject, emailAdd);

		}

		return isCreated;
	}

	/* This method will use JavaMailwithAttachment class to send the file to registered i4 User id */
	private boolean sendExcelFile(String filename, String parmSubject, String emailAdd){

		boolean isSent = false;

		String from="ReportUser@murata.com";
		String to=emailAdd;
		String cc="";
		String bcc="";
		String replyTo="";
		String subject = parmSubject.trim();
		String text = "Email Body testing";
		String filename1 = "";
		String filename2 = "";
		String filename3 = "";
		String filename4 = "";
		String filename5 = "";
		String filename6 = "";
		String filename7 = "";
		String filename8 = "";

		filename1 = filename;
		System.out.println("Filename1  " + filename1);

		try{
			short status = new JavaMailwithAttachment().sendMail(from, to, cc, bcc, replyTo, subject, text, 
					filename1, filename2, filename3, filename4, filename5, 
					filename6, filename7, filename8);
			System.out.println("Email Sending Status:" + status);
			if(status == 0){
				isSent = true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}

		
		return isSent;
	}
}

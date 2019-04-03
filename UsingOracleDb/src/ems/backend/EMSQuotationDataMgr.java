package ems.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ems.pojo.EMSQuotationData;
import ems.pojo.EMSQuotationDataPDName;

public class EMSQuotationDataMgr {

	public List<EMSQuotationData> getQuoteData(int qdYear, int qdMonth, String qdCust, String qdFileName, 
			int qdDeadline, String qdUser, Connection conn) {

		List<EMSQuotationData> emsArr = new ArrayList<EMSQuotationData>();

		try {

			String queryString = "SELECT * FROM USSO_EMS_QUOTATION_DATA WHERE QDYEAR=?"
					+ " AND QDMONTH=? AND QDCUST=? AND QDFILENAME=? AND QDDEADLINE=? AND QDUSERID=?";
			PreparedStatement preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setInt(1, qdYear);
			preparedStatement.setInt(2, qdMonth);
			preparedStatement.setString(3, qdCust);
			preparedStatement.setString(4, qdFileName);
			preparedStatement.setInt(5, qdDeadline);
			preparedStatement.setString(6, qdUser);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				EMSQuotationData ems = new EMSQuotationData();
				ems.setQdYear(rs.getInt(1));
				ems.setQdMonth(rs.getInt(2));
				ems.setQdCust(rs.getString(3));
				ems.setQdUniNum(rs.getInt(4));
				ems.setQdSite(rs.getString(5));
				ems.setQdSpt(rs.getString(6));
				ems.setQdEmsCpn(rs.getString(7));
				ems.setQdOemCpn(rs.getString(8));
				ems.setQdEmsCpn(rs.getString(9));
				ems.setQdOem(rs.getString(10));
				ems.setQdSiteCpnMpn(rs.getString(11));
				ems.setQdCpnMpn(rs.getString(12));
				ems.setQdPdName(rs.getString(13));
				ems.setQdH30d(rs.getString(14));
				ems.setQdH40d(rs.getString(15));
				ems.setQdCurMpn(rs.getString(16));
				ems.setQdCurPrice(rs.getFloat(17));
				ems.setQdCurCurrency(rs.getString(18));
				ems.setQdCurOemPrice(rs.getFloat(19));
				ems.setQdCurOemPrcYn(rs.getString(20));
				ems.setQdRemarks(rs.getString(21));
				ems.setQdCurShare(rs.getString(22));
				ems.setQdWwCcfMpn(rs.getString(23));
				ems.setQdWwCcfCur(rs.getString(24));
				ems.setQdWwCcfSp(rs.getFloat(25));
				ems.setQdPmrMpn(rs.getString(26));
				ems.setQdPmrPack(rs.getString(27));
				ems.setQdPmrSp(rs.getFloat(28));
				ems.setQdPmrSpb(rs.getFloat(29));
				ems.setQdNxtPrice(rs.getFloat(30));
				ems.setQdNxtPrcYn(rs.getString(31));
				ems.setQdPmrH30d(rs.getString(32));
				ems.setQdPmrH40d(rs.getString(33));
				ems.setQdPmrProCat(rs.getString(34));
				ems.setQdPmrCurrency(rs.getString(35));
				ems.setQdPmrNoBid(rs.getString(36));
				ems.setQdPmrComment(rs.getString(37));
				ems.setQdPmrNxtPdLt(rs.getInt(38));
				ems.setQdPmrNxtMoq(rs.getInt(39));
				ems.setQdPmrPackQty(rs.getInt(40));
				ems.setQdPmrCnor(rs.getString(41));
				ems.setQdPmrPartType(rs.getString(42));
				ems.setQdPmrNcNr(rs.getString(43));
				ems.setQdStatus(rs.getString(44));
				ems.setQdCrtUsr(rs.getString(45));
				ems.setQdCrtDate(rs.getInt(46));
				ems.setQdCrtTime(rs.getInt(47));
				ems.setQdUpdUser(rs.getString(48));
				ems.setQdUpdDate(rs.getInt(49));
				ems.setQdUpdTime(rs.getInt(50));
				ems.setQdFileName(rs.getString(51));
				ems.setQdDeadline(rs.getInt(52));
				ems.setQdUserId(rs.getString(53));
				emsArr.add(ems);
			}
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emsArr;

	}

	public List<EMSQuotationDataPDName> getQuoteDataPDName(int qdYear, int qdMonth, String qdCust, String qdFileName, 
			int qdDeadline, String qdUser, String qdStatus, Connection conn) {

		List<EMSQuotationDataPDName> emsArr = new ArrayList<EMSQuotationDataPDName>();

		try {

			String queryString = "SELECT QDYEAR, QDMONTH, QDCUST, QDPDNAME, QDFILENAME, QDDEADLINE, QDUSERID" 
					+ " FROM USSO_EMS_QUOTATION_DATA WHERE QDYEAR=?"
					+ " AND QDMONTH=? AND QDCUST=? AND QDFILENAME=? AND QDDEADLINE=? AND QDUSERID=?"
					+ " AND QDSTATUS=? GROUP BY QDYEAR, QDMONTH, QDCUST, QDFILENAME, QDDEADLINE, QDUSERID, QDPDNAME" 
					+ " ORDER BY QDYEAR, QDMONTH, QDCUST, QDFILENAME, QDDEADLINE, QDUSERID, QDPDNAME";
			
			PreparedStatement preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setInt(1, qdYear);
			preparedStatement.setInt(2, qdMonth);
			preparedStatement.setString(3, qdCust);
			preparedStatement.setString(4, qdFileName);
			preparedStatement.setInt(5, qdDeadline);
			preparedStatement.setString(6, qdUser);
			preparedStatement.setString(7, qdStatus);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				EMSQuotationDataPDName ems = new EMSQuotationDataPDName(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), qdStatus);
				
				emsArr.add(ems);
			}
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emsArr;

	}
	
	public boolean chkWithoutPDReply(int qdYear, int qdMonth, String qdCust, String qdFileName, 
			int qdDeadline, String qdUser, String qdStatus, Connection conn) {
		
		boolean isFound = true;
		EMSQuotationDataPDName ems = new EMSQuotationDataPDName();

		try {

			String queryString = "SELECT QDYEAR, QDMONTH, QDCUST, QDPDNAME, QDFILENAME, QDDEADLINE, QDUSERID" 
					+ " FROM USSO_EMS_QUOTATION_DATA WHERE QDYEAR=?"
					+ " AND QDMONTH=? AND QDCUST=? AND QDFILENAME=? AND QDDEADLINE=? AND QDUSERID=?"
					+ " AND QDSTATUS=? GROUP BY QDYEAR, QDMONTH, QDCUST, QDFILENAME, QDDEADLINE, QDUSERID, QDPDNAME" 
					+ " ORDER BY QDYEAR, QDMONTH, QDCUST, QDFILENAME, QDDEADLINE, QDUSERID, QDPDNAME";
			
			//System.out.println("Query String: " + queryString);
			PreparedStatement preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setInt(1, qdYear);
			preparedStatement.setInt(2, qdMonth);
			preparedStatement.setString(3, qdCust);
			preparedStatement.setString(4, qdFileName);
			preparedStatement.setInt(5, qdDeadline);
			preparedStatement.setString(6, qdUser);
			preparedStatement.setString(7, qdStatus);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				ems = new EMSQuotationDataPDName(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), qdStatus);
				
			}
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(ems.getQdFileName() == null){
			isFound = false;
		}
		
		return isFound;

	}
	
}

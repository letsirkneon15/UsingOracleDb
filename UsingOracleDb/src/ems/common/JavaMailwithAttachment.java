/*******************************************************************************************
JavaMailwithAttachment.java - from java mail tutorial

Date       : 02/26/2002
Programmer : James Zhang
Desc       : Sending email with Attachments.

Updated
Date       : 08/28/2002
Programmer : Mike Eovino
Desc       : Allow up to 8 attachments to be sent

Updated
Date       : 09/12/2002
Programmer : Mike Eovino
Desc       : Fix Lotus Notes R5 Webmail issues with Multipart section

Updated
Date       : 09/12/2002
Programmer : Mike Eovino
Desc       : Strip path information from filename

Makes use of activation.jar and mail.jar

Updated
Date		:	2013.09.05
Programmer	:	Ronald C. Vega
Desc		:	Source clean up and modified to suit AS400 requirements
				Allows for up to 8 attachments
				Body of message can be formed as HTML message
********************************************************************************************/
package ems.common;

import java.util.Properties;
import java.lang.String;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class JavaMailwithAttachment {
public static void main(String[] args) {

// email properties passed as input parameters
/*String from      = args[0];
String to        = args[1];
String cc        = args[2];
String bcc       = args[3];
String replyTo   = args[4];
String subject   = args[5];
String text      = args[6];
String filename1 = args[7];
String filename2 = args[8];
String filename3 = args[9];
String filename4 = args[10];
String filename5 = args[11];
String filename6 = args[12];
String filename7 = args[13];
String filename8 = args[14];*/

// form and send the email
/*try{
	sendMail(from,to,cc,bcc,replyTo,subject,text,filename1,filename2,
			filename3,filename4,filename5,filename6,filename7,filename8);
}catch(Exception e){
	e.printStackTrace();
}*/
}

public short sendMail(String from, String to, String cc, String bcc, String replyTo, 
	String subject, String text, String filename1, String filename2, String filename3,
	String filename4, String filename5, String filename6,String filename7, 
	String filename8) throws Exception {
try{
	
	from = from.trim();
	to = to.trim(); 
	cc = cc.trim();
	bcc = bcc.trim();
        
	
	// Check the attachments. If cannot find the attached file, return error. 201 to 208
	if (!filename1.equals("")) {
		File attachFile1 = new File(filename1);
		System.out.println("attachFile1" + attachFile1);
		if(!attachFile1.exists()){
			return 201;
		}
	}
	if (!filename2.equals("")) {
		File attachFile2 = new File(filename2);
		if(!attachFile2.exists()){
			return 202;
		}
	}
	if (!filename3.equals("")) {
		File attachFile3 = new File(filename3);
		if(!attachFile3.exists()){
			return 203;
		}
	}
	if (!filename4.equals("")) {
		File attachFile4 = new File(filename4);
		if(!attachFile4.exists()){
			return 204;
		}
	}
	if (!filename5.equals("")) {
		File attachFile5 = new File(filename5);
		if(!attachFile5.exists()){
			return 205;
		}
	}
	if (!filename6.equals("")) {
		File attachFile6 = new File(filename6);
		if(!attachFile6.exists()){
			return 206;
		}
	}
	if (!filename7.equals("")) {
		File attachFile7 = new File(filename7);
		if(!attachFile7.exists()){
			return 207;
		}
	}
	if (!filename8.equals("")) {
		File attachFile8 = new File(filename8);
		if(!attachFile8.exists()){
			return 208;
		}
	}
	
	// Get information from the properties file
	InputStream emailPropertyFile = null;
	Properties emailProperty = new Properties();
	emailPropertyFile = this.getClass().getClassLoader().getResourceAsStream("./JavaMail.properties");
	
	emailProperty.load(emailPropertyFile);
	emailPropertyFile.close();
        
	String hostName = emailProperty.getProperty("email.hostname");   
	String hostType = emailProperty.getProperty("email.hosttype");   
	
	// Get system properties
	Properties systemProperty = System.getProperties();

	// Setup mail server  
	systemProperty.put(hostType, hostName);

	// Get session
	Session session = Session.getInstance(systemProperty, null);

	// Define message
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(from));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
	message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
	message.setSubject(subject);
	
	// Set the reply to
	replyTo = replyTo.trim();
	if(replyTo != null && !replyTo.equals("")){
		InternetAddress ReplyTo[] = new InternetAddress[1];                     
		ReplyTo[0] = new InternetAddress(replyTo);
		message.setReplyTo(ReplyTo);
	}
	
	// Create the message part 
	BodyPart messageBodyPart = new MimeBodyPart();
	// Fill the message
	messageBodyPart.setContent(text, "text/html");
	
	// Create a Multipart
	Multipart multipart = new MimeMultipart();

	// Add part one
	multipart.addBodyPart(messageBodyPart);

	//======================
	// Parts two through thirty-one are attachments
	//======================

	if (!filename1.equals("")) {
		// Create second body part (attachment 1)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source1 = new FileDataSource(filename1);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source1));
		// Set the filename
		int filename1LastSlash = filename1.lastIndexOf("/");
		if (filename1LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
			int filename1Length = filename1.length();
			filename1 = filename1.substring(filename1LastSlash + 1, filename1Length);
		}
		messageBodyPart.setFileName(filename1);
		// Add part two to multipart
		multipart.addBodyPart(messageBodyPart);
	}
	if (!filename2.equals("")) {
		// Create third body part (attachment 2)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source2 = new FileDataSource(filename2);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source2));
		// Set the filename
		int filename2LastSlash = filename2.lastIndexOf("/");
		if (filename2LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
            int filename2Length = filename2.length();
            filename2 = filename2.substring(filename2LastSlash + 1, filename2Length);
		}
		messageBodyPart.setFileName(filename2);
		// Add part three to multipart
		multipart.addBodyPart(messageBodyPart);
	}
	if (!filename3.equals("")) {
		// Create fourth body part (attachment 3)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source3 = new FileDataSource(filename3);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source3));
		// Set the filename
		int filename3LastSlash = filename3.lastIndexOf("/");
		if (filename3LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
            int filename3Length = filename3.length();
            filename3 = filename3.substring(filename3LastSlash + 1, filename3Length);
		}
		messageBodyPart.setFileName(filename3);
		// Add part four to multipart
		multipart.addBodyPart(messageBodyPart);
	}
	if (!filename4.equals("")) {
		// Create fifth body part (attachment 4)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source4 = new FileDataSource(filename4);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source4));
		// Set the filename
		int filename4LastSlash = filename4.lastIndexOf("/");
		if (filename4LastSlash == -1) {
			// filename is OK, do nothing
		} else {
            // strip path info from filename
            int filename4Length = filename4.length();
            filename4 = filename4.substring(filename4LastSlash + 1, filename4Length);
		}
		messageBodyPart.setFileName(filename4);
		// Add part five to multipart
		multipart.addBodyPart(messageBodyPart);       
	}
	if (!filename5.equals("")) {
		// Create sixth body part (attachment 5)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source5 = new FileDataSource(filename5);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source5));
		// Set the filename
		int filename5LastSlash = filename5.lastIndexOf("/");
		if (filename5LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
            int filename5Length = filename5.length();
            filename5 = filename5.substring(filename5LastSlash + 1, filename5Length);
		}
		messageBodyPart.setFileName(filename5);
		// Add part 6 to multipart
		multipart.addBodyPart(messageBodyPart);
	}
	if (!filename6.equals("")) {
		// Create seventh body part (attachment 6)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source6 = new FileDataSource(filename6);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source6));
		// Set the filename
		int filename6LastSlash = filename6.lastIndexOf("/");
		if (filename6LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
            int filename6Length = filename6.length();
            filename6 = filename6.substring(filename6LastSlash + 1, filename6Length);
		}
		messageBodyPart.setFileName(filename6);
		// Add part seven to multipart
		multipart.addBodyPart(messageBodyPart);
	}                
	if (!filename7.equals("")) {
		// Create eighth body part (attachment 7)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source7 = new FileDataSource(filename7);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source7));
		// Set the filename
		int filename7LastSlash = filename7.lastIndexOf("/");
		if (filename7LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
            int filename7Length = filename7.length();
            filename7 = filename7.substring(filename7LastSlash + 1, filename7Length);
		}
		messageBodyPart.setFileName(filename7);
		// Add part eight to multipart
		multipart.addBodyPart(messageBodyPart);
	}
	if (!filename8.equals("")) {
		// Create ninth body part (attachment 8)
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("\n");          
		// Get the attachments
		DataSource source8 = new FileDataSource(filename8);
		// Set the data handler to the attachment
		messageBodyPart.setDataHandler(new DataHandler(source8));
		// Set the filename
		int filename8LastSlash = filename8.lastIndexOf("/");
		if (filename8LastSlash == -1) {
			// filename is OK, do nothing
		} else {
			// strip path info from filename
			int filename8Length = filename8.length();
			filename8 = filename8.substring(filename8LastSlash + 1, filename8Length);
		}
		messageBodyPart.setFileName(filename8);
		// Add part nine to multipart
		multipart.addBodyPart(messageBodyPart);       
	}            
	// Put parts in message
	message.setContent(multipart);

	// Send the message
	Transport.send(message);
        
}catch(MessagingException me){                  
	// search for the real exception that caused the problem
	Exception realException = me;
	me.printStackTrace();
	while ( (realException instanceof MessagingException) && 
			((MessagingException)realException).getNextException() != null) {
		realException = ((MessagingException)realException).getNextException();
	}
	// map the exceptions to error codes
	if (realException instanceof javax.mail.MessagingException) 
		return 101;
	else if (realException instanceof java.net.ConnectException) 
		return 102;
	else if (realException instanceof java.net.UnknownHostException) 
		return 103;                             
}catch(Exception e){
	e.printStackTrace();
	// filename is not found
	return 999;
}       
return 0;  // Success
} 
}


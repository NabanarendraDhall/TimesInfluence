package com.TIautomation.CommonUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	static String body = "";

	public static void mail() {
		final String user = "narendra.dhall@techeasesystems.in";// change accordingly
		final String password = "Narendra@123";// change accordingly
		String[] to = { "dhallnabanarendra@gmail.com", "ajit.swami@techeasesystems.in",
				"narendra.dhall@techeasesystems.in" };// change accordingly
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "3535");
		props.put("mail.smtp.starttis.enable", "true");
		props.put("mail.smtp.starttis.equired", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			for (String toEmail : to) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			}
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			Date dateobj = new Date();
			message.setSubject("TES Automation Test Report: " + df.format(dateobj));
			// message.setText("This is simple program of sending email using JavaMail
			// API");
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText("Hi Team,\nPlease find the automation test report on " + df.format(dateobj)
					+ " in the below attachment.\nThe attachment is a HTML file so open it with your browser for best result.");
			// Create a multi part message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			// String []filename =
			// {"D:\\WorkSpace_Naba\\HReck\\test-output\\emailable-report.html",TakeScteenshot.folderName()};
			String filename = "D:\\WorkSpace_Naba\\HReck\\test-output\\emailable-report.html";
			String fileNameToDisplay = "TestReport";
			// for(String files: filename) {
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileNameToDisplay);
			// }
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static String readGmail(final String USERNAME, final String PASSWORD, String sub) {
		Folder folder = null;
		Store store = null;
		System.out.println("***Reading mailbox...");
		try {
			Properties props = new Properties();
			props.put("mail.store.protocol", "imaps");
			Session session = Session.getInstance(props);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", USERNAME, PASSWORD);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
			for (int i = messages.length - 1; i > 0; i--) {
				System.out.println("Reading MESSAGE # " + (i + 1) + "...");
				Message msg = messages[i];
				String strMailSubject = "", strMailBody = "";
				// Getting mail subject
				Object subject = msg.getSubject();
				if (subject.equals(sub)) {
					// Getting mail body
					Object content = msg.getContent();
					// Casting objects of mail subject and body into String
					if (content instanceof String) {
						body = (String) content;
						// System.out.println(body);
						break;
						// strMailSubject = (String) subject;
						// strMailBody = (String) content;
					} else if (content instanceof Multipart) {
						Multipart mp = (Multipart) content;
						// System.out.println(mp);
						break;
						// strMailSubject = (String) subject;
						// strMailBody = (String) content;
					}

					// Printing mail subject and body
					// System.out.println(strMailSubject);
					// System.out.println(strMailBody);
					// For debugging get only 10 emails
					// if(i>=9)
					// break;
				}
			}
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			if (folder != null) {
				try {
					folder.close(true);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (store != null) {
				try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return body;
	}

	public static void main(String[] args) {
		// String mailContent = SendMail.readGmail("upavptechease@gmail.com",
		// "Tes@1234", "Your ‘Shree Jayshar’ New Password");
		// int index = mailContent.indexOf("Password:");
		// String myPass = mailContent.substring(index + 10, index + 18);
		// System.out.println(myPass);

		SendMail.mail();
	}
}

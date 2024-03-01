package Model;

import java.util.Properties;

import javax.mail.Message;

import javax.mail.Session;

import javax.mail.Transport;
//import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
//import Java.io.OutputStream;

public class SendMail {
	// System.out.println("hi");

	public static void sendEmail(Customer c) {
		try {

			String from = "getir.ecza@yandex.com";

			String pass = ".Adlgngr1";

			String[] to = { c.getEmail() };//"adilegngr@yandex.com"
			// String[] to = { "getirreczaa@yandex.com"};

			String host = "smtp.yandex.com";
			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "false");

			props.put("mail.smtps.host", host);

			props.put("mail.smtps.user", from);
			// props.put("mail.transport.protocol", "smtp");

			props.put("mail.smtps.password", pass);

			props.put("mail.smtps.port", "465");

			props.put("mail.smtps.auth", "false");

			/*
			 * props.put("mail.smtp.quitwait", "false");
			 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			 * props.put("mail.debug", "true");
			 */
			Session session = Session.getDefaultInstance(props, null);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {

				toAddress[i] = new InternetAddress(to[i]);

			}
			for (int i = 0; i < toAddress.length; i++) {

				message.addRecipient(Message.RecipientType.TO, toAddress[i]);

			}

			message.setSubject("GETİR");

			message.setText("Sayın " + c.getName() + " siparişiniz yola çıkmıştır");

			Transport transport = session.getTransport("smtps");

			transport.connect(host, from, pass);

			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Mail basariyla gonderildi");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
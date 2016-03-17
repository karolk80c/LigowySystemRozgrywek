package pl.karolkolarczyk.lgs.common;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Mailer {
	private JavaMailSenderImpl sender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.sender = mailSender;
		sender.setDefaultEncoding("UTF-8");
	}

	public void sendMail(String from, String to, String subject, String bodyText) {
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from));
			helper.setTo(new InternetAddress(to));
			helper.setSubject(subject);
			helper.setText("<html><body>" + bodyText + "</body></html>", true);
			sender.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("Nie uda³o³o siê wys³aæ wiadomoœci email");
		}
	}

}

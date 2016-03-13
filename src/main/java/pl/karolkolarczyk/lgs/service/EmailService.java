package pl.karolkolarczyk.lgs.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.common.Mailer;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Service
public class EmailService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	private String siteUrl = "http://149.156.64.22:8081/ping-pong/";

	public void sendEmail(String login, String recipient, String topic, String content) {
		User sender = userRepository.findOne(login);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
		Mailer mailer = (Mailer) context.getBean("Mailer");
		content = content
				.concat("\n\n ----------------------------------------------------- \n\nWiadomosc wys³ana przez ligowy system rozgrywek ping-ponga.")
				.concat("\nJeœli chcesz odpowiedziec na wiadomosc mozesz skontaktowac sie z nadawca: "
						+ "\nImie nazwisko: " + sender.getFirstName() + " " + sender.getLastName() + "\nAdres email: "
						+ sender.getEmailAdress() + "\nNumer kontaktowy: " + sender.getContactNumber() + "\n")
				.concat(siteUrl);
		mailer.sendMail(sender.getEmailAdress(), recipient, topic, content);
		context.close();
	}

	public void sendEmailToAllActive(String topic, String content, Principal principal) {
		List<User> users = userService.findActiveAndDisqualifiedPlayers();
		String senderLogin = principal.getName();
		for (User user : users) {
			sendEmail(senderLogin, user.getEmailAdress(), topic, content);
		}
	}

	public void sendNotification(String login, String recipient, String topic, String content) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
		Mailer mailer = (Mailer) context.getBean("Mailer");
		content = content
				.concat("\n\n ----------------------------------------------------- \n\nWiadomosc wygenerowana automatycznie przez ligowy system rozgrywek ping-ponga.\n")
				.concat(siteUrl);
		mailer.sendMail(login, recipient, topic, content);
		context.close();
	}

	public void sendNotificationToAllPlayers(String topic, String content) {
		List<User> users = userService.findActiveAndDisqualifiedPlayers();
		for (User user : users) {
			sendNotification("leaguegamesystem@gmail.com", user.getEmailAdress(), topic, content);
		}
	}

}

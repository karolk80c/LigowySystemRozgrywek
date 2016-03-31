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

	private String siteUrl = "<a href=http://www.fais.uj.edu.pl/wydzial/sport/ping-pong>http://www.fais.uj.edu.pl/wydzial/sport/ping-pong</a>";

	public void sendEmail(String login, String recipient, String topic, String content) {
		User sender = userRepository.findOne(login);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
		Mailer mailer = (Mailer) context.getBean("Mailer");
		String emailTo = "<a href=mailto:" + sender.getEmailAdress() + ">" + sender.getEmailAdress() + "</a>";
		content = content
				.concat("<br><br> ----------------------------------------------------- <br><br>Wiadomoœæ wys³ana przez ligowy system rozgrywek ping-ponga.")
				.concat("<br>Jeœli chcesz odpowiedzieæ na wiadomoœæ mo¿esz skontaktowaæ siê z nadawc¹: "
						+ "<br>Imiê nazwisko: " + sender.getFirstName() + " " + sender.getLastName()
						+ "<br>Adres email: " + emailTo + "<br>Numer kontaktowy: " + sender.getContactNumber() + "\n")
				.concat("<br>Adres Url: " + siteUrl);
		mailer.sendMail(sender.getEmailAdress(), recipient, topic, content);
		context.close();
	}

	public void sendEmailToAllActive(String topic, String content, Principal principal) {
		List<User> users = userService.findActivePlayers();
		User sender = userRepository.findOne(principal.getName());
		if (users != null) {
			for (User user : users) {
				sendEmail(sender.getLogin(), user.getEmailAdress(), topic, content);
			}
		}
	}

	public void sendNotification(String recipient, String topic, String content) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
		Mailer mailer = (Mailer) context.getBean("Mailer");
		content = content
				.concat("<br><br> ----------------------------------------------------- <br><br>Wiadomoœæ wygenerowana automatycznie przez ligowy system rozgrywek ping-ponga.<br>")
				.concat("<br>Adres Url: " + siteUrl);
		mailer.sendMail("leaguegamesystem@gmail.com", recipient, topic, content);
		context.close();
	}

	public void sendNotificationToAllPlayers(String topic, String content) {
		List<User> users = userService.findActiveAndDisqualifiedPlayers();
		for (User user : users) {
			sendNotification(user.getEmailAdress(), topic, content);
		}
	}

}

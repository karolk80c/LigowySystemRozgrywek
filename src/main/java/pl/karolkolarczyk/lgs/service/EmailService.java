package pl.karolkolarczyk.lgs.service;

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

	public void sendEmail(String login, String recipient, String topic, String content) {
		User sender = userRepository.findOne(login);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-mail.xml");
		Mailer mailer = (Mailer) context.getBean("Mailer");
		content = content
				.concat("\n\n ----------------------------------------------------- \n\n Wiadomosc wys³ana przez ligowy system rozgrywek ping-ponga")
				.concat("\n Jeœli chcesz odpowiedziec na wiadomosc mozesz skontaktowac sie z nadawca: "
						+ "\n Dane kontaktowe: \n Imie nazwisko: " + sender.getFirstName() + " " + sender.getLastName()
						+ "\nAdres email: " + sender.getEmailAdress() + "\n Numer kontaktowy: "
						+ sender.getContactNumber());
		mailer.sendMail(sender.getEmailAdress(), recipient, topic, content);
		context.close();
	}
}

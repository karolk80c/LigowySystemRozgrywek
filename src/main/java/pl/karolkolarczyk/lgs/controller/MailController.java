package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.common.SingleEmail;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.UserRepository;
import pl.karolkolarczyk.lgs.service.EmailService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class MailController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@ModelAttribute("email")
	public SingleEmail construct() {
		return new SingleEmail();
	}

	@RequestMapping("/email")
	public String showEmailForm(Model model, Principal principal) {
		User user = userService.findOne(principal.getName());
		List<User> usersList = new ArrayList<>();
		if ("ROLE_ADMIN".equals(user.getRoles().get(0).getName())) {
			usersList = userService.findActivePlayers();
		} else {
			usersList.add(userRepository.findByFirstName("Pomoc"));
			usersList.add(userRepository.findByFirstName("Administrator"));
		}
		model.addAttribute("usersList", usersList);
		return "email";
	}

	@RequestMapping("/email/{login}")
	public String showEmailForm(Model model, @PathVariable String login) {
		User user = userService.findOne(login);
		model.addAttribute("user", user);
		return "email-to";
	}

	@RequestMapping(value = "/email/{login}", method = RequestMethod.POST)
	public String submitEmailFormToSelectedUser(Model model, @ModelAttribute("email") SingleEmail email,
			Principal principal, @PathVariable String login) throws MessagingException {
		emailService.sendEmail(principal.getName(), email.getRecipient(), email.getTopic(), email.getContent());
		return "redirect:/email/" + login + ".html?success=true";
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String submitEmailForm(Model model, @ModelAttribute("email") SingleEmail email, Principal principal)
			throws MessagingException {
		emailService.sendEmail(principal.getName(), email.getRecipient(), email.getTopic(), email.getContent());
		return "redirect:/email.html?success=true";
	}

	@RequestMapping(value = "/email/users", method = RequestMethod.POST)
	public String submitEmailToAll(Model model, @ModelAttribute("email") SingleEmail email, Principal principal)
			throws MessagingException {
		emailService.sendEmailToAllActive(email.getTopic(), email.getContent(), principal);
		return "redirect:/management.html?success=true";
	}

}

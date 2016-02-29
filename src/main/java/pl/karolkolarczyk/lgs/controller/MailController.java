package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.common.SingleEmail;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.EmailService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class MailController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	@RequestMapping("/email")
	public String showEmailForm(Model model) {
		model.addAttribute("usersList", userService.findAll());
		return "email";
	}

	@RequestMapping("/email/{login}")
	public String showEmailForm(Model model, @PathVariable String login) {
		User user = userService.findOne(login);
		model.addAttribute("user", user);
		model.addAttribute("recipientEmail", user.getEmailAdress());
		return "email-to";
	}

	@RequestMapping(value = "/email/{login}", method = RequestMethod.POST)
	public String submitEmailFormToSelectedUser(Model model, @ModelAttribute("email") SingleEmail email,
			Principal principal, @PathVariable String login) {
		emailService.sendEmail(principal.getName(), email.getRecipient(), email.getTopic(), email.getContent());
		return "redirect:/email/" + login + ".html?success=true";
	}

	@ModelAttribute("email")
	public SingleEmail construct() {
		return new SingleEmail();
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String submitEmailForm(Model model, @ModelAttribute("email") SingleEmail email, Principal principal) {
		emailService.sendEmail(principal.getName(), email.getRecipient(), email.getTopic(), email.getContent());
		return "redirect:/email.html?success=true";
	}

}

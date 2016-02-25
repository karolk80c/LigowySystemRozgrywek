package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.common.SingleEmail;
import pl.karolkolarczyk.lgs.repository.UserRepository;
import pl.karolkolarczyk.lgs.service.EmailService;

@Controller
public class MailController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/email")
	public String showEmailForm(Model model) {
		return "email";
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

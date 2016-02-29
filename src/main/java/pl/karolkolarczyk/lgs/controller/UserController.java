package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String users(Model model, Principal principal) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/users/{login}")
	public String detail(Model model, @PathVariable String login, Principal principal) {
		User user = userService.findOne(login);
		if (principal.getName().equals(user.getLogin())) {
			return "redirect:/account.html";
		} else {
			model.addAttribute("user", user);
			return "user-detail";
		}
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOne(name));
		return "account";
	}

}

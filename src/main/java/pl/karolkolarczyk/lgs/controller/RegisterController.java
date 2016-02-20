package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserService userService;

	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@RequestMapping
	public String showRegister() {
		return "user-register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user) {
		userService.save(user);

		return "redirect:/register.html?success=true";
	}

}

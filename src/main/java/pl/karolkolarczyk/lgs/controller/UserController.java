package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.service.UserService;

@Controller

public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		
		return "users";
	}
	
	@RequestMapping("/users/{login}")
	public String detail(Model model, @PathVariable String login){
		model.addAttribute("user", userService.findOne(login));
		return "user-detail";
	}
}

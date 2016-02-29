package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.UserService;


@Controller
@RequestMapping("/users")
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping("/update/{login}")
	public String updateRole(@PathVariable String login) {
		User user = userService.findOne(login);
		userService.updateToRoleUser(user);
		return "redirect:/users.html";
	}


	@RequestMapping("/remove/{login}")
	public String removeUser(@PathVariable String login) {
		userService.delete(login);
		return "redirect:/users.html";
	}


}

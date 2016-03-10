package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private MatchService matchService;

	@RequestMapping("/users/update/{login}")
	public String updateRole(@PathVariable String login) {
		User user = userService.findOne(login);
		userService.updateToRoleUser(user);
		return "redirect:/users.html";
	}

	@RequestMapping("/users/remove/{login}")
	public String removeUser(@PathVariable String login) {
		userService.delete(login);
		matchService.updateUsersRanking();
		return "redirect:/users.html";
	}

	@RequestMapping("/users/disqualifie/{login}")
	public String diqualifieeUser(@PathVariable String login) {
		userService.disqualifie(login);
		return "redirect:/users.html";
	}

	@RequestMapping("/management")
	public String showManagement() {
		return "management";
	}

}

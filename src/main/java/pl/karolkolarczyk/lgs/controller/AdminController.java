package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;


	@RequestMapping("/users/update/{login}")
	public String updateRole(@PathVariable String login) {
		User user = userService.findOne(login);
		userService.updateToRoleUser(user);
		return "redirect:/users.html";
	}

	@RequestMapping("/users/remove/{login}")
	public String removeUser(@PathVariable String login) {
		userService.delete(login);
		return "redirect:/users.html";
	}

	@RequestMapping("/users/disqualifie/{login}")
	public String diqualifieeUser(@PathVariable String login) {
		User disqualified = userService.findOne(login);
		userService.disqualifie(login);
		int size = userService.findActivePlayers().size();
		disqualified.setLostMatches(size);
		disqualified.setLostSets(size * 4);
		disqualified.setLostSmallPoints(size * 11);
		disqualified.setWonMatches(0);
		disqualified.setWonSets(0);
		disqualified.setWonMatches(0);
		return "redirect:/users.html";
	}

	@RequestMapping("/management")
	public String showManagement() {
		return "management";
	}

}

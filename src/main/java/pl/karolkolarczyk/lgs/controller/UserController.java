package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.UserService;
import pl.karolkolarczyk.lgs.service.UserStatisticService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserStatisticService userStatisticService;
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		
		return "users";
	}
	
	@RequestMapping("/{login}")
	public String detail(Model model, @PathVariable String login){
		User user = userService.findOne(login);
		model.addAttribute("user", user );
		model.addAttribute("userStats",userStatisticService.findOne(user.getUserStatistic().getId()));
		return "user-detail";
	}
	
	
}

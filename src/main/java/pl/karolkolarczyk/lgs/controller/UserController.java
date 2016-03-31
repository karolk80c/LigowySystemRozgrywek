package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping("/resetPassword")
	public String showResetPassword() {
		return "reset-password";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(Model model, HttpServletRequest request) {
		userService.resetPassword(request);
		return "redirect:/login.html?reset=true";
	}

	@RequestMapping("/account/changePassword")
	public String showChangePassword() {
		return "change-password";
	}

	@RequestMapping(value = "/account/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, HttpServletRequest request, Principal principal) {
		userService.changePassword(request, principal);
		return "redirect:/account.html?change=true";
	}

	@RequestMapping("/account/changePersonalData")
	public String showchangePersonalData(Principal principal,Model model) {
		User user = userService.findOne(principal.getName());
		model.addAttribute("user", user);
		return "change-detail";
	}

	@RequestMapping(value = "/account/changePersonalData", method = RequestMethod.POST)
	public String changePersonalData(Model model, HttpServletRequest request, Principal principal) {
		userService.changePersonalData(request, principal);
		return "redirect:/account.html?changeData=true";
	}

}

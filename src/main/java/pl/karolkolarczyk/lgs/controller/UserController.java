package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MatchRepository matchRepository;

	@RequestMapping("/users")
	public String users(Model model, Principal principal) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/users/{login}")
	public String detail(Model model, @PathVariable String login, Principal principal) {
		User user = userService.findOne(login);

		Date currentTime = new Date();
		Pageable incomingMatchesPage = new PageRequest(0, 10, Direction.ASC, "matchDate");
		Page<Match> incomingMatches = matchRepository.findByCompletedAndMatchDateAfterAndFirstNameOrSecondName(false,
				currentTime, incomingMatchesPage, user.getFullName(), user.getFullName());

		Pageable latestMatchesPage = new PageRequest(0, 10, Direction.DESC, "lastModificationDate");
		Page<Match> latestMatches = matchRepository.findByCompletedAndMatchDateNotNullAndFirstNameOrSecondName(true,
				latestMatchesPage, user.getFullName(), user.getFullName());

		model.addAttribute("latestMatches", latestMatches.getContent());
		model.addAttribute("incomingMatches", incomingMatches.getContent());
		User visitor = userService.findOne(principal.getName());

		if (visitor.getLogin().equals(user.getLogin())) {
			return "redirect:/account.html";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("principalName", visitor.getFullName());
			return "user-detail";
		}
	}

	@RequestMapping("/users/find/{fullName}")
	public String findUser(Model model, Principal principal, @PathVariable String fullName) {
		String[] split = fullName.split(" ");
		String login = userService.returnLoginByFullName(split[0], split[1]);
		return "redirect:/users/" + login + ".html";
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		User user = userService.findOne(principal.getName());

		Date currentTime = new Date();
		Pageable incomingMatchesPage = new PageRequest(0, 10, Direction.ASC, "matchDate");
		Page<Match> incomingMatches = matchRepository.findByCompletedAndMatchDateAfterAndFirstNameOrSecondName(false,
				currentTime, incomingMatchesPage, user.getFullName(), user.getFullName());

		Pageable latestMatchesPage = new PageRequest(0, 10, Direction.DESC, "lastModificationDate");
		Page<Match> latestMatches = matchRepository.findByCompletedAndMatchDateNotNullAndFirstNameOrSecondName(true,
				latestMatchesPage, user.getFullName(), user.getFullName());

		model.addAttribute("latestMatches", latestMatches.getContent());
		model.addAttribute("incomingMatches", incomingMatches.getContent());
		model.addAttribute("user", user);
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
	public String showchangePersonalData(Principal principal, Model model) {
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

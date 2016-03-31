package pl.karolkolarczyk.lgs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.DrawService;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.RoundService;
import pl.karolkolarczyk.lgs.service.SeasonService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private SeasonService seasonService;

	@Autowired
	private DrawService drawService;

	@Autowired
	private RoundService roundService;

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
		return "redirect:/users.html";
	}

	@RequestMapping("/users/deactivate/{login}")
	public String deactivateUser(@PathVariable String login) {
		userService.deactivate(login);
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

	@RequestMapping("/admin-matches")
	public String showAllMatches(Model model) {
		List<Match> matches = matchService.findAll();
		model.addAttribute("matches", matches);
		return "admin-matches";
	}

	@RequestMapping("/management/users/clear")
	public String clearUsersData() {
		userService.clearUsersData();
		return "redirect:/management.html";
	}

	@RequestMapping("/management/matches/clear")
	public String clearMatchData() {
		matchService.clearMatchesData();
		return "redirect:/management.html";
	}

	@RequestMapping("/management/seasons/all")
	public String deleteAllSeasons() {
		List<Season> allSeasons = seasonService.findAll();
		for (Season season : allSeasons) {
			seasonService.delete(season);
		}
		return "redirect:/management.html?success=true";
	}

	@RequestMapping("/management/users/all")
	public String deleteAllUsers() {
		List<User> users = userService.findActiveAndDisqualifiedPlayers();
		for (User user : users) {
			userService.delete(user.getLogin());
		}
		return "redirect:/management.html?success=true";
	}

	@RequestMapping("/management/season/draw")
	public String doDraw(Model model) {
		drawService.draw();
		return "redirect:/timetable.html";
	}

	@RequestMapping("/management/remove/rounds")
	public String showRemoveRounds(Model model) {
		model.addAttribute("count", roundService.count());
		return "remove-rounds";
	}

	@RequestMapping(value = "/management/remove/rounds", method = RequestMethod.POST)
	public String RemoveRounds(HttpServletRequest request) {
		roundService.remove(request);
		return "redirect:/timetable.html";
	}

}
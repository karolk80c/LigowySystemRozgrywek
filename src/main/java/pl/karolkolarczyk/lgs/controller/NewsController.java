package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	MatchService matchService;

	@Autowired
	UserService userService;

	@RequestMapping
	public String showNews(Model model, Principal principal) {

		List<Match> incomingMatches = matchService.findIncoming();
		List<Match> latestMatches = matchService.findLatest();
		if (principal != null) {
			User principalUser = userService.findOne(principal.getName());
			model.addAttribute("principalName", principalUser.getFullName());
		}
		model.addAttribute("latestMatches", latestMatches);
		model.addAttribute("incomingMatches", incomingMatches);
		return "news";
	}

	@RequestMapping("/allMatches")
	public String showAllMatches(Model model, Principal principal) {
		List<Match> allMatches = matchService.findAllIncoming();
		if (principal != null) {
			User principalUser = userService.findOne(principal.getName());
			model.addAttribute("principalName", principalUser.getFullName());
		}
		model.addAttribute("allMatches", allMatches);

		return "all-matches";
	}

	@RequestMapping("/allCompletedMatches")
	public String showAllCompletedMatches(Model model, Principal principal) {
		List<Match> allMatches = matchService.findAllCompleted();
		if (principal != null) {
			User principalUser = userService.findOne(principal.getName());
			model.addAttribute("principalName", principalUser.getFullName());
		}
		model.addAttribute("allCompletedMatches", allMatches);

		return "all-completed-matches";
	}

}

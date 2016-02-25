package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.SetService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	SetService setService;

	@Autowired
	MatchService matchService;

	@Autowired
	UserService userService;

	@ModelAttribute("set")
	public Set constructSet() {
		return new Set();
	}

	@RequestMapping("/{id}")
	public String addMatchStatistics(Model model, @PathVariable Integer id) {
		Match match = matchService.findOneWithSets(id);
		model.addAttribute("match", match);
		return "addmatch";
	}

	@RequestMapping("/{id}/approve")
	public String approveMatch(Model model, @PathVariable Integer id, Principal principal) {
		User user = userService.findOne(principal.getName());
		matchService.approve(id, user);
		return "redirect:/matches.html?success=true";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String addSet(Model model, @PathVariable Integer id, @ModelAttribute("set") Set set,
			Principal principal) {
		User user = userService.findOne(principal.getName());
		setService.save(set, id, user);
		return "redirect:/matches/" + id + ".html";
	}

	@RequestMapping
	public String matches(Model model, Principal principal) {
		String name = principal.getName();
		User user = userService.findOne(name);
		model.addAttribute("user", user);
		return "user-matches";
	}

}

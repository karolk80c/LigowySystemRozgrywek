package pl.karolkolarczyk.lgs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.enums.Place;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
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

	@Autowired
	MatchRepository matchRepository;

	@RequestMapping
	public String matches(Model model, Principal principal) {
		String name = principal.getName();
		User user = userService.findOne(name);
		model.addAttribute("user", user);
		return "user-matches";
	}

	@RequestMapping(value = "/{id}/dataAndPlace", method = RequestMethod.POST)
	public String addDataAndPlace(Model model, HttpServletRequest request, @PathVariable Integer id) {
		Match matchFromRepository = matchService.findOne(id);
		matchService.addDateAndPlace(request, matchFromRepository);
		model.addAttribute("match", matchFromRepository);
		return "redirect:/matches.html";

	}

	@RequestMapping(value = "/{id}/dateAndPlace")
	public String addDataAndPlace(Model model, @PathVariable Integer id) {
		model.addAttribute("match", matchService.findOne(id));
		model.addAttribute("places", Place.values());
		return "dateAndPlace";
	}

	@RequestMapping("/{id}/approve")
	public String approveMatch(Model model, @PathVariable Integer id, Principal principal) {
		User user = userService.findOne(principal.getName());
		matchService.approve(id, user);
		return "redirect:/matches.html?success=true";
	}

	@RequestMapping("/{id}/clear")
	public String clearMatchData(@PathVariable Integer id) {
		matchService.clearMatchData(id);
		return "redirect:/admin-matches.html";
	}

	@RequestMapping("/{id}/cancel")
	public String cancelMatch(Model model, @PathVariable Integer id) {
		matchService.cancel(id);
		return "redirect:/matches.html";
	}

	@RequestMapping("/{id}/sendMessage")
	public String sendEmail(Model model, @PathVariable Integer id, Principal principal) {
		String recipientLogin = matchService.getRecipientLogin(principal.getName(), id);
		return "redirect:/email/" + recipientLogin + ".html";
	}

}

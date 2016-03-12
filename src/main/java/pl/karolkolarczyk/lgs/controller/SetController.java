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
import pl.karolkolarczyk.lgs.exception.UnacceptableResultException;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.SetService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/matches")
public class SetController {

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

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String addSet(Model model, @PathVariable Integer id, @ModelAttribute("set") Set set, Principal principal) {
		User user = userService.findOne(principal.getName());
		try {
			setService.save(set, id, user);
		} catch (UnacceptableResultException e) {
			e.printStackTrace();
		}
		return "redirect:/matches/" + id + ".html";
	}

	@RequestMapping("/{id}")
	public String addMatchStatistics(Model model, @PathVariable Integer id) {
		Match match = matchService.findOneWithSets(id);
		model.addAttribute("match", match);
		return "addmatch";
	}

	@RequestMapping("/{id}/deleteSet/{setId}")
	public String addMatchStatistics(Model model, @PathVariable Integer id, @PathVariable Integer setId) {
		setService.delete(setId, id);
		return "redirect:/matches/" + id + ".html";
	}


}

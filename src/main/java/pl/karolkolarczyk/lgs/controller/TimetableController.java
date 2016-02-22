package pl.karolkolarczyk.lgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.RoundService;
import pl.karolkolarczyk.lgs.utils.TimetTableEntity;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@Autowired
	RoundService roundService;

	@Autowired
	MatchService matchService;

	@Autowired
	TimetTableEntity timeTableEntity;

	@ModelAttribute("set")
	public Set constructSet() {
		return new Set();
	}

	@RequestMapping
	public String showTimetable(Model model) {
		List<Match> match = matchService.findAll();
		List<Round> rounds = roundService.findAll();
		model.addAttribute("round", rounds);
		model.addAttribute("match", match);
		return "timetable";
	}


	@RequestMapping("/{id}")
	public String addMatchStatistics(Model model, @PathVariable Integer id) {
		Match match = matchService.findOne(id);
		model.addAttribute("match", match);
		return "addmatch";
	}

}

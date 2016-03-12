package pl.karolkolarczyk.lgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.RoundService;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@Autowired
	RoundService roundService;

	@Autowired
	MatchService matchService;

	@RequestMapping
	public String showTimetable(Model model) {
		List<Round> rounds = roundService.findAllWithMatchesWithSets();
		model.addAttribute("round", rounds);
		return "timetable";
	}

}

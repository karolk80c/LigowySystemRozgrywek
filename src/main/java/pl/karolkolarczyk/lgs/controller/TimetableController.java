package pl.karolkolarczyk.lgs.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.RoundService;
import pl.karolkolarczyk.lgs.service.SeasonService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@Autowired
	RoundService roundService;

	@Autowired
	MatchService matchService;

	@Autowired
	SeasonService seasonService;

	@Autowired
	UserService userService;

	@RequestMapping
	public String showTimetable(Model model) {
		List<Round> rounds = roundService.findAllWithMatchesWithSets();
		int newRoundNumber = 1;
		if (rounds.size() > 0) {
			newRoundNumber = rounds.get(rounds.size() - 1).getNumber() + 1;
		}

		model.addAttribute("newNumber", newRoundNumber);
		model.addAttribute("round", rounds);
		return "timetable";
	}

	@RequestMapping(value = "/{roundId}/addMatch")
	public String showAddMatch(Model model, @PathVariable int roundId) {
		model.addAttribute("usersList", userService.findActivePlayers());
		model.addAttribute("roundId", roundId);
		return "addMatchToRound";
	}

	@RequestMapping(value = "/{roundId}/addMatch", method = RequestMethod.POST)
	public String addMatch(@PathVariable int roundId, HttpServletRequest request) {
		Round round = roundService.findOne(roundId);
		if (round != null) {
			matchService.saveMatchToRound(round, request);
		} else {
			throw new RuntimeException("Nie znaleziono rundy");
		}
		return "redirect:/timetable/" + roundId + "/addMatch.html?success=true";
	}

	@RequestMapping(value = "/addRound/{roundNumber}")
	public String addRound(@PathVariable int roundNumber) {
		Round round = roundService.findOneByNumber(roundNumber - 1);
		Round newRound = new Round();
		Season season = new Season();
		if (round != null) {
			season = round.getSeason();
		} else {
			String seasonNumber = Calendar.getInstance().get(Calendar.YEAR) + "/"
					+ Calendar.getInstance().get(Calendar.MONTH);
			season.setNumber(seasonNumber);
			seasonService.save(season);
			roundNumber = 1;
		}
		newRound.setSeason(season);
		newRound.setNumber(roundNumber);
		roundService.save(newRound);
		return "redirect:/timetable.html";
	}

	@RequestMapping(value = "/removeRound/{id}")
	public String removeRound(@PathVariable int id) {
		roundService.removeRound(id);
		return "redirect:/timetable.html";
	}

}

package pl.karolkolarczyk.lgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.service.MatchService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	MatchService matchService;

	@RequestMapping
	public String showNews(Model model) {
		Page<Match> findIncomingMatches = matchService.findRequestMatches(Direction.ASC, 30, "matchDate");
		List<Match> incomingMatchList = new ArrayList<>();
		List<Match> latestMatchList = new ArrayList<>();
		for (Match match : findIncomingMatches) {
			if (match.getMatchPlace() != null) {
				incomingMatchList.add(match);
			}
		}
		Page<Match> findLatestMatches = matchService.findRequestMatches(Direction.ASC, 30, "lastModificationDate");
		for (Match match : findLatestMatches) {
			if (match.getMatchPlace() != null) {
				latestMatchList.add(match);
			}
		}
		model.addAttribute("latestMatches", latestMatchList);
		model.addAttribute("incomingMatches", latestMatchList);
		return "news";
	}

}

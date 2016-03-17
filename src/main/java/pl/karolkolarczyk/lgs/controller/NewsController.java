package pl.karolkolarczyk.lgs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Date currentTime = new Date();
		Pageable incomingMatchesPage = new PageRequest(0, 10, Direction.ASC, "matchDate");
		Page<Match> incomingMatches = matchRepository.findByCompletedAndMatchDateAfter(false, currentTime,
				incomingMatchesPage);

		Pageable latestMatchesPage = new PageRequest(0, 10, Direction.DESC, "lastModificationDate");
		Page<Match> latestMatches = matchRepository.findByCompletedAndMatchDateNotNull(true, latestMatchesPage);

		model.addAttribute("latestMatches", latestMatches.getContent());
		model.addAttribute("incomingMatches", incomingMatches.getContent());
		return "news";
	}

}

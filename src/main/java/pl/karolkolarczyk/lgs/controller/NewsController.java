package pl.karolkolarczyk.lgs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.repository.MatchRepository;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	MatchRepository matchRepository;

	@RequestMapping
	public String showNews(Model model) {
		model.addAttribute("matches", matchRepository.findByMatchDate(new Date()));
		return "news";
	}

}

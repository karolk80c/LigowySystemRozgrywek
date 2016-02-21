package pl.karolkolarczyk.lgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.entity.Match;
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

	@RequestMapping
	public String showTimetable(Model model) {
		List<Match> match = matchService.findAll();
		// List<TimetTableEntity> timeTableEntities = new ArrayList<>();
		// for (Match m : match) {
		// List<User> users = m.getUsers();
		// if (users != null & users.size() > 0) {
		// timeTableEntity.setFirstName(users.get(0).getFirstName() +
		// users.get(0).getLastName());
		// timeTableEntity.setSecondName(users.get(1).getFirstName() +
		// users.get(1).getLastName());
		// timeTableEntity.setMatchDate(m.getMatchDate());
		// timeTableEntity.setFirstPoint(3);
		// timeTableEntity.setSecondPoint(4);
		// timeTableEntities.add(timeTableEntity);
		// }
		// }

		model.addAttribute("match", match);
		return "timetable";
	}

}

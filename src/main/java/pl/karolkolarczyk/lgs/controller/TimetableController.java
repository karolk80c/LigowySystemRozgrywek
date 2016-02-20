package pl.karolkolarczyk.lgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@RequestMapping
	public String showTimetable() {
		return "timetable";
	}
	
}

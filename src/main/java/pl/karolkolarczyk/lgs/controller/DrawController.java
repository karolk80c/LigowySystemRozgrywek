package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.karolkolarczyk.lgs.service.DrawService;

@Controller
@RequestMapping("/draw")
public class DrawController {

	@Autowired
	DrawService drawService;

	@RequestMapping
	public String showDraw() {
		return "draw";
	}

	@RequestMapping("/result")
	public String doDraw(Model model) {
		drawService.draw();

		return "redirect:/timetable.html";
	}



}

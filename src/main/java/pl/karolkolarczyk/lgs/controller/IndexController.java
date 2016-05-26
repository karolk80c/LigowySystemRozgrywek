package pl.karolkolarczyk.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.karolkolarczyk.lgs.entity.Link;
import pl.karolkolarczyk.lgs.service.LinkService;

@Controller
public class IndexController {

	@Autowired
	LinkService linkService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/rules")
	public String showRules() {
		return "rules";
	}

	@ModelAttribute("link")
	public Link constructLink() {
		return new Link();
	}

	@RequestMapping("/photo")
	public String showPhotos(Model model) {
		model.addAttribute("links", linkService.getAllLinks());
		return "photo";
	}

	@RequestMapping(value = "/photo/addLink", method = RequestMethod.POST)
	public String addSet(@ModelAttribute("link") Link link) {
		linkService.addLink(link);
		return "redirect:/photo.html";
	}

	@RequestMapping(value = "/photo/removeLink/{linkId}")
	public String addSet(@PathVariable Integer linkId) {
		linkService.removeLink(linkId);
		return "redirect:/photo.html";
	}

}

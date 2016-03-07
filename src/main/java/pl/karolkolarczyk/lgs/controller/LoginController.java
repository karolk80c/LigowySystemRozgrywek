package pl.karolkolarczyk.lgs.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, @RequestParam(required = false) boolean error) {
		return "login";
	}

}

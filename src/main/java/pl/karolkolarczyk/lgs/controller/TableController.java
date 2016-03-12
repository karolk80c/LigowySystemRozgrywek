package pl.karolkolarczyk.lgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.service.MatchService;
import pl.karolkolarczyk.lgs.service.UserService;

@Controller
@RequestMapping("/table")
public class TableController {

	@Autowired
	UserService userService;

	@Autowired
	MatchService matchService;

	@RequestMapping
	public String showTable() {
		return "table";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String sortColumn(@RequestParam(defaultValue = "mainPoints") String properties,
			@RequestParam(defaultValue = "desc") String order, Model model) throws Exception {
		List<User> usersList = new ArrayList<>();
		if (properties.equals("mainPoints") && order.equals("desc")) {
			usersList = matchService.compareAndSortUsers();
			model.addAttribute("users", usersList);
		} else {
			Page<User> users = null;
			if (properties == null || properties.isEmpty()) {
				users = userService.findAllWithStatistics("wonMatches", Direction.DESC);
			} else {
				if (order.equals("desc") || order == null || order.isEmpty()) {
					users = userService.findAllWithStatistics(properties, Direction.DESC);
					model.addAttribute("order", "asc");
				} else {
					users = userService.findAllWithStatistics(properties, Direction.ASC);
					model.addAttribute("order", "desc");
				}
			}
			for (User user : users) {
				usersList.add(user);
			}

			model.addAttribute("users", usersList);
		}
		return "table";
	}

}

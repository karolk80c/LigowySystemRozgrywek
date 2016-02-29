package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoleRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	EmailService emailService;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findAllWithoutAdmins() {
		List<User> users = userRepository.findAll();
		List<User> usersWithoutAdmins = new ArrayList<>();
		for (User user : users) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				if (role.getName().equals("ROLE_USER")) {
					usersWithoutAdmins.add(user);
				}
			}
		}
		return usersWithoutAdmins;
	}

	public List<String> getNamesList() {
		List<User> allUsers = findAll();
		List<String> userNames = new ArrayList<>();
		for (User user : allUsers) {
			userNames.add(user.getFirstName() + " " + user.getLastName());
		}
		return userNames;
	}

	public User findOne(String login) {
		return userRepository.findOne(login);
	}

	public void save(User user) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_AWAIT"));
		user.setRoles(roles);
		user.setCreateDate(new Date());
		userRepository.save(user);
	}

	public void updateToRoleUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setEnabled(true);
		user.setRoles(roles);
		String emailMessagecontent = "Poprawnie zarejestrowano uzytkownika ".concat(user.getLogin()).concat(
				" w systemie, od tej pory mo¿esz zalogowac sie w systemie, powiadomimy Cie kiedy wystartuje sezon.");
		emailService.sendNotification("leaguegamesystem@gmail.com", user.getEmailAdress(),
				"Rejestracja w systemie ligowych rozgrywek", emailMessagecontent);
		userRepository.save(user);
	}

	public void delete(String login) {
		userRepository.delete(login);
	}

	@Transactional
	public Page<User> findAllWithStatistics(String properties, Direction order) {
		return userRepository.findAll(new PageRequest(0, 30, order, properties));
	}

}

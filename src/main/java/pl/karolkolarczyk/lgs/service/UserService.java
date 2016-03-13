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

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.exception.ImpossibleResultException;
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
	MatchService matchService;

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
				if (role.getName().equals("ROLE_USER") || role.getName().equals("ROLE_DISQUALIFIED")) {
					usersWithoutAdmins.add(user);
				}
			}
		}
		return usersWithoutAdmins;
	}

	public List<User> findActiveAndDisqualifiedPlayers() {
		List<User> users = userRepository.findAll();
		List<User> usersWithoutAdmins = new ArrayList<>();
		for (User user : users) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				if (role.getName().equals("ROLE_USER") || role.getName().equals("ROLE_DISQUALIFIED")) {
					usersWithoutAdmins.add(user);
				}
			}
		}
		return usersWithoutAdmins;
	}

	public List<User> findActivePlayers() {
		List<User> users = userRepository.findAll();
		List<User> activeUsersWithoutAdmins = new ArrayList<>();
		for (User user : users) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				if (role.getName().equals("ROLE_USER")) {
					activeUsersWithoutAdmins.add(user);
				}
			}
		}
		return activeUsersWithoutAdmins;
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
		User user = userRepository.findOne(login);
		userRepository.delete(user);
	}

	@Transactional
	public Page<User> findAllWithStatistics(String properties, Direction order) {
		return userRepository.findAll(new PageRequest(0, 30, order, properties));
	}

	public void disqualifie(String login) {
		User disqualified = userRepository.findOne(login);
		disqualified.setEnabled(false);
		List<Role> rolesList = new ArrayList<>();
		rolesList.add(roleRepository.findByName("ROLE_DISQUALIFIED"));
		disqualified.setRoles(rolesList);
		List<Match> matches = disqualified.getMatches();
		for (Match match : matches) {
			List<User> users = match.getUsers();
			User user1 = users.get(0);
			User user2 = users.get(1);
			if (!match.isCompleted()) {
				if (disqualified.getLogin().equals(user1.getLogin())) {
					matchService.disqualifiedFromMatch(match, disqualified, user2);
				} else if (disqualified.getLogin().equals(user2.getLogin())) {
					matchService.disqualifiedFromMatch(match, disqualified, user1);
				} else {
					throw new ImpossibleResultException();
				}
				match.setCompleted(true);
			} else {
				if (disqualified.getLogin().equals(user1.getLogin())) {
					matchService.disqualifiedFromCompletedMatch(match, user1, user2);
				} else if (disqualified.getLogin().equals(user2.getLogin())) {
					matchService.disqualifiedFromCompletedMatch(match, user2, user1);
				}
			}
		}
	}
}

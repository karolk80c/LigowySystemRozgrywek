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
		String emailMessagecontent = "Poprawnie zarejestrowano użytkownika ".concat(user.getLogin()).concat(
				" w systemie, od tej pory możesz zalogowaś się w systemie, powiadomimy Cię kiedy wystartuje sezon.");
		emailService.sendNotification("leaguegamesystem@gmail.com", user.getEmailAdress(),
				"Rejestracja w systemie ligowych rozgrywek ping-ponga", emailMessagecontent);
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

	@Transactional
	public void disqualifie(String login) {
		User disqualified = userRepository.findOne(login);
		disqualified.setEnabled(false);
		emailService.sendNotification("leaguegamesystem@gmail.com", disqualified.getEmailAdress(), "Dyskwalifikacja",
				"Zostałeś zdyskwalifikowany z turnieju.");
		List<Role> rolesList = new ArrayList<>();
		rolesList.add(roleRepository.findByName("ROLE_DISQUALIFIED"));
		disqualified.setRoles(rolesList);
		userRepository.save(disqualified);
		List<Match> matches = disqualified.getMatches();
		if (matches != null) {
			for (Match match : matches) {
				List<User> users = match.getUsers();
				User user1 = users.get(0);
				User user2 = users.get(1);
				if (!match.isCompleted()) {
					if (disqualified.getLogin().equals(user1.getLogin())) {
						matchService.updateSecondPointAfterDisqualification(match, user2);
					} else if (disqualified.getLogin().equals(user2.getLogin())) {
						matchService.updateSecondPointAfterDisqualification(match, user1);
					} else {
						throw new ImpossibleResultException();
					}
				} else {
					if (disqualified.getLogin().equals(user1.getLogin())) {
						matchService.disqualifiedFromCompletedMatch(match, user2);
						emailService.sendNotification("leaguegamesystem@gmail.com", user2.getEmailAdress(),
								"Wiadomość dotycząca spotkania",
								"Twoj przeciwnik: " + user1.getFullName() + " został zdyskfalifikowany z turnieju");
					} else if (disqualified.getLogin().equals(user2.getLogin())) {
						matchService.disqualifiedFromCompletedMatch(match, user1);
					}
				}
				match.setCompleted(true);
				match.setMatchDate(new Date());
				match.setMatchPlace("Mecz się nie odbył");
				matchRepository.save(match);
			}
		}
		updateDisqualifieUser();
		matchService.updateUsersRanking();
	}

	private void updateDisqualifieUser() {
		List<User> allUsers = userRepository.findAll();
		int size = findActivePlayers().size();
		for (User disqualified : allUsers) {
			for (Role role : disqualified.getRoles()) {
				if (("ROLE_DISQUALIFIED").equals(role.getName())) {
					disqualified.setLostMatches(size);
					disqualified.setLostSets(size * 4);
					disqualified.setLostSmallPoints(size * 11 * 4);
					disqualified.setWonMatches(0);
					disqualified.setWonSets(0);
					disqualified.setWonSmallPoints(0);
				}
			}
		}
	}

	public void deactivate(String login) {
		User user = userRepository.findOne(login);
		disqualifie(login);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_AWAIT"));
		user.setRoles(roles);
		user.setEnabled(false);
		userRepository.save(user);
	}

	public void clearUsersData() {
		List<User> users = findActiveAndDisqualifiedPlayers();
		for (User user : users) {
			user.setLostMatches(0);
			user.setWonMatches(0);
			user.setLostSets(0);
			user.setWonSets(0);
			user.setLostSmallPoints(0);
			user.setWonSmallPoints(0);
			userRepository.save(user);
		}
	}

}
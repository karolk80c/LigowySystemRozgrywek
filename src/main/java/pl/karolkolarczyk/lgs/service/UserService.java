package pl.karolkolarczyk.lgs.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private List<User> findDisqualifiedPlayers() {
		List<User> users = userRepository.findAll();
		List<User> disqualifiedUsers = new ArrayList<>();
		for (User user : users) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				if ("ROLE_DISQUALIFIED".equals(role.getName())) {
					disqualifiedUsers.add(user);
				}
			}
		}
		return disqualifiedUsers;
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
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void updateToRoleUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setEnabled(true);
		user.setRoles(roles);
		String emailMessagecontent = "Poprawnie zarejestrowano użytkownika ".concat(user.getLogin())
				.concat(", od tej pory możesz zalogować się w systemie, powiadomimy Cię kiedy wystartuje sezon.");
		emailService.sendNotification(user.getEmailAdress(), "Rejestracja w systemie ligowych rozgrywek ping-ponga",
				emailMessagecontent);
		userRepository.save(user);
	}

	public void delete(String login) {
		User user = userRepository.findOne(login);
		if (user.getMatches().size() == 0) {
			userRepository.delete(user);
		} else {
			throw new RuntimeException("Nie można usunąć użytkownika który posiada mecze w aktywnym sezonie.");
		}

	}

	@Transactional
	public Page<User> findAllWithStatistics(String properties, Direction order) {
		return userRepository.findAll(new PageRequest(0, 1000, order, properties));
	}

	@Transactional
	public void disqualifie(String login) {
		User disqualified = userRepository.findOne(login);
		disqualified.setEnabled(false);
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
						emailService.sendNotification(user2.getEmailAdress(), "Wiadomość dotycząca spotkania",
								"Twój przeciwnik: " + user1.getFullName() + " został zdyskwalifikowany z turnieju");
					} else if (disqualified.getLogin().equals(user2.getLogin())) {
						matchService.disqualifiedFromCompletedMatch(match, user1);
						emailService.sendNotification(user1.getEmailAdress(), "Wiadomość dotycząca spotkania",
								"Twój przeciwnik: " + user2.getFullName() + " został zdyskwalifikowany z turnieju");
					}
				}
				match.setCompleted(true);
				match.setMatchDate(new Date());
				match.setMatchPlace("Mecz się nie odbył");
				matchRepository.save(match);
			}
			updateDisqualifieUser();
			matchService.updateUsersRanking();
		}
		emailService.sendNotification(disqualified.getEmailAdress(), "Dyskwalifikacja",
				"Zostałeś zdyskwalifikowany z turnieju.");
	}

	public void updateDisqualifieUser() {
		for (User disqualified : findDisqualifiedPlayers()) {
			int matchesSize = disqualified.getMatches().size();
			disqualified.setLostMatches(matchesSize);
			disqualified.setLostSets(matchesSize * 4);
			disqualified.setLostSmallPoints(matchesSize * 44);
			disqualified.setWonMatches(0);
			disqualified.setWonSets(0);
			disqualified.setWonSmallPoints(0);
		}
	}

	public void deactivate(String login) {
		User user = userRepository.findOne(login);
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
			user.setRankingPosition(0);
			userRepository.save(user);
		}
	}

	public void resetPassword(HttpServletRequest request) {
		String newPassword = RandomStringUtils.randomAlphabetic(7);
		String login = request.getParameter("login");
		String email = request.getParameter("emailAdress");
		User user = userRepository.findOne(login);
		if (user != null) {
			if (user.getEmailAdress().equals(email)) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(newPassword));
				emailService.sendNotification(email, "Nowe Hasło", "Nowe Hasło: ".concat(newPassword));
			} else {
				throw new RuntimeException("Nieprawidłowy adres email do podanego loginu");
			}
		} else {
			throw new RuntimeException("Użytkownik z podanym loginem nie istnieje");
		}
	}

	public void changePassword(HttpServletRequest request, Principal principal) {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		User user = userRepository.findOne(principal.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(encoder.encode(newPassword));
			emailService.sendNotification(user.getEmailAdress(), "Poprawnie zmieniono hasło",
					"Poprawnie zmieniono hasło<br> Twoje nowe hasło: " + newPassword);
		} else {
			throw new RuntimeException("Podane stare hasło nie zgadza się z obecnym");
		}
	}

	public void changePersonalData(HttpServletRequest request, Principal principal) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String contactNumber = request.getParameter("contactNumber");
		User user = userRepository.findOne(principal.getName());
		int count = 0;
		List<Match> matches = user.getMatches();
		if ((!firstName.trim().isEmpty()) && (!firstName.equals(user.getFirstName()))) {
			for (Match match : matches) {
				if (match.getFirstName().equals(user.getFullName())) {
					match.setFirstName(firstName + " " + user.getLastName());
				} else if (match.getSecondName().equals(user.getFullName())) {
					match.setSecondName(firstName + " " + user.getLastName());
				}
			}
			user.setFirstName(firstName);
			count++;
		}
		if ((!lastName.trim().isEmpty()) && (!lastName.equals(user.getLastName()))) {
			for (Match match : matches) {
				if (match.getFirstName().equals(user.getFullName())) {
					match.setFirstName(user.getFirstName() + " " + lastName);
				} else if (match.getSecondName().equals(user.getFullName())) {
					match.setSecondName(user.getFirstName() + " " + lastName);
				}
			}
			user.setLastName(lastName);
			count++;
		}
		if (!contactNumber.equals(user.getContactNumber())) {
			user.setContactNumber(contactNumber);
			count++;
		}
		if (count > 0) {
			userRepository.save(user);
		}
	}

	public String returnLoginByFullName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName).getLogin();
	}

	public void saveUserToRepository(User user) {
		userRepository.save(user);
	}

}
package pl.karolkolarczyk.lgs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.enums.Place;
import pl.karolkolarczyk.lgs.exception.NotExistingPlaceException;
import pl.karolkolarczyk.lgs.exception.ParseDateException;
import pl.karolkolarczyk.lgs.exception.UnacceptableResultException;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.SetRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Service

public class MatchService {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	SetRepository setRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	public List<Match> findAll() {
		return matchRepository.findAll();
	}

	public Match findOne(Integer id) {
		return matchRepository.findOne(id);
	}

	@Transactional
	public Match findOneWithSets(Integer id) {
		Match match = findOne(id);
		List<Set> sets = setRepository.findByMatch(match);
		match.setSets(sets);
		return match;
	}

	public void disqualifiedFromCompletedMatch(Match match, User second) {
		boolean isTwoPlayerDisqualified = false;
		List<Role> roles = second.getRoles();
		for (Role role : roles) {
			if (("ROLE_DISQUALIFIED").equals(role.getName())) {
				isTwoPlayerDisqualified = true;
			}
		}
		resetPointsForMatch(match, second);
		if (!isTwoPlayerDisqualified) {
			updateSecondPointAfterDisqualification(match, second);
		}
	}

	public void updateSecondPointAfterDisqualification(Match match, User winner) {
		int firstSmallPoint = 0;
		int secondSmallPoint = 0;
		if (match.getFirstName().equals(winner.getFullName())) {
			match.setFirstPoints(4);
			match.setSecondPoints(0);
			firstSmallPoint = 11;
		} else if (match.getSecondName().equals(winner.getFullName())) {
			match.setFirstPoints(0);
			match.setSecondPoints(4);
			secondSmallPoint = 11;
		} else {
			throw new RuntimeException();
		}
		winner.setWonSets(winner.getWonSets() + 4);
		winner.setWonMatches(winner.getWonMatches() + 1);
		winner.setWonSmallPoints(winner.getWonSmallPoints() + 44);

		removeSetsFromMatch(match);

		for (int i = 0; i < 4; i++) {
			Set set = new Set(firstSmallPoint, secondSmallPoint, match);
			setRepository.save(set);
		}
		match.setFirstApproved(true);
		match.setSecondApproved(true);
		matchRepository.save(match);
	}

	public void removeSetsFromMatch(Match match) {
		List<Set> sets = setRepository.findByMatch(match);
		if (sets != null) {
			for (Set set : sets) {
				setRepository.delete(set);
			}
		}
	}

	private void resetPointsForMatch(Match match, User second) {
		int firstSmallPoints = 0;
		int secondSmallPoints = 0;

		for (Set set : match.getSets()) {
			firstSmallPoints += set.getFirstPlayerScore();
			secondSmallPoints += set.getSecondPlayerScore();
		}
		if (match.getFirstPoints() > match.getSecondPoints()) {
			if (match.getFirstName().equals(second.getFullName())) {
				second.setWonMatches(second.getWonMatches() - 1);
				second.setWonSets(second.getWonSets() - match.getFirstPoints());
				second.setLostSets(second.getLostSets() - match.getSecondPoints());
				second.setWonSmallPoints(second.getWonSmallPoints() - firstSmallPoints);
				second.setLostSmallPoints(second.getLostSmallPoints() - secondSmallPoints);
			} else {
				second.setLostMatches(second.getLostMatches() - 1);
				second.setWonSets(second.getWonSets() - match.getSecondPoints());
				second.setLostSets(second.getLostSets() - match.getFirstPoints());
				second.setWonSmallPoints(second.getWonSmallPoints() - secondSmallPoints);
				second.setLostSmallPoints(second.getLostSmallPoints() - firstSmallPoints);
			}
		} else if (match.getFirstPoints() < match.getSecondPoints()) {
			if (match.getFirstName().equals(second.getFullName())) {
				second.setLostMatches(second.getLostMatches() - 1);
				second.setWonSets(second.getWonSets() - match.getSecondPoints());
				second.setLostSets(second.getLostSets() - match.getFirstPoints());
				second.setWonSmallPoints(second.getWonSmallPoints() - secondSmallPoints);
				second.setLostSmallPoints(second.getLostSmallPoints() - firstSmallPoints);
			} else {
				second.setWonMatches(second.getWonMatches() - 1);
				second.setWonSets(second.getWonSets() - match.getFirstPoints());
				second.setLostSets(second.getLostSets() - match.getSecondPoints());
				second.setWonSmallPoints(second.getWonSmallPoints() - firstSmallPoints);
				second.setLostSmallPoints(second.getLostSmallPoints() - secondSmallPoints);
			}
		}
	}

	@Transactional
	public void approveMatchByAdmin(Integer id) {
		Match match = findOne(id);
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		sendNotificationTo(user1);
		sendNotificationTo(user2);
		List<Set> sets = setRepository.findByMatch(match);
		int firstSmallPoints = 0;
		int secondSmallPoints = 0;
		if (sets.size() > 0) {
			for (Set set : sets) {
				firstSmallPoints += set.getFirstPlayerScore();
				secondSmallPoints += set.getSecondPlayerScore();
			}
		}
		if ((user1.getFullName()).equals(match.getFirstName())) {
			updatePoints(match, firstSmallPoints, secondSmallPoints, user1, user2);
		} else if ((user2.getFullName()).equals(match.getFirstName())) {
			updatePoints(match, firstSmallPoints, secondSmallPoints, user2, user1);
		}
		saveUsers(user1, user2, match);
	}

	@Transactional
	public void approve(Integer id, User user) {
		Match match = findOne(id);
		if ((match.getFirstPoints() < 4 && match.getSecondPoints() < 4) || match.getFirstPoints() > 4
				|| match.getSecondPoints() > 4) {
			throw new UnacceptableResultException(match.getFirstPoints(), match.getSecondPoints());
		}
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		List<Set> sets = setRepository.findByMatch(match);
		int firstSmallPoints = 0;
		int secondSmallPoints = 0;
		if (sets.size() > 0) {
			for (Set set : sets) {
				firstSmallPoints += set.getFirstPlayerScore();
				secondSmallPoints += set.getSecondPlayerScore();
			}
		}

		if ((user.getFullName()).equals(match.getFirstName())) {
			match.setFirstApproved(true);
			if (!match.isSecondApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					sendNotificationTo(user2);
				} else {
					sendNotificationTo(user1);
				}
			} else {
				if (user.getLogin().equals(user1.getLogin())) {
					updatePoints(match, firstSmallPoints, secondSmallPoints, user1, user2);
				} else if (user.getLogin().equals(user2.getLogin())) {
					updatePoints(match, firstSmallPoints, secondSmallPoints, user2, user1);
				}
				saveUsers(user1, user2, match);
			}
		} else if ((user.getFullName()).equals(match.getSecondName())) {
			match.setSecondApproved(true);
			if (!match.isFirstApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					sendNotificationTo(user2);
				} else {
					sendNotificationTo(user1);
				}
			} else {
				if (user.getLogin().equals(user1.getLogin())) {
					updatePoints(match, firstSmallPoints, secondSmallPoints, user2, user1);
				} else if (user.getLogin().equals(user2.getLogin())) {
					updatePoints(match, firstSmallPoints, secondSmallPoints, user1, user2);
				}
				saveUsers(user1, user2, match);
			}
		}

	}

	private void saveUsers(User user1, User user2, Match match) {
		match.setCompleted(true);
		matchRepository.save(match);
		userRepository.save(user1);
		userRepository.save(user2);
		updateUsersRanking();
	}

	private void sendNotificationTo(User user) {
		emailService.sendNotification(user.getEmailAdress(), "Aktualizacja meczu ping-pong",
				"Twój przeciwnik w³aœnie zaktualizowa³ wynik meczu, sprawdŸ szczegó³y spotkania i jeœli wszystko siê zgadza zaakceptuj wynik.");
	}

	private void updatePoints(Match match, int firstSmallPoints, int secondSmallPoints, User user1, User user2) {
		user1.setWonSmallPoints(user1.getWonSmallPoints() + firstSmallPoints);
		user1.setLostSmallPoints(user1.getLostSmallPoints() + secondSmallPoints);
		user2.setWonSmallPoints(user2.getWonSmallPoints() + secondSmallPoints);
		user2.setLostSmallPoints(user2.getLostSmallPoints() + firstSmallPoints);
		user1.setWonSets(user1.getWonSets() + match.getFirstPoints());
		user2.setWonSets(user2.getWonSets() + match.getSecondPoints());
		user2.setLostSets(user2.getLostSets() + match.getFirstPoints());
		user1.setLostSets(user1.getLostSets() + match.getSecondPoints());
		if (match.getFirstPoints() > match.getSecondPoints()) {
			user2.setLostMatches(user2.getLostMatches() + 1);
			user1.setWonMatches(user1.getWonMatches() + 1);
		} else if (match.getFirstPoints() < match.getSecondPoints()) {
			user1.setLostMatches(user1.getLostMatches() + 1);
			user2.setWonMatches(user2.getWonMatches() + 1);
		}
	}

	public void updateUsersRanking() {
		List<User> usersList = compareAndSortUsers();
		int i = 1;
		for (User user : usersList) {
			user.setRankingPosition(i);
			i++;
			userRepository.save(user);
		}
	}

	public List<User> compareAndSortUsers() {
		List<User> usersList = userService.findActiveAndDisqualifiedPlayers();
		Comparator<User> comparator = Comparator.comparing(User::getWonMatches).thenComparing(User::getBalanceSets)
				.thenComparing(User::getBalanceSmallPoints);
		Collections.sort(usersList, comparator.reversed());
		return usersList;
	}

	@Transactional
	public String getRecipientLogin(String name, Integer id) {
		Match match = findOne(id);
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		if (user1.getLogin().equals(name)) {
			return user2.getLogin();
		} else {
			return user1.getLogin();
		}
	}

	public void cancel(Integer id) {
		Match match = findOne(id);
		if (match.isFirstApproved() && !match.isSecondApproved()) {
			match.setFirstApproved(false);
		} else if (match.isSecondApproved() && !match.isFirstApproved()) {
			match.setSecondApproved(false);
		}
		matchRepository.save(match);
	}

	public void addDateAndPlace(HttpServletRequest request, Match matchFromRepository) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		try {
			Date date = dateFormat.parse(request.getParameter("matchDate"));
			matchFromRepository.setMatchDate(date);
		} catch (ParseException e) {
			throw new ParseDateException("Nie uda³o siê zapisaæ daty w podanym formacie");
		}
		String matchPlaceParameter = request.getParameter("matchPlace");
		if (matchPlaceParameter.equals(Place.A.getName())) {
			matchFromRepository.setMatchPlace(Place.A.getName());
		} else if (matchPlaceParameter.equals(Place.F.getName())) {
			matchFromRepository.setMatchPlace(Place.F.getName());
		} else if (matchPlaceParameter.equals(Place.G.getName())) {
			matchFromRepository.setMatchPlace(Place.G.getName());
		} else if (matchPlaceParameter.equals(Place.OTHER.getName())) {
			matchFromRepository.setMatchPlace(Place.OTHER.getName());
		} else {
			throw new NotExistingPlaceException("Nie znaleziono takiego miejsca");
		}
		matchRepository.save(matchFromRepository);
	}

	public void delete(Match match) {
		matchRepository.delete(match.getId());
	}

	public void clearMatchesData() {
		List<Match> matches = matchRepository.findAll();
		for (Match match : matches) {
			clearMatchData(match.getId());
		}
	}

	public void clearMatchData(int id) {
		Match match = findOneWithSets(id);
		match.setFirstPoints(0);
		match.setSecondPoints(0);
		match.setCompleted(false);
		match.setFirstApproved(false);
		match.setSecondApproved(false);
		match.setMatchDate(null);
		match.setMatchPlace("");
		List<Set> sets = match.getSets();
		if (sets.size() > 0) {
			for (Set set : sets) {
				setRepository.delete(set.getId());
			}
		}
		match.setSets(null);
		matchRepository.save(match);
	}

}
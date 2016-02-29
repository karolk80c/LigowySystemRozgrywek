package pl.karolkolarczyk.lgs.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
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

	@Transactional
	public void approve(Integer id, User user) throws UnacceptableResultException {
		Match match = findOne(id);
		List<Set> sets = setRepository.findByMatch(match);
		int firstSmallPoints = 0;
		int secondSmallPoints = 0;
		for (Set set : sets) {
			firstSmallPoints += set.getFirstPlayerScore();
			secondSmallPoints += set.getSecondPlayerScore();
		}
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		if ((user.getFirstName().concat(" ").concat(user.getLastName())).equals(match.getFirstName())) {
			match.setFirstApproved(true);
			if (!match.isSecondApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					emailService.sendNotification("leaguegamesystem@gmail.com", user2.getEmailAdress(),
							"Aktualizacja meczu ping-pong",
							"Twoj przeciwnik wlasnie zaaktualizowal wynik meczu, sprawdz szczegoly spotkania i jesli wszystko sie zgadza zaakceptuj wynik.");
				} else {
					emailService.sendNotification("leaguegamesystem@gmail.com", user1.getEmailAdress(),
							"Aktualizacja meczu ping-pong",
							"Twoj przeciwnik wlasnie zaaktualizowal wynik meczu, sprawdz szczegoly spotkania i jesli wszystko sie zgadza zaakceptuj wynik.");
				}
			} else {
				match.setMatchDate(new Date());
				if (user.getLogin().equals(user1.getLogin())) {
					if (match.getFirstPoints() == 3 && (match.getSecondPoints() == 1 || match.getSecondPoints() == 0)) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 3);
					} else if (match.getFirstPoints() == 3 && match.getSecondPoints() == 2) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 2);
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 1);
					} else if (match.getSecondPoints() == 3
							&& (match.getFirstPoints() == 1 || match.getFirstPoints() == 0)) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 3);
					} else if (match.getSecondPoints() == 3 && match.getFirstPoints() == 2) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 2);
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 1);
					} else {
						throw new UnacceptableResultException(match.getFirstPoints(), match.getSecondPoints());
					}
					user1.setWonSets(user1.getWonSets() + match.getFirstPoints());
					user2.setWonSets(user2.getWonSets() + match.getSecondPoints());
					user2.setLostSets(user2.getLostSets() + match.getFirstPoints());
					user1.setLostSets(user1.getLostSets() + match.getSecondPoints());

					user1.setWonSmallPoints(user1.getWonSmallPoints() + firstSmallPoints);
					user1.setLostSmallPoints(user1.getLostSmallPoints() + secondSmallPoints);
					user2.setWonSmallPoints(user2.getWonSmallPoints() + secondSmallPoints);
					user2.setLostSmallPoints(user2.getLostSmallPoints() + firstSmallPoints);

					if (match.getFirstPoints() > match.getSecondPoints()) {
						user2.setLostMatches(user2.getLostMatches() + 1);
						user1.setWonMatches(user1.getWonMatches() + 1);
					} else if (match.getFirstPoints() < match.getSecondPoints()) {
						user1.setLostMatches(user1.getLostMatches() + 1);
						user2.setWonMatches(user2.getWonMatches() + 1);
					}
				} else if (user.getLogin().equals(user2.getLogin())) {
					if (match.getFirstPoints() == 3 && (match.getSecondPoints() == 1 || match.getSecondPoints() == 0)) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 3);
					} else if (match.getFirstPoints() == 3 && match.getSecondPoints() == 2) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 2);
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 1);
					} else if (match.getSecondPoints() == 3
							&& (match.getFirstPoints() == 1 || match.getFirstPoints() == 0)) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 3);
					} else if (match.getSecondPoints() == 3 && match.getFirstPoints() == 2) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 2);
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 1);
					} else {
						throw new UnacceptableResultException(match.getFirstPoints(), match.getSecondPoints());
					}
					user2.setWonSets(user2.getWonSets() + match.getFirstPoints());
					user1.setWonSets(user1.getWonSets() + match.getSecondPoints());
					user1.setLostSets(user1.getLostSets() + match.getFirstPoints());
					user2.setLostSets(user2.getLostSets() + match.getSecondPoints());

					user2.setWonSmallPoints(user2.getWonSmallPoints() + firstSmallPoints);
					user2.setLostSmallPoints(user2.getLostSmallPoints() + secondSmallPoints);
					user1.setWonSmallPoints(user1.getWonSmallPoints() + secondSmallPoints);
					user1.setLostSmallPoints(user1.getLostSmallPoints() + firstSmallPoints);
					if (match.getFirstPoints() > match.getSecondPoints()) {
						user1.setLostMatches(user1.getLostMatches() + 1);
						user2.setWonMatches(user2.getWonMatches() + 1);
					} else if (match.getFirstPoints() < match.getSecondPoints()) {
						user2.setLostMatches(user2.getLostMatches() + 1);
						user1.setWonMatches(user1.getWonMatches() + 1);
					}
				}
				userRepository.save(user1);
				userRepository.save(user2);
			}
		} else if ((user.getFirstName().concat(" ").concat(user.getLastName())).equals(match.getSecondName())) {
			match.setSecondApproved(true);
			if (!match.isFirstApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					String topic = "Aktualizacja meczu ping-ponga " + match.getFirstName() + " vs "
							+ match.getSecondName();
					emailService.sendNotification("leaguegamesystem@gmail.com", user2.getEmailAdress(), topic,
							"Twoj przeciwnik wlasnie zaaktualizowal wynik meczu, sprawdz szczegoly spotkania i jesli wszystko sie zgadza zaakceptuj wynik.");
				} else {
					emailService.sendNotification("leaguegamesystem@gmail.com", user1.getEmailAdress(),
							"Aktualizacja meczu ping-pong",
							"Twoj przeciwnik wlasnie zaaktualizowal wynik meczu, sprawdz szczegoly spotkania i jesli wszystko sie zgadza zaakceptuj wynik.");
				}
			} else {
				match.setMatchDate(new Date());
				if (user.getLogin().equals(user1.getLogin())) {
					if (match.getFirstPoints() == 3 && (match.getSecondPoints() == 1 || match.getSecondPoints() == 0)) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 3);
					} else if (match.getFirstPoints() == 3 && match.getSecondPoints() == 2) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 2);
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 1);
					} else if (match.getSecondPoints() == 3
							&& (match.getFirstPoints() == 1 || match.getFirstPoints() == 0)) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 3);
					} else if (match.getSecondPoints() == 3 && match.getFirstPoints() == 2) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 2);
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 1);
					} else {
						throw new UnacceptableResultException(match.getFirstPoints(), match.getSecondPoints());
					}
					user2.setWonSmallPoints(user2.getWonSmallPoints() + firstSmallPoints);
					user2.setLostSmallPoints(user2.getLostSmallPoints() + secondSmallPoints);
					user1.setWonSmallPoints(user1.getWonSmallPoints() + secondSmallPoints);
					user1.setLostSmallPoints(user1.getLostSmallPoints() + firstSmallPoints);
					user2.setWonSets(user2.getWonSets() + match.getFirstPoints());
					user1.setWonSets(user1.getWonSets() + match.getSecondPoints());
					user1.setLostSets(user1.getLostSets() + match.getFirstPoints());
					user2.setLostSets(user2.getLostSets() + match.getSecondPoints());
					if (match.getFirstPoints() > match.getSecondPoints()) {
						user1.setLostMatches(user1.getLostMatches() + 1);
						user2.setWonMatches(user2.getWonMatches() + 1);
					} else if (match.getFirstPoints() < match.getSecondPoints()) {
						user2.setLostMatches(user2.getLostMatches() + 1);
						user1.setWonMatches(user1.getWonMatches() + 1);
					}
				} else if (user.getLogin().equals(user2.getLogin())) {
					if (match.getFirstPoints() == 3 && (match.getSecondPoints() == 1 || match.getSecondPoints() == 0)) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 3);
					} else if (match.getFirstPoints() == 3 && match.getSecondPoints() == 2) {
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 2);
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 1);
					} else if (match.getSecondPoints() == 3
							&& (match.getFirstPoints() == 1 || match.getFirstPoints() == 0)) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 3);
					} else if (match.getSecondPoints() == 3 && match.getFirstPoints() == 2) {
						user2.setVolleyballPoints(user2.getVolleyballPoints() + 2);
						user1.setVolleyballPoints(user1.getVolleyballPoints() + 1);
					} else {
						throw new UnacceptableResultException(match.getFirstPoints(), match.getSecondPoints());
					}
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
				userRepository.save(user1);
				userRepository.save(user2);
			}
		}
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

}

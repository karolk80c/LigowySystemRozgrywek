package pl.karolkolarczyk.lgs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
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
	public void approve(Integer id, User user) {
		Match match = findOne(id);
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		if ((user.getFirstName().concat(" ").concat(user.getLastName())).equals(match.getFirstName())) {
			match.setFirstApproved(true);
			if (match.isSecondApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					if (match.getFirstPoints() > match.getSecondPoints()) {
						user2.setLostMatches(user2.getLostMatches() + 1);
						user1.setWonMatches(user1.getWonMatches() + 1);
					} else if (match.getFirstPoints() < match.getSecondPoints()) {
						user1.setLostMatches(user1.getLostMatches() + 1);
						user2.setWonMatches(user2.getWonMatches() + 1);
					}
				} else if (user.getLogin().equals(user2.getLogin())) {
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
			if (match.isFirstApproved()) {
				if (user.getLogin().equals(user1.getLogin())) {
					if (match.getFirstPoints() > match.getSecondPoints()) {
						user1.setLostMatches(user1.getLostMatches() + 1);
						user2.setWonMatches(user2.getWonMatches() + 1);
					} else if (match.getFirstPoints() < match.getSecondPoints()) {
						user2.setLostMatches(user2.getLostMatches() + 1);
						user1.setWonMatches(user1.getWonMatches() + 1);
					}
				} else if (user.getLogin().equals(user2.getLogin())) {
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

}

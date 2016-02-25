package pl.karolkolarczyk.lgs.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.SetRepository;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Service
public class SetService {

	@Autowired
	SetRepository setRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	UserRepository userRepository;

	public List<Set> findAll() {
		return setRepository.findAll();
	}

	@Transactional
	public void save(Set set, Integer id, User principal) {
		Match match = matchRepository.findOne(id);
		set.setMatch(match);
		setRepository.save(set);
		List<Set> sets = setRepository.findByMatch(match);
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		int firstPoints = 0;
		int secondPoints = 0;

		for (Set s : sets) {
			if (s.getFirstPlayerScore() > s.getSecondPlayerScore()) {
				firstPoints++;
			} else if (s.getFirstPlayerScore() < s.getSecondPlayerScore()) {
				secondPoints++;
			}
		}

		if (set.getFirstPlayerScore() > set.getSecondPlayerScore()) {
			if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
				user1.setWonSets(user1.getWonSets() + 1);
				user2.setLostSets(user2.getLostSets() + 1);
			} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
				user2.setWonSets(user2.getWonSets() + 1);
				user1.setLostSets(user1.getLostSets() + 1);
			}
		} else if (set.getFirstPlayerScore() < set.getSecondPlayerScore()) {
			if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
				user2.setWonSets(user2.getWonSets() + 1);
				user1.setLostSets(user1.getLostSets() + 1);
			} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
				user1.setWonSets(user1.getWonSets() + 1);
				user2.setLostSets(user2.getLostSets() + 1);
			}
		}

		if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
			user1.setWonSmallPoints(user1.getWonSmallPoints() + set.getFirstPlayerScore());
			user1.setLostSmallPoints(user1.getLostSmallPoints() + set.getSecondPlayerScore());
			user2.setWonSmallPoints(user2.getWonSmallPoints() + set.getSecondPlayerScore());
			user2.setLostSmallPoints(user2.getLostSmallPoints() + set.getFirstPlayerScore());
		} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
			user2.setWonSmallPoints(user2.getWonSmallPoints() + set.getFirstPlayerScore());
			user2.setLostSmallPoints(user2.getLostSmallPoints() + set.getSecondPlayerScore());
			user1.setWonSmallPoints(user1.getWonSmallPoints() + set.getSecondPlayerScore());
			user1.setLostSmallPoints(user1.getLostSmallPoints() + set.getFirstPlayerScore());
		}

		userRepository.save(user1);
		userRepository.save(user2);
		match.setFirstPoints(firstPoints);
		match.setSecondPoints(secondPoints);
		match.setMatchDate(new Date());
		matchRepository.save(match);

	}

}

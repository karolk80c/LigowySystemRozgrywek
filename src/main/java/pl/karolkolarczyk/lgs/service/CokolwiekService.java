package pl.karolkolarczyk.lgs.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Cokolwiek;
import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.CokolwiekRepository;
import pl.karolkolarczyk.lgs.repository.MatchRepository;

@Service
public class CokolwiekService {

	@Autowired
	CokolwiekRepository cokolwiekRepository;

	@Autowired
	MatchRepository matchRepository;

	public List<Cokolwiek> findAll() {
		return cokolwiekRepository.findAll();
	}

	@Transactional
	public void save(Cokolwiek cokolwiek, Integer id, User principal) {
		Match match = matchRepository.findOne(id);
		cokolwiek.setMatch(match);
		cokolwiekRepository.save(cokolwiek);
		List<Cokolwiek> cokolwieks = cokolwiekRepository.findByMatch(match);
		List<User> users = match.getUsers();
		User user1 = users.get(0);
		User user2 = users.get(1);
		int firstPoints = 0;
		int secondPoints = 0;

		for (Cokolwiek s : cokolwieks) {
			if (s.getFirstPlayerScore() > s.getSecondPlayerScore()) {
				firstPoints++;
			} else if (s.getFirstPlayerScore() < s.getSecondPlayerScore()) {
				secondPoints++;
			}
		}

		if (cokolwiek.getFirstPlayerScore() > cokolwiek.getSecondPlayerScore()) {
			if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
				user1.setWonSets(user1.getWonSets() + 1);
				user2.setLostSets(user2.getLostSets() + 1);
			} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
				user2.setWonSets(user2.getWonSets() + 1);
				user1.setLostSets(user1.getLostSets() + 1);
			}
		} else if (cokolwiek.getFirstPlayerScore() < cokolwiek.getSecondPlayerScore()) {
			if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
				user2.setWonSets(user2.getWonSets() + 1);
				user1.setLostSets(user1.getLostSets() + 1);
			} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
				user1.setWonSets(user1.getWonSets() + 1);
				user2.setLostSets(user2.getLostSets() + 1);
			}
		}

		if ((user1.getFirstName().concat(" ").concat(user1.getLastName())).equals(match.getFirstName())) {
			user1.setWonSmallPoints(user1.getWonSmallPoints() + cokolwiek.getFirstPlayerScore());
			user1.setLostSmallPoints(user1.getLostSmallPoints() + cokolwiek.getSecondPlayerScore());
			user2.setWonSmallPoints(user2.getWonSmallPoints() + cokolwiek.getSecondPlayerScore());
			user2.setLostSmallPoints(user2.getLostSmallPoints() + cokolwiek.getFirstPlayerScore());
		} else if ((user2.getFirstName().concat(" ").concat(user2.getLastName())).equals(match.getFirstName())) {
			user2.setWonSmallPoints(user2.getWonSmallPoints() + cokolwiek.getFirstPlayerScore());
			user2.setLostSmallPoints(user2.getLostSmallPoints() + cokolwiek.getSecondPlayerScore());
			user1.setWonSmallPoints(user1.getWonSmallPoints() + cokolwiek.getSecondPlayerScore());
			user1.setLostSmallPoints(user1.getLostSmallPoints() + cokolwiek.getFirstPlayerScore());
		}

		match.setFirstPoints(firstPoints);
		match.setSecondPoints(secondPoints);
		match.setMatchDate(new Date());
		matchRepository.save(match);

	}

}

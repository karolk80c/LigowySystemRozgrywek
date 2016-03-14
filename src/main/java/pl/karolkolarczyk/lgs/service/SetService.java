package pl.karolkolarczyk.lgs.service;

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
		if ((set.getFirstPlayerScore() < 11 && set.getSecondPlayerScore() < 11)
				|| ((set.getFirstPlayerScore() - set.getSecondPlayerScore() > 2
						|| (set.getFirstPlayerScore() - set.getSecondPlayerScore()) == 1)
						&& (set.getFirstPlayerScore() >= 11 && set.getSecondPlayerScore() > 9))
				|| ((set.getSecondPlayerScore() - set.getFirstPlayerScore() > 2
						|| (set.getSecondPlayerScore() - set.getFirstPlayerScore()) == 1)
						&& (set.getSecondPlayerScore() >= 11 && set.getFirstPlayerScore() > 9))
				|| (set.getFirstPlayerScore() > 11 && set.getSecondPlayerScore() < 10)
				|| (set.getSecondPlayerScore() > 11 && set.getFirstPlayerScore() < 10)) {
			throw new UnacceptableResultException(set.getFirstPlayerScore(), set.getSecondPlayerScore());
		}
		setRepository.save(set);
		if (set.getFirstPlayerScore() > set.getSecondPlayerScore()) {
			match.setFirstPoints(match.getFirstPoints() + 1);
		} else if (set.getFirstPlayerScore() < set.getSecondPlayerScore()) {
			match.setSecondPoints(match.getSecondPoints() + 1);
		} else {
			throw new UnacceptableResultException(set.getFirstPlayerScore(), set.getSecondPlayerScore());
		}

		matchRepository.save(match);
	}

	public Set findOne(Integer setId) {
		return setRepository.findOne(setId);
	}

	@Transactional
	public void delete(Integer setId, Integer matchId) {
		Match match = matchRepository.findOne(matchId);
		Set set = setRepository.findOne(setId);
		if (set.getFirstPlayerScore() > set.getSecondPlayerScore()) {
			match.setFirstPoints(match.getFirstPoints() - 1);
		} else if (set.getFirstPlayerScore() < set.getSecondPlayerScore()) {
			match.setSecondPoints(match.getSecondPoints() - 1);
		}
		matchRepository.save(match);
		setRepository.delete(setId);
	}

}

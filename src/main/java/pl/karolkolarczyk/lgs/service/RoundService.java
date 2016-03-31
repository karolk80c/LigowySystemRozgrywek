package pl.karolkolarczyk.lgs.service;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoundRepository;
import pl.karolkolarczyk.lgs.repository.SetRepository;

@Service
public class RoundService {

	@Autowired
	RoundRepository roundRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	SetRepository setRepository;

	@PersistenceContext
	EntityManager em;

	@Autowired
	private MatchService matchService;

	public List<Round> findAll() {
		return roundRepository.findAll();
	}

	public void delete(Round round) {
		roundRepository.delete(round.getId());
	}

	public Round findOneWithMatchesWithSets(int id) {
		Round round = roundRepository.findOne(id);
		List<Match> matches = matchRepository.findByRound(round);
		for (Match match : matches) {
			List<Set> sets = setRepository.findByMatch(match);
			match.setSets(sets);
		}
		round.setMatches(matches);
		return round;
	}

	public List<Round> findAllWithMatchesWithSets() {
		List<Round> rounds = findAll();
		for (Round round : rounds) {
			List<Match> matches = matchRepository.findByRound(round);
			for (Match match : matches) {
				List<Set> sets = setRepository.findByMatch(match);
				match.setSets(sets);
			}
			round.setMatches(matches);
		}
		return rounds;
	}

	public long count() {
		return roundRepository.count();
	}

	public void remove(HttpServletRequest request) {
		long count = Long.parseLong(request.getParameter("countRound"));
		long loopCounter = count;
		int size = (int) roundRepository.count();
		if (count <= size) {
			Random generator = new Random();
			for (long i = 0; i < loopCounter; i++) {
				int roundNumber = generator.nextInt(size + 1) + 1;
				Round round = roundRepository.findByNumber(roundNumber);
				if (round != null) {
					removeMatchesFromRound(round);
					delete(round);
					updateRoundNumberInformation();
				} else {
					loopCounter++;
				}
			}
		} else {
			throw new RuntimeException("Nie mo¿na usun¹æ wiêcej kolejek ni¿ istnieje");
		}
	}

	private void updateRoundNumberInformation() {
		List<Round> findAll = roundRepository.findAll();
		int number = 0;
		for (Round round : findAll) {
			round.setNumber(++number);
			roundRepository.save(round);
		}
	}

	public void removeMatchesFromRound(Round round) {
		List<Match> matches = round.getMatches();
		if (matches.size() > 0) {
			for (Match match : matches) {
				matchService.removeSetsFromMatch(match);
				matchService.delete(match);
			}
		}
	}

	public void removeAllRounds() {
		List<Round> allRounds = roundRepository.findAll();
		if (allRounds.size() > 0) {
			for (Round round : allRounds) {
				removeMatchesFromRound(round);
				delete(round);
			}
		}
	}

}

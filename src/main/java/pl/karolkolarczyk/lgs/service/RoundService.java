package pl.karolkolarczyk.lgs.service;

import java.util.List;

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

	public List<Round> findAll() {
		return roundRepository.findAll();
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

}

package pl.karolkolarczyk.lgs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Cokolwiek;
import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.repository.CokolwiekRepository;
import pl.karolkolarczyk.lgs.repository.MatchRepository;

@Service

public class MatchService {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	CokolwiekRepository setRepository;

	@Autowired
	CokolwiekRepository cokolwiekRepository;

	public List<Match> findAll() {
		return matchRepository.findAll();
	}

	public Match findOne(Integer id) {
		return matchRepository.findOne(id);
	}

	@Transactional
	public Match findOneWithSets(Integer id) {
		Match match = findOne(id);
		List<Cokolwiek> sets = setRepository.findByMatch(match);
		match.setCokolwieks(sets);
		return match;
	}



}

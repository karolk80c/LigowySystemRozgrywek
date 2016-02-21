package pl.karolkolarczyk.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.repository.MatchRepository;

@Service

public class MatchService {

	@Autowired
	MatchRepository matchRepository;

	public List<Match> findAll() {
		return matchRepository.findAll();
	}

}

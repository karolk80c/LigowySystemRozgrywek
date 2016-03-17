package pl.karolkolarczyk.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoundRepository;
import pl.karolkolarczyk.lgs.repository.SeasonRepository;
import pl.karolkolarczyk.lgs.repository.SetRepository;

@Service
public class SeasonService {

	@Autowired
	RoundRepository roundRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	SetRepository setRepository;

	@Autowired
	SeasonRepository seasonRepository;

	public List<Season> findAll() {
		return seasonRepository.findAll();
	}

	public void delete(Season season) {
		seasonRepository.delete(season.getId());
	}

}

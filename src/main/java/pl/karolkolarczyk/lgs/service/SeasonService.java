package pl.karolkolarczyk.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.repository.SeasonRepository;

@Service
public class SeasonService {

	@Autowired
	SeasonRepository seasonRepository;

	public List<Season> findAll() {
		return seasonRepository.findAll();
	}

	public void delete(Season season) {
		seasonRepository.delete(season.getId());
	}

	public void save(Season season) {
		seasonRepository.save(season);
	}

	Season findOneByNumber(String number) {
		return seasonRepository.findOneByNumber(number);
	}

}

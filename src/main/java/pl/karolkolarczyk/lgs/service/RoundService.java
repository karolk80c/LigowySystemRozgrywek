package pl.karolkolarczyk.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.repository.RoundRepository;

@Service
public class RoundService {
	
	@Autowired
	RoundRepository roundRepository;
	


	public List<Round> findAll() {
		return roundRepository.findAll();
	}

	
	
}

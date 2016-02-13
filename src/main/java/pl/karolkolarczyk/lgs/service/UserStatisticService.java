package pl.karolkolarczyk.lgs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.repository.UserRepository;
import pl.karolkolarczyk.lgs.repository.UserStatisticRepository;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.entity.UserStatistic;

@Service
@Transactional
public class UserStatisticService {
	
	@Autowired
	private UserStatisticRepository userStatisticRepository;
	
	public List<UserStatistic> findAll() {
		return userStatisticRepository.findAll();
	}
	
	public UserStatistic findOne(Integer id){
		return userStatisticRepository.findOne(id);
	}

}

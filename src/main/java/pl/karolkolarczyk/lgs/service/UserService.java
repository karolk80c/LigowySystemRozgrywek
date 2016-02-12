package pl.karolkolarczyk.lgs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.repository.UserRepository;
import pl.karolkolarczyk.lgs.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findOne(String login){
		return userRepository.findOne(login);
	}

}

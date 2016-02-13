package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.entity.UserStatistic;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoleRepository;
import pl.karolkolarczyk.lgs.repository.SetRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;
import pl.karolkolarczyk.lgs.repository.UserStatisticRepository;
import java.util.Date;

@Transactional
@Service
public class InitDatabaseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserStatisticRepository userStatisticRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private SetRepository setRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		List<Role> rolesList = new ArrayList<>();
		rolesList.add(roleAdmin);

		List<Role> testUserRolesList = new ArrayList<Role>();
		testUserRolesList.add(roleUser);

		UserStatistic testUserStatistic = new UserStatistic();
		testUserStatistic.setLostSets(13);
		testUserStatistic.setLostSmallPoints(58);
		testUserStatistic.setWonSmallPoints(120);
		testUserStatistic.setWonSets(15);
		userStatisticRepository.save(testUserStatistic);

		UserStatistic testUserStatistic2 = new UserStatistic();
		testUserStatistic2.setLostSets(3);
		testUserStatistic2.setLostSmallPoints(24);
		testUserStatistic2.setWonSmallPoints(67);
		testUserStatistic2.setWonSets(12);
		userStatisticRepository.save(testUserStatistic2);
		
		Match match = new Match();
		match.setMatchDate(new Date());
		matchRepository.save(match);
		
		List<Match> matches = new ArrayList<>();
		matches.add(match);

		User userAdmin = new User();
		userAdmin.setRoles(rolesList);
		userAdmin.setContactNumber("790215666");
		userAdmin.setEmailAdress("karolk80c@gmail.com");
		userAdmin.setFirstName("Karol");
		userAdmin.setLastName("Kolarczyk");
		userAdmin.setLogin("karolk80c");
		userAdmin.setPassword("Kolakola1");
		userAdmin.setEnabled(true);
		userRepository.save(userAdmin);

		User userTest = new User();
		userTest.setContactNumber("999999999");
		userTest.setEmailAdress("supertestowy@gmail.com");
		userTest.setFirstName("Janusz");
		userTest.setLastName("Testow");
		userTest.setRoles(testUserRolesList);
		userTest.setUserStatistic(testUserStatistic);
		userTest.setLogin("supertestowy");
		userTest.setPassword("Kolakola1");
		userTest.setMatches(matches);
		userTest.setEnabled(true);
		userRepository.save(userTest);

		User userTest2 = new User();
		userTest2.setContactNumber("123456789");
		userTest2.setEmailAdress("supertestowy2@gmail.com");
		userTest2.setFirstName("Franek");
		userTest2.setLastName("Lowca");
		userTest2.setRoles(testUserRolesList);
		userTest2.setUserStatistic(testUserStatistic2);
		userTest2.setLogin("supertestowy2");
		userTest2.setPassword("Kolakola1");
		userTest2.setMatches(matches);
		userRepository.save(userTest2);

		List<User> userMatchList = new ArrayList<>();
		userMatchList.add(userTest);
		userMatchList.add(userTest2);

		Set firstSet = new Set();
		firstSet.setFirstPlayerScore(5);
		firstSet.setSecondPlayerScore(13);
		firstSet.setMatch(match);
		setRepository.save(firstSet);

		Set secondSet = new Set();
		secondSet.setFirstPlayerScore(15);
		secondSet.setSecondPlayerScore(10);
		secondSet.setMatch(match);
		setRepository.save(secondSet);

	}

}

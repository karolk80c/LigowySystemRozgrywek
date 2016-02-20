package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.Set;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoleRepository;
import pl.karolkolarczyk.lgs.repository.SetRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Transactional
@Service
public class InitDatabaseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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

		Role roleAwait = new Role();
		roleAwait.setName("ROLE_AWAIT");
		roleRepository.save(roleAwait);

		List<Role> rolesList = new ArrayList<>();
		rolesList.add(roleAdmin);

		List<Role> testUserRolesList = new ArrayList<Role>();
		testUserRolesList.add(roleUser);

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
		userAdmin.setLogin("admin");
		userAdmin.setPassword("Kolakola1");
		userAdmin.setEnabled(true);
		userRepository.save(userAdmin);

		User userTest = new User();
		userTest.setLostSets(9);
		userTest.setLostSmallPoints(50);
		userTest.setWonSmallPoints(120);
		userTest.setWonSets(15);
		userTest.setWonMatches(15);
		userTest.setLostMatches(13);
		userTest.setContactNumber("999999999");
		userTest.setEmailAdress("supertestowy@gmail.com");
		userTest.setFirstName("Janusz");
		userTest.setLastName("Kowal");
		userTest.setRoles(testUserRolesList);
		userTest.setLogin("supertestowy");
		userTest.setPassword("Kolakola1");
		userTest.setMatches(matches);
		userTest.setEnabled(true);
		userRepository.save(userTest);

		User userTest2 = new User();
		userTest2.setLostSets(3);
		userTest2.setLostSmallPoints(24);
		userTest2.setWonSmallPoints(67);
		userTest2.setWonSets(12);
		userTest2.setWonMatches(7);
		userTest2.setLostMatches(8);
		userTest2.setContactNumber("123456789");
		userTest2.setEmailAdress("supertestowy2@gmail.com");
		userTest2.setFirstName("Franek");
		userTest2.setLastName("Urban");
		userTest2.setRoles(testUserRolesList);
		userTest2.setLogin("supertestowy2");
		userTest2.setPassword("Kolakola1");
		userTest2.setMatches(matches);
		userTest2.setEnabled(true);
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

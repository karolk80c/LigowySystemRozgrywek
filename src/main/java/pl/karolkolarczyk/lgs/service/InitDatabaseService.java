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
		userTest2.setEnabled(true);
		userRepository.save(userTest2);

		User userTest3 = new User();
		userTest3.setLostSets(5);
		userTest3.setLostSmallPoints(10);
		userTest3.setWonSmallPoints(5);
		userTest3.setWonSets(10);
		userTest3.setWonMatches(12);
		userTest3.setLostMatches(11);
		userTest3.setContactNumber("999999999");
		userTest3.setEmailAdress("supertestowy5@gmail.com");
		userTest3.setFirstName("Michal");
		userTest3.setLastName("Aniol");
		userTest3.setRoles(testUserRolesList);
		userTest3.setLogin("supertestowy3");
		userTest3.setPassword("Kolakola1");
		userTest3.setEnabled(true);
		userRepository.save(userTest3);

		User userTest4 = new User();
		userTest4.setContactNumber("999999999");
		userTest4.setEmailAdress("supertestowy5@gmail.com");
		userTest4.setFirstName("Adam");
		userTest4.setLastName("Wojcik");
		userTest4.setRoles(testUserRolesList);
		userTest4.setLogin("supertestowy4");
		userTest4.setPassword("Kolakola1");
		userTest4.setEnabled(true);
		userRepository.save(userTest4);

		User userTest5 = new User();
		userTest5.setContactNumber("999999999");
		userTest5.setEmailAdress("supertestowy5@gmail.com");
		userTest5.setFirstName("Jan");
		userTest5.setLastName("Gracz");
		userTest5.setRoles(testUserRolesList);
		userTest5.setLogin("supertestowy5");
		userTest5.setPassword("Kolakola1");
		userTest5.setEnabled(true);
		userRepository.save(userTest5);

		User userTest6 = new User();
		userTest6.setContactNumber("999999999");
		userTest6.setEmailAdress("supertestowy5@gmail.com");
		userTest6.setFirstName("Lcujan");
		userTest6.setLastName("Pasta");
		userTest6.setRoles(testUserRolesList);
		userTest6.setLogin("supertestowy6");
		userTest6.setPassword("Kolakola1");
		userTest6.setEnabled(true);
		userRepository.save(userTest6);

		List<User> userMatchList = new ArrayList<>();
		userMatchList.add(userTest3);
		userMatchList.add(userTest2);

		List<User> userMatchList2 = new ArrayList<>();
		userMatchList2.add(userTest);
		userMatchList2.add(userTest2);

		Match match = new Match();
		match.setMatchDate(new Date());
		match.setFirstName(userMatchList.get(0).getFirstName());
		match.setSecondName(userMatchList.get(1).getFirstName());
		match.setUsers(userMatchList);
		matchRepository.save(match);

		Match match2 = new Match();
		match2.setMatchDate(new Date());
		match2.setFirstName(userMatchList2.get(1).getFirstName());
		match2.setSecondName(userMatchList2.get(1).getFirstName());
		match.setUsers(userMatchList2);
		matchRepository.save(match2);

		List<Match> matches = new ArrayList<>();
		matches.add(match);
		matches.add(match2);

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

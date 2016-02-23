package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.CokolwiekRepository;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoleRepository;
import pl.karolkolarczyk.lgs.repository.RoundRepository;
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
	private CokolwiekRepository setRepository;

	@Autowired
	RoundRepository roundRepository;

	@Autowired
	DrawService drawService;

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
		userAdmin.setFirstName("Admin");
		userAdmin.setLastName("Administrator");
		userAdmin.setLogin("admin");
		userAdmin.setPassword("admin");
		userAdmin.setEnabled(true);
		userRepository.save(userAdmin);

		User userTest = new User();
		userTest.setContactNumber("999999999");
		userTest.setEmailAdress("supertestowy@gmail.com");
		userTest.setFirstName("Janusz");
		userTest.setLastName("Kowal");
		userTest.setRoles(testUserRolesList);
		userTest.setLogin("supertestowy");
		userTest.setPassword("admin");
		userTest.setEnabled(true);
		userRepository.save(userTest);

		User userTest2 = new User();
		userTest2.setContactNumber("123456789");
		userTest2.setEmailAdress("supertestowy2@gmail.com");
		userTest2.setFirstName("Franek");
		userTest2.setLastName("Urban");
		userTest2.setRoles(testUserRolesList);
		userTest2.setLogin("supertestowy2");
		userTest2.setPassword("admin");
		userTest2.setEnabled(true);
		userRepository.save(userTest2);

		User userTest3 = new User();
		userTest3.setContactNumber("999999999");
		userTest3.setEmailAdress("supertestowy5@gmail.com");
		userTest3.setFirstName("Michal");
		userTest3.setLastName("Aniol");
		userTest3.setRoles(testUserRolesList);
		userTest3.setLogin("supertestowy3");
		userTest3.setPassword("admin");
		userTest3.setEnabled(true);
		userRepository.save(userTest3);

		User userTest4 = new User();
		userTest4.setContactNumber("999999999");
		userTest4.setEmailAdress("supertestowy5@gmail.com");
		userTest4.setFirstName("Adam");
		userTest4.setLastName("Wojcik");
		userTest4.setRoles(testUserRolesList);
		userTest4.setLogin("supertestowy4");
		userTest4.setPassword("admin");
		userTest4.setEnabled(true);
		userRepository.save(userTest4);

		User userTest5 = new User();
		userTest5.setContactNumber("999999999");
		userTest5.setEmailAdress("supertestowy5@gmail.com");
		userTest5.setFirstName("Jan");
		userTest5.setLastName("Gracz");
		userTest5.setRoles(testUserRolesList);
		userTest5.setLogin("supertestowy5");
		userTest5.setPassword("admin");
		userTest5.setEnabled(true);
		userRepository.save(userTest5);

		User userTest6 = new User();
		userTest6.setContactNumber("999999999");
		userTest6.setEmailAdress("supertestowy5@gmail.com");
		userTest6.setFirstName("Lcujan");
		userTest6.setLastName("Pasta");
		userTest6.setRoles(testUserRolesList);
		userTest6.setLogin("supertestowy6");
		userTest6.setPassword("admin");
		userTest6.setEnabled(true);
		userRepository.save(userTest6);

		drawService.draw();

	}

}

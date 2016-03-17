package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.User;
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
	RoundRepository roundRepository;

	@Autowired
	DrawService drawService;

	@PostConstruct
	public void init() {

		if (roleRepository.count() == 0 && userRepository.count() == 0) {
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			Role roleAwait = new Role();
			roleAwait.setName("ROLE_AWAIT");
			roleRepository.save(roleAwait);

			Role roleDisqualified = new Role("ROLE_DISQUALIFIED");
			roleRepository.save(roleDisqualified);

			List<Role> rolesList = new ArrayList<>();
			rolesList.add(roleAdmin);

			User userAdmin = new User();
			userAdmin.setRoles(rolesList);
			userAdmin.setContactNumber("12312312");
			userAdmin.setEmailAdress("leaguegamesystem@gmail.com");
			userAdmin.setFirstName("Admin");
			userAdmin.setLastName("Administrator");
			userAdmin.setLogin("admin");
			userAdmin.setPassword("admin");
			userAdmin.setEnabled(true);
			userAdmin.setCreateDate(new Date());
			userRepository.save(userAdmin);

			User userHelp = new User();
			userHelp.setRoles(rolesList);
			userHelp.setContactNumber("12312312");
			userHelp.setEmailAdress("karolk80c@gmail.com");
			userHelp.setFirstName("Pomoc");
			userHelp.setLastName("Techniczna");
			userHelp.setLogin("karol");
			userHelp.setPassword("admin");
			userHelp.setEnabled(true);
			userHelp.setCreateDate(new Date());
			userRepository.save(userHelp);

			User userDirector = new User();
			userDirector.setRoles(rolesList);
			userDirector.setContactNumber("12312312");
			userDirector.setEmailAdress("director@test.com");
			userDirector.setFirstName("Dyrektor");
			userDirector.setLastName("Turnieju");
			userDirector.setLogin("director");
			userDirector.setPassword("admin");
			userDirector.setEnabled(true);
			userDirector.setCreateDate(new Date());
			userRepository.save(userDirector);

			List<Role> testUserRolesList = new ArrayList<Role>();
			testUserRolesList.add(roleUser);

			int howManyUserGenerate = 8;

			for (int i = 1; i <= howManyUserGenerate; i++) {
				User userTest = new User();
				userTest.setContactNumber("999999999");
				if (i != 1) {
					userTest.setEmailAdress("supertestowy" + i + "@gmail.com");
				} else {
					userTest.setEmailAdress("supertestowy@gmail.com");
				}
				userTest.setFirstName("Imie" + i);
				userTest.setLastName("Nazwisko" + i);
				userTest.setRoles(testUserRolesList);
				userTest.setLogin("test" + i);
				userTest.setPassword("admin");
				userTest.setEnabled(true);
				userTest.setCreateDate(new Date());
				userRepository.save(userTest);
			}

			drawService.draw();

		}

	}

}

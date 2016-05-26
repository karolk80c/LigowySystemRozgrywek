package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	MatchService matchService;

	@PostConstruct
	public void init() {

		matchService.updateOrder();

		if (roleRepository.count() == 0 && userRepository.count() == 0) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
			userAdmin.setEmailAdress("supertestowy@gmail.com");
			userAdmin.setFirstName("Administrator");
			userAdmin.setLastName("Serwisu");
			userAdmin.setLogin("admin");
			userAdmin.setPassword(encoder.encode("p1nk-p0nk"));
			userAdmin.setEnabled(true);
			userAdmin.setCreateDate(new Date());
			userRepository.save(userAdmin);

			User userDirector = new User();
			userDirector.setRoles(rolesList);
			userDirector.setEmailAdress("supertestowy@gmail.com");
			userDirector.setFirstName("Dyrektor");
			userDirector.setLastName("Serwisu");
			userDirector.setLogin("director");
			userDirector.setPassword(encoder.encode("p1nk-p0nk"));
			userDirector.setEnabled(false);
			userDirector.setCreateDate(new Date());
			userRepository.save(userDirector);

			User userHelp = new User();
			userHelp.setRoles(rolesList);
			userHelp.setContactNumber("790215666");
			userHelp.setEmailAdress("karol.kolarczyk@student.uj.edu.pl");
			userHelp.setFirstName("Pomoc");
			userHelp.setLastName("Techniczna");
			userHelp.setLogin("karolk80c");
			userHelp.setPassword(encoder.encode("p1nk-p0nk"));
			userHelp.setEnabled(true);
			userHelp.setCreateDate(new Date());
			userRepository.save(userHelp);

			List<Role> testUserRolesList = new ArrayList<Role>();
			testUserRolesList.add(roleUser);

			int howManyUserGenerate = 11;

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
				userTest.setPassword(encoder.encode("admin"));
				userTest.setEnabled(true);
				userTest.setCreateDate(new Date());
				userRepository.save(userTest);
			}

			// drawService.draw();

		}
	}
}

package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.RoleRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Transactional
@Service
public class InitDatabaseService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
		rolesList.add(roleUser);
		
		List<Role> testUserRolesList = new ArrayList<Role>();
		testUserRolesList.add(roleUser);
		
		User userAdmin = new User();
		userAdmin.setRoles(rolesList);
		userAdmin.setContactNumber("790215666");
		userAdmin.setEmailAdress("karolk80c@gmail.com");
		userAdmin.setFirstName("Karol");
		userAdmin.setLastName("Kolarczyk");
		userAdmin.setLogin("karolk80c");
		userAdmin.setPassword("Kolakola1");
		userRepository.save(userAdmin);
		
		User userTest = new User();
		userTest.setContactNumber("999999999");
		userTest.setEmailAdress("supertestowy@gmail.com");
		userTest.setFirstName("Janusz");
		userTest.setLastName("Testow");
	
		userTest.setRoles(testUserRolesList);
		userTest.setLogin("supertestowy");
		userTest.setPassword("Kolakola1");
		userRepository.save(userTest);
		
	}
	
	
}

package pl.karolkolarczyk.lgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByEmailAdress(String emailAdress);

	User findByFirstName(String firstName);

	User findByFirstNameAndLastName(String firstName, String lastName);

}

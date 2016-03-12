package pl.karolkolarczyk.lgs.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.karolkolarczyk.lgs.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String emailAdress, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.findByEmailAdress(emailAdress) == null;
	}

}

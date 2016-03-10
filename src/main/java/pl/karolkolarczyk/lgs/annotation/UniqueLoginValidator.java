package pl.karolkolarczyk.lgs.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.karolkolarczyk.lgs.repository.UserRepository;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueLogin constraintAnnotation) {
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.findOne(login) == null;
	}

}

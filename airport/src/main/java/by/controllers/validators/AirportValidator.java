package by.controllers.validators;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import by.dao.model.flight.Airport;

@Component
@PropertySource("classpath:operator.properties")
public class AirportValidator extends AbstractAirEntityValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Airport.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
	}

}

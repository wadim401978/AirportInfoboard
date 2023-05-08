package by.controllers.validators;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.dao.model.flight.Airline;

@Component
@PropertySource("classpath:operator.properties")
public class AirlineValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Airline.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "icaoCode", "admin.error.ICAO.empty");
		ValidationUtils.rejectIfEmpty(errors, "iataCode", "admin.error.IATA.empty");
	}

}

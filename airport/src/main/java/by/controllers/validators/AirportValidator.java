package by.controllers.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.dao.model.flight.Airport;

@Component
@PropertySource("classpath:operator.properties")
public class AirportValidator implements Validator {
	
	@Autowired
	private Environment env;

	@Override
	public boolean supports(Class<?> clazz) {
		return Airport.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ModelMap model = (ModelMap) target;
		boolean isEmpty = (boolean) model.getAttribute("isEmpty");
		ValidationUtils.rejectIfEmpty(errors, "icaoCode", "admin.error.ICAO.empty");
		ValidationUtils.rejectIfEmpty(errors, "iataCode", "admin.error.IATA.empty");
		if (isEmpty) {
			errors.reject("icaoCode", env.getProperty("admin.error.airport.empty"));
		}
	}

}

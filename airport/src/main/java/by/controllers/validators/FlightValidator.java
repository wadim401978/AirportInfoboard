package by.controllers.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import by.dao.model.flight.Flight;

@Component
@PropertySource("classpath:operator.properties")
public class FlightValidator implements Validator {
	
	@Autowired
	private Environment env;

	@Override
	public boolean supports(Class<?> clazz) {
		return Flight.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ModelMap model = (ModelMap) target;
		Flight flight = (Flight) model.getAttribute("flight");
		if (flight.getAirline().getId() == 0) {
			errors.reject("airline_id", env.getProperty("admin.error.airline.empty"));
		}
		
		if (flight.getAirport().getId() == 0) {
			errors.reject("airport_id", env.getProperty("admin.error.airport.empty"));
		}
		
		if (flight.getNumber() == 0) {
			errors.reject("number", env.getProperty("admin.error.flight.empty"));
		}

	}

}

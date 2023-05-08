package by.controllers.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.dao.model.flight.Arrival;

@Component
@PropertySource("classpath:operator.properties")
public class ArrivalValidator implements Validator {
	
	@Autowired
	private Environment env;

	@Override
	public boolean supports(Class<?> clazz) {
		return Arrival.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ModelMap model = (ModelMap) target;
		Arrival schFlight = (Arrival) model.getAttribute("arrival");
		
		if (schFlight.getFlight().getId() == 0) {
			errors.reject("number", env.getProperty("admin.error.flight.empty"));
		}
	}

}

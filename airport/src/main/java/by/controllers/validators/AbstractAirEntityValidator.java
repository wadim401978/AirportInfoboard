package by.controllers.validators;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import by.dao.model.common.Language;

@Component
@PropertySource("classpath:operator.properties")
public abstract class AbstractAirEntityValidator {
	
	@Autowired
	private Environment env;

	public void validate(Object target, Errors errors) {
		ModelMap model = (ModelMap) target;
		
		boolean isEmpty;
		ValidationUtils.rejectIfEmpty(errors, "icaoCode", "admin.error.ICAO.empty");
		ValidationUtils.rejectIfEmpty(errors, "iataCode", "admin.error.IATA.empty");
		
		if (model.getAttribute("isEmpty") == null) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}

		if (isEmpty) {
			errors.reject("names", env.getProperty("admin.error.name.empty"));
		} else {
			@SuppressWarnings("unchecked")
			Map<Language, String> names = (Map<Language, String>) model.getAttribute("names");
			Iterator<Entry<Language, String>> it = names.entrySet().iterator();
			while (it.hasNext()) {
				 Entry<Language, String> item = it.next();
				 if (item.getValue().trim().equals("")) {
					 errors.reject("names", env.getProperty("admin.error.name.empty"));
				 }
			}
		}
	}

}

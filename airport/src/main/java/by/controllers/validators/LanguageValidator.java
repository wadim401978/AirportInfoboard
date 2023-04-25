package by.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.dao.model.common.Language;

@Component("langValidator")
public class LanguageValidator implements Validator {
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Language.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "admin.error.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "tag", "admin.error.language.tag.empty");
	}
	
}

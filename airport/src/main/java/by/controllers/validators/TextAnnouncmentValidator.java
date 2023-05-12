package by.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import by.dao.model.infomsg.TextBlock;

@Component
public class TextAnnouncmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TextBlock.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "html", "admin.error.html.empty");
	}

}

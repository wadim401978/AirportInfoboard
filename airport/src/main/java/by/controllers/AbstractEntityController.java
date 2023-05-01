package by.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
@PropertySource("classpath:operator.properties")
public abstract class AbstractEntityController {
	
	@Autowired
	private Environment env;

	public Environment getEnv() {
		return env;
	}
	
}

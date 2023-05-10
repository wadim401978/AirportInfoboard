package by.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import by.dao.model.common.Language;
import by.services.Service;

@Controller
@PropertySource("classpath:operator.properties")
public abstract class AbstractEntityController {
	
	@Autowired
	private Environment env;

	public Environment getEnv() {
		return env;
	}
	
	public Map<Language, String> getNames(HttpServletRequest req, ModelMap model, Service<Language> langService) {
		Map<Language, String> map = new HashMap<>();
		Language lang;
		if (req.getParameter("isEmpty") != null) {
			Enumeration<String> parametersNames = req.getParameterNames();
			while (parametersNames.hasMoreElements()) {
				String parameterName = (String) parametersNames.nextElement();
				if (parameterName.trim().indexOf("langId") > -1) {
					String index = parameterName.substring("langId".length());
					lang = langService.get(Integer.parseInt(index.trim()));
					map.put(lang, req.getParameter("name" + index));
				}
			}
		}
		
		return map;
	}

	
}

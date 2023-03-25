package by.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.dao.model.common.Language;
import by.services.LanguageService;


@Controller
@RequestMapping("/admin/lang")
public class LanguageController {
	
	private LanguageService langService;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public LanguageController(LanguageService langService) {
		super();
		this.langService = langService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Language lang = langService.get(id);
		String title = operatorResourceBundle.getObject("admin.language") +": " + lang.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("language", lang);
		return "admin/lang";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = operatorResourceBundle.getObject("admin.language") + ": "
				+ operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("language", new Language(0, null, null, false));
		return "admin/lang";
    }

}

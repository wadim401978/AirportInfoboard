package by.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/{id}.html")
    public String lang(ModelMap model, @ModelAttribute(binding=false) Language lang) {
		String title = operatorResourceBundle.getObject("admin.language") +": " + lang.getName();
    	model.addAttribute("title", title);
		return "admin/lang";
    }
    
	@ModelAttribute
	public Language findLanguage(@PathVariable int id) {
	    return langService.get(id);
	}

}

package by.controllers;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.controllers.validators.LanguageValidator;
import by.dao.model.common.Language;
import by.services.LanguageService;


@Controller
@RequestMapping("/admin/lang")
@PropertySource("classpath:operator.properties")
public class LanguageController {
	
	@Autowired
	private LanguageValidator validator;
	
	@Autowired
	private Environment env;
	
	private LanguageService service;
	
    @Autowired(required = true)
	public LanguageController(LanguageService langService) {
		super();
		this.service = langService;
	}

	@RequestMapping(value = "/{id}.html")
    public String getLang(ModelMap model, @PathVariable("id") int id) {
		Language lang = service.get(id);
		String title = env.getProperty("admin.language") +": " + lang.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("language", lang);
		return "admin/lang";
    }
    
	@GetMapping(path = "/add.html")
    public String createNewLang(ModelMap model) {
		String title = env.getProperty("admin.language") + ": "
				+ env.getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("language", new Language(0, null, null, false));
		return "admin/lang";
    }

	@PostMapping(path = "/dlangs.html")
    public String deleteLang(ModelMap model, HttpServletRequest req) {
		Enumeration<String> pidEnum = req.getParameterNames();
		while (pidEnum.hasMoreElements()) {
			String pid = pidEnum.nextElement();
			int id = Integer.parseInt(pid, 10);
			service.remove(id);
		}
		return "redirect:../langs.html";
    }
	
    @PostMapping("/save.html")
    public String saveLang(@ModelAttribute Language lang, BindingResult result) {
        //TODO validation: TAG and NAME isNotEmpty
    	
    	validator.validate(lang, result);
//    	
//    	if (result.hasErrors()) {
//    		return "admin/lang";
//    	}
    	
    	service.save(lang);
		return "redirect:../langs.html";
    }

}

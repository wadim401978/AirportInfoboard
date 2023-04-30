package by.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    private String getTitle(int id, String name) {
    	if (id == 0) {
    		return env.getProperty("admin.language") + ": "
    				+ env.getProperty("admin.new.title");
    	} else {
    		return env.getProperty("admin.language") +": " + name;
    	}
    }

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET)
    public String getLang(ModelMap model, @PathVariable("id") int id) {
		Language lang = service.get(id);
		model.addAttribute("language", lang);
    	model.addAttribute("title", getTitle(id, lang.getName()));
		return "admin/lang";
    }
    
	@GetMapping(path = "/add.html")
    public String createNewLang(ModelMap model) {
		Language lang = new Language(0, null, null, false);
		model.addAttribute("language", lang);
    	model.addAttribute("title", getTitle(0, lang.getName()));
		return "admin/lang";
    }
	
	public String redirectLang(ModelMap model) {
		Language lang = (Language) model.getAttribute("language");
		model.addAttribute("language", lang);
    	model.addAttribute("title", getTitle(lang.getId(), lang.getName()));
		return "admin/lang";
	}

	@PostMapping(path = "/dlangs.html")
	public String deleteItems(HttpServletRequest req) {
		service.simpleRemoveItems(req);
		return "redirect:../langs.html";
    }
	
    @PostMapping("/save.html")
    public String saveLang(@ModelAttribute("language") Language language, BindingResult result, ModelMap model) {
    	validator.validate(language, result);
    	
    	if (result.hasErrors()) {
    		model.addAttribute("language", language);
    		return redirectLang(model);
    	}
    	
    	try {
    		service.save(language);
		} catch (DuplicateKeyException e) {
			result.rejectValue("tag", "admin.error.duplicated.field");
			return redirectLang(model);
		} catch (Exception e) {
			return redirectLang(model);
		}
    	
		return "redirect:../langs.html";
    }

}

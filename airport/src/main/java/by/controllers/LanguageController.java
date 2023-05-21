package by.controllers;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import by.app.exception.DeleteException;
import by.controllers.validators.LanguageValidator;
import by.dao.model.common.Language;
import by.services.LanguageService;

@Controller
@RequestMapping("/admin/lang")
public class LanguageController extends AbstractEntityController {
	
	@Autowired
	private LanguageValidator validator;
	
	@Autowired
	private LanguageService service;
	
	private String getTitle() {
		return getEnv().getProperty("admin.language") ;
	}
	private String getTitle(Language lang) {
		if (lang.getId() == 0) {
			return getTitle() + ": " + getEnv().getProperty("admin.new.title");
		} else {
			return getTitle() + ": " + lang.getName();
		}
	}
    
    private String sendLang(ModelMap model, Language lang) {
		model.addAttribute("language", lang);
    	model.addAttribute("title", getTitle(lang));
		return "admin/lang";
    }
    
	private String redirectLang(ModelMap model) {
		return sendLang(model, (Language) model.getAttribute("language"));
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET)
    public String getLang(ModelMap model, @PathVariable("id") int id) {
		return sendLang(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String createNewLang(ModelMap model) {
		return sendLang(model, new Language(0, null, null, false));
    }
	
	@PostMapping(path = "/dlangs.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					service.get(e.getEntityId()).getName()
					);
			session.setAttribute("error", msg);
		}
		
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

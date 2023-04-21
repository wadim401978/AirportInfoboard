package by.controller;

import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.common.Language;
import by.services.LanguageService;


@Controller
@RequestMapping("/admin/lang")
public class LanguageController {
	
	private LanguageService service;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public LanguageController(LanguageService langService) {
		super();
		this.service = langService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Language lang = service.get(id);
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

	@PostMapping(path = "/dlangs.html")
    public String del(ModelMap model, HttpServletRequest req) {
		Enumeration<String> pidEnum = req.getParameterNames();
		while (pidEnum.hasMoreElements()) {
			String pid = pidEnum.nextElement();
			int id = Integer.parseInt(pid, 10);
			service.remove(id);
		}
		return "redirect:../langs.html";
    }
	
    @PostMapping("/save.html")
    public String postLangs(@ModelAttribute Language lang, BindingResult bindingResult) {
        //TODO validation: TAG and NAME isNotEmpty
    	service.save(lang);
		return "redirect:../langs.html";
    }

}

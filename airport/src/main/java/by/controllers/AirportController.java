package by.controllers;

import java.text.MessageFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.app.exception.DeleteException;
import by.controllers.validators.AirportValidator;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;
import by.services.AirportService;
import by.services.LanguageService;

@Controller
@RequestMapping("/admin/airport")
public class AirportController extends AbstractEntityController {
	
	@Autowired
	private AirportService service;
	
	@Autowired
	private LanguageService langService;
	
	@Autowired
	private AirportValidator validator;
	
	private String getRedirect() {
		return "redirect:../airports.html";
	}
	
    private String getTitle() {
    	return getEnv().getProperty("admin.airport") +": ";
    }

    private String getTitle(Airport airport) {
    	if (airport.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		return getTitle() + airport.getName();
    	}
    }
    
    private String sendAirport(ModelMap model, Airport airport) {
//    	airport.setNames(null);
    	model.addAttribute("airport", airport);
    	model.addAttribute("title", getTitle(airport));
    	model.addAttribute("langs", langService.getActiveItems());
		return "admin/airport";
    }
    
    private String redirectAirport(ModelMap model) {
    	return sendAirport(model, (Airport) model.getAttribute("airport"));
    }
	
    @RequestMapping(value = "/{id}.html")
    public String getAirport(ModelMap model, @PathVariable("id") int id) {
		return sendAirport(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
    	return sendAirport(model, new Airport());
    }
	
	@ModelAttribute("names")
	public Map<Language, String> getNames(HttpServletRequest req, ModelMap model) {
		return getNames(req, model, langService);
	}
	
	@PostMapping("/save.html")
	public String saveAirport(
			@ModelAttribute("names") Map<Language, String> names,
			@ModelAttribute("airport") Airport airport, BindingResult result, 
			ModelMap model,
			HttpServletRequest req
			) {
		
		airport.setDefaultLanguage(langService.getDefaultLang());
		airport.setNames(names);
		model.addAttribute("isEmpty", req.getParameter("isEmpty"));
		validator.validate(model, result);
		if(result.hasErrors()) {
			return redirectAirport(model);
		}
		
		service.save(airport);
		return getRedirect();

		
	}
	
	@PostMapping(path = "/dairports.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			Airport airport = service.get(e.getEntityId());
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					airport.getIataCode() + "-" + airport.getName()
					);
			session.setAttribute("error", msg);
		}
		
		return getRedirect();
    }

}

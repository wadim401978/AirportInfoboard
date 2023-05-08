package by.controllers;

import java.text.MessageFormat;
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
import by.controllers.validators.AirlineValidator;
import by.dao.model.flight.Airline;
import by.services.AirlineService;

@Controller
@RequestMapping("/admin/airline")
public class AirlineController extends AbstractEntityController {
	
	@Autowired
	private AirlineService service;
	
	@Autowired
	private AirlineValidator validator;
	
	private String getRedirect() {
		return "redirect:../airlines.html";
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.airline") +": ";
    }

    private String getTitle(Airline airline) {
    	if (airline.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		return getTitle() + airline.getName();
    	}
    }
    
    private String sendAirline(ModelMap model, Airline airline) {
    	model.addAttribute("title", getTitle(airline));
    	model.addAttribute("airline", airline);
		return "admin/airline";
    }
    
    private String redirectAirline(ModelMap model) {
    	return sendAirline(model, (Airline) model.getAttribute("airline"));
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		return sendAirline(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		return sendAirline(model, new Airline());
    }
	
	@PostMapping("/save.html")
	public String saveAirport(
			@ModelAttribute("airline") Airline airline, BindingResult result, 
			ModelMap model,
			HttpServletRequest req
			) {
		
		validator.validate(airline, result);
		if(result.hasErrors()) {
			return redirectAirline(model);
		}
		return getRedirect();
	}

	
	@PostMapping(path = "/dairlines.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			Airline airline = service.get(e.getEntityId());
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					airline.getIataCode() + "-" + airline.getName()
					);
			session.setAttribute("error", msg);
		}
		
		return getRedirect();
    }

}

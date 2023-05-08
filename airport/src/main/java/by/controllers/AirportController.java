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
import by.controllers.validators.AirportValidator;
import by.dao.model.flight.Airport;
import by.services.AirportService;

@Controller
@RequestMapping("/admin/airport")
public class AirportController extends AbstractEntityController {
	
	@Autowired
	private AirportService service;
	
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
    	model.addAttribute("title", getTitle(airport));
    	model.addAttribute("airport", airport);
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
	
	//TODO
	//create a model the default language
	
	@PostMapping("/save.html")
	public String saveAirport(
			@ModelAttribute("airport") Airport airport, BindingResult result, 
			ModelMap model,
			HttpServletRequest req
			) {
		
		validator.validate(airport, result);
		if(result.hasErrors()) {
			return redirectAirport(model);
		}
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

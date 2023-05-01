package by.controllers;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.app.exception.DeleteException;
import by.dao.model.flight.Flight;
import by.services.FlightService;


@Controller
@RequestMapping("/admin/flight")
public class FlightController extends AbstractEntityController {
	
	private FlightService service;
	
    @Autowired(required = true)
	public FlightController(FlightService flightService) {
		super();
		this.service = flightService;
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.flight") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String getFlight(ModelMap model, @PathVariable("id") int id) {
		Flight flight = service.get(id);
		String title = getTitle() + flight.getIataNumber() + "|" + flight.getIcaoNumber();
    	model.addAttribute("title", title);
    	model.addAttribute("flight", flight);
		return "admin/flight";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("flight", new Flight());
		return "admin/flight";
    }
	
	@PostMapping(path = "/dflights.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			Flight flight = service.get(e.getEntityId());
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					flight.getIcaoNumber() 
					);
			session.setAttribute("error", msg);
		}
		
		return "redirect:../flights.html";
    }


}

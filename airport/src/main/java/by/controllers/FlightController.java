package by.controllers;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.Flight;
import by.services.FlightService;


@Controller
@RequestMapping("/admin/flight")
public class FlightController {
	
	private FlightService service;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public FlightController(FlightService flightService) {
		super();
		this.service = flightService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.flight") +": ";
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
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("flight", new Flight());
		return "admin/flight";
    }
	
	@PostMapping(path = "/dflights.html")
	public String deleteItems(HttpServletRequest req) {
		service.simpleRemoveItems(req);
		return "redirect:../flights.html";
    }


}

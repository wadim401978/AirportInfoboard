package by.controllers;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.Airline;
import by.services.AirlineService;


@Controller
@RequestMapping("/admin/airline")
public class AirlineController {
	
	private AirlineService airlineService;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public AirlineController(AirlineService airlineService) {
		super();
		this.airlineService = airlineService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.airline") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Airline airline = airlineService.get(id);
		String title = getTitle() + airline.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("airline", airline);
		return "admin/airline";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("airline", new Airline());
		return "admin/airline";
    }

}

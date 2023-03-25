package by.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.Airport;
import by.services.AirportService;


@Controller
@RequestMapping("/admin/airport")
public class AirportController {
	
	private AirportService airportService;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public AirportController(AirportService airportService) {
		super();
		this.airportService = airportService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.airport") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Airport airport = airportService.get(id);
		String title = getTitle() + airport.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("airport", airport);
		return "admin/airport";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("airport", new Airport());
		return "admin/airport";
    }

}

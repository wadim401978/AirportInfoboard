package by.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.services.ScheduledDepartureFlightService;


@Controller
@RequestMapping("/admin/departure")
public class DepartureController {
	
	private ScheduledDepartureFlightService departureService;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public DepartureController(ScheduledDepartureFlightService departureService) {
		super();
		this.departureService = departureService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.departure") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		ScheduledDepartureFlight departure = departureService.get(id);
		String title = getTitle() + departure.getFlight().getIataNumber() + " " + departure.getScheduledDate();
    	model.addAttribute("title", title);
    	model.addAttribute("departure", departure);
		return "admin/departure";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("departure", new ScheduledDepartureFlight());
		return "admin/departure";
    }

}

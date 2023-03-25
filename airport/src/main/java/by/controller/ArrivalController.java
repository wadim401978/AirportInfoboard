package by.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.services.ScheduledArrivalFlightService;


@Controller
@RequestMapping("/admin/arrival")
public class ArrivalController {
	
	private ScheduledArrivalFlightService arrivalService;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public ArrivalController(ScheduledArrivalFlightService arrivalService) {
		super();
		this.arrivalService = arrivalService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.arrival") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String arrival(ModelMap model, @PathVariable("id") int id) {
		ScheduledArrivalFlight arrival = arrivalService.get(id);
		String title = getTitle() + arrival.getFlight().getIataNumber() + " " + arrival.getScheduledDate();
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", arrival);
		return "admin/arrival";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", new ScheduledArrivalFlight());
		return "admin/arrival";
    }

}

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
import by.dao.model.flight.Arrival;
import by.services.ArrivalService;


@Controller
@RequestMapping("/admin/arrival")
public class ArrivalController {
	
	private ArrivalService service;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public ArrivalController(ArrivalService arrivalService) {
		super();
		this.service = arrivalService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.arrival") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String getArrival(ModelMap model, @PathVariable("id") int id) {
		Arrival arrival = service.get(id);
		String title = getTitle() + arrival.getFlight().getIataNumber() + " " + arrival.getScheduledDate();
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", arrival);
		return "admin/arrival";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", new Arrival());
		return "admin/arrival";
    }
	
	@PostMapping(path = "/darrivals.html")
	public String deleteItems(HttpServletRequest req) {
		service.simpleRemoveItems(req);
		return "redirect:../arrivals.html";
    }

}

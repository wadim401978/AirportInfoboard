package by.controllers;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.Departure;
import by.services.DepartureService;


@Controller
@RequestMapping("/admin/departure")
public class DepartureController {
	
	private DepartureService service;
	private ResourceBundle operatorResourceBundle;
	
    @Autowired(required = true)
	public DepartureController(DepartureService departureService) {
		super();
		this.service = departureService;
		this.operatorResourceBundle = ResourceBundle.getBundle("operator");
	}
    
    private String getTitle() {
    	return operatorResourceBundle.getObject("admin.departure") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String getDeparture(ModelMap model, @PathVariable("id") int id) {
		Departure departure = service.get(id);
		String title = getTitle() + departure.getFlight().getIataNumber() + " " + departure.getScheduledDate();
    	model.addAttribute("title", title);
    	model.addAttribute("departure", departure);
		return "admin/departure";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + operatorResourceBundle.getObject("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("departure", new Departure());
		return "admin/departure";
    }

	@PostMapping(path = "/ddepartures.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (Exception e) {
			session.setAttribute("error", "I can't delete ses");
		}

		return "redirect:../departures.html";
    }

}

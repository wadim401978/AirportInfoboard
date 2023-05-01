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
import by.dao.model.flight.Airline;
import by.services.AirlineService;


@Controller
@RequestMapping("/admin/airline")
public class AirlineController extends AbstractEntityController {
	
	private AirlineService service;
	
    @Autowired(required = true)
	public AirlineController(AirlineService airlineService) {
		super();
		this.service = airlineService;
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.airline") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Airline airline = service.get(id);
		String title = getTitle() + airline.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("airline", airline);
		return "admin/airline";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("airline", new Airline());
		return "admin/airline";
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
		
		return "redirect:../airlines.html";
    }


}

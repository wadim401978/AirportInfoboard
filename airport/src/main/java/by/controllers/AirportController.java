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
import by.dao.model.flight.Airport;
import by.services.AirportService;


@Controller
@RequestMapping("/admin/airport")
public class AirportController extends AbstractEntityController {
	
	private AirportService service;
	
    @Autowired(required = true)
	public AirportController(AirportService airportService) {
		super();
		this.service = airportService;
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.airport") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Airport airport = service.get(id);
		String title = getTitle() + airport.getName();
    	model.addAttribute("title", title);
    	model.addAttribute("airport", airport);
		return "admin/airport";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("airport", new Airport());
		return "admin/airport";
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
		
		return "redirect:../airports.html";
    }


}

package by.controllers;

import java.text.SimpleDateFormat;

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
import by.services.FlightService;


@Controller
@RequestMapping("/admin/departure")
public class DepartureController extends AbstractEntityController {
	
	@Autowired
	private DepartureService service;
	
	@Autowired
	private FlightService fservice;
    
    private String getTitle() {
    	return getEnv().getProperty("admin.departure") +": ";
    }
    
	private String getRedirect() {
		return "redirect:../departures.html";
	}
	
	private String getReturn() {
		return "admin/departure";
	}

	@RequestMapping(value = "/{id}.html")
    public String getDeparture(ModelMap model, @PathVariable("id") int id) {
		Departure departure = service.get(id);
		SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy");
		String title = getTitle() + 
				departure.getFlight().getIataNumber() + " " + 
				pattern.format(departure.getScheduledDate());
    	model.addAttribute("title", title);
    	model.addAttribute("departure", departure);
    	model.addAttribute("flights", fservice.getFlights(false));
		return getReturn();
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("departure", new Departure());
    	model.addAttribute("flights", fservice.getFlights(false));
		return getReturn();
    }
	
	@PostMapping("/save.html")
	public String saveDeparture(HttpServletRequest req) {
		Departure departure = service.getDeparture(req);
		service.save(departure);
		return getRedirect();
	}
	
	

	@PostMapping(path = "/ddepartures.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (Exception e) {
			session.setAttribute("error", "You can't delete record: " + e.getMessage());
		}

		return getRedirect();
    }

}

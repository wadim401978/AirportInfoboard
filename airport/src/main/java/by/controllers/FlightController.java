package by.controllers;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.app.exception.DeleteException;
import by.controllers.validators.FlightValidator;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/flight")
public class FlightController extends AbstractEntityController {
	
	@Autowired
	private FlightService service;
	
	@Autowired
	private AirlineService lservice;
	
	@Autowired
	private AirportService pservice;
	
	@Autowired
	private FlightValidator validator;
	
	private String getRedirect() {
		return "redirect:../flights.html";
	}
	
	private String getReturn() {
		return "admin/flight";
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.flight") +": ";
    }

    private String getTitle(Flight flight) {
    	if (flight.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		return getTitle() + flight.getIataNumber() + "|" + flight.getIcaoNumber();
    	}
    }

	private String sendFlight(ModelMap model, Flight flight) {
    	model.addAttribute("title", getTitle(flight));
    	model.addAttribute("flight", flight);
    	model.addAttribute("airports", pservice.getAll());
    	model.addAttribute("airlines", lservice.getAll());
		return getReturn();
	}
	
	private String redirectFlight(ModelMap model) {
		return sendFlight(model, (Flight) model.getAttribute("flight"));
	}
    
    @RequestMapping(value = "/{id}.html")
    public String getFlight(ModelMap model, @PathVariable("id") int id) {
		Flight flight = service.get(id);
    	return sendFlight(model, flight);
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		return sendFlight(model, new Flight());
    }
	
	@PostMapping(path = "/dflights.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			Flight flight = service.get(e.getEntityId());
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					flight.getIcaoNumber() 
					);
			session.setAttribute("error", msg);
		}
		
		return getRedirect();
    }
	
	@ModelAttribute("flight.airport")
	public Airport requestAirport(@RequestParam(value = "airport_id", defaultValue = "0") final int airport_id) {
		Airport airport = null;
		if(airport_id == 0) {
			airport = new Airport();
		} else {
			airport = pservice.get(airport_id);
		}
		return airport;
	}
	
	@ModelAttribute("flight.airline")
	public Airline requestAirline(@RequestParam(value = "airline_id", defaultValue = "0") final int airline_id) {
		Airline airline = null;
		if(airline_id == 0) {
			airline = new Airline();
		} else {
			airline = lservice.get(airline_id);
		}
		return airline;
	}
	
	
	@PostMapping("/save.html")
	public String saveFlight(
			@ModelAttribute("flight") Flight flight, BindingResult result, 
			ModelMap model 
			) {
		int scheduledFlightsCount;
		String oldType;
		
		flight.setAirline((Airline) model.getAttribute("flight.airline"));
		flight.setAirport((Airport) model.getAttribute("flight.airport"));
		model.addAttribute("flight", flight); 
		
		if (flight.getId() ==  0) {
			scheduledFlightsCount = 0;
			oldType = flight.getType();
		} else {
			scheduledFlightsCount = service.getScheduledFlightsCount(flight);
			oldType = service.get(flight.getId()).getType();
		}
		model.addAttribute("scheduledFlightsCount", scheduledFlightsCount);
		model.addAttribute("oldType", oldType);
		validator.validate(model, result);
		
		if (result.hasErrors()) {
			return redirectFlight(model);
		}
		
		try {
			service.save(flight); 
			return getRedirect();
		} catch (DuplicateKeyException e) {
			result.rejectValue("number", "admin.error.duplicated.field");
			return redirectFlight(model);
		}
		
		
	}
}

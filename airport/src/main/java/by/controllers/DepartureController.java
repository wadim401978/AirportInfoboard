package by.controllers;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import by.controllers.validators.DepartureValidator;
import by.dao.model.flight.Departure;
import by.dao.model.flight.DepartureStatus;
import by.dao.model.flight.Flight;
import by.services.DepartureService;
import by.services.FlightService;
import by.services.util.DateTime;

@Controller
@RequestMapping("/admin/departure")
public class DepartureController extends AbstractEntityController {
	
	private static final Logger logger = Logger.getLogger(DepartureController.class);
	
	@Autowired
	private DepartureService service;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private DepartureValidator validator;
    
    private String getTitle() {
    	return getEnv().getProperty("admin.departure") +": ";
    }
    
    private String getTitle(Departure departure) {
    	if (departure.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy");
    		return  getTitle() + 
    				departure.getFlight().getIataNumber() + " " + 
    				pattern.format(departure.getScheduledDate());
    	}
    }
    
	private String getRedirect() {
		return "redirect:../departures.html";
	}
	
	private String sendDeparture(ModelMap model, Departure departure) {
		model.addAttribute("flights", flightService.getFlights(false));
		model.addAttribute("departure", departure);
		model.addAttribute("title", getTitle(departure));
		return "admin/departure";
	}
	
	private String redirectDeparture(ModelMap model) {
		return sendDeparture(model, (Departure) model.getAttribute("departure"));
	}

	@RequestMapping(value = "/{id}.html")
    public String getDeparture(ModelMap model, @PathVariable("id") int id) {
		return sendDeparture(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(@ModelAttribute("departure.status") DepartureStatus status,
    		ModelMap model
    		) {
		Departure departure = new Departure();
    	departure.setStatus(status);
    	
		return sendDeparture(model, departure);
    }
	
	@ModelAttribute("departure.flight")
	public Flight requestFight(@RequestParam(value = "flight_id", defaultValue = "0") final int flight_id) {
		Flight flight;
		if (flight_id == 0) {
			flight = new Flight();
		} else {
			flight = flightService.get(flight_id);
		}
		return flight;
	}
	
	@ModelAttribute("departure.status")
	public DepartureStatus requestStatus(@RequestParam(value = "depStatus", defaultValue = "1") final int statusId) {
		DepartureStatus departureStatus = null;
		DepartureStatus[] statuses = DepartureStatus.values();
		for (DepartureStatus status : statuses) {
			if (status.getId() == statusId) {
				departureStatus = status;
			}
		}
		return departureStatus;
	}
	
	
	@PostMapping("/save.html")
	public String saveDeparture(
			@ModelAttribute("departure") Departure departure, BindingResult result, 
			ModelMap model,
			HttpServletRequest req
			) {
		
		departure.setFlight((Flight) model.getAttribute("departure.flight"));
		String date = req.getParameter("depScheduledDate");
		departure.setScheduledDate(DateTime.getDate(date, req.getParameter("scheduledTime")));
		departure.setStatusTime(DateTime.getDate(date, req.getParameter("depStatusTime")));
		departure.setStatus((DepartureStatus) model.getAttribute("departure.status"));
		
		validator.validate(model, result);
		if (result.hasErrors()) {
			return redirectDeparture(model);
		}
		
		try {
			service.save(departure);
			return getRedirect();
		} catch (DuplicateKeyException e) {
			result.reject("scheduledDate", getEnv().getProperty("admin.error.duplicated.field"));
			return redirectDeparture(model);
		}

	}

	@PostMapping(path = "/ddepartures.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (Exception e) {
			String error = "delete departure flight error: ";
			session.setAttribute("error", error + e.getMessage());
			logger.error(error + e.getMessage());
		}
		return getRedirect();
    }
}

package by.controllers;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import by.app.util.DateTime;
import by.controllers.validators.ArrivalValidator;
import by.dao.model.flight.Arrival;
import by.dao.model.flight.ArrivalStatus;
import by.dao.model.flight.Flight;
import by.services.ArrivalService;
import by.services.FlightService;

@Controller
@RequestMapping("/admin/arrival")
public class ArrivalController  extends AbstractEntityController {
	
	@Autowired
	private ArrivalService service;
	
	@Autowired
	private FlightService fservice;
	
	@Autowired
	private ArrivalValidator validator;
	
    private String getTitle() {
    	return getEnv().getProperty("admin.arrival") +": ";
    }
    
    private String getTitle(Arrival arrival) {
    	if (arrival.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy");
    		return getTitle() + 
    				arrival.getFlight().getIataNumber() + " - " + 
    				pattern.format(arrival.getScheduledDate());
    	}
    }
    
	private String getRedirect() {
		return "redirect:../arrivals.html";
	}
	
	private String sendArrival(ModelMap model, Arrival arrival) {
		model.addAttribute("arrival", arrival);
		model.addAttribute("flights", fservice.getFlights(true));
		model.addAttribute("title", getTitle(arrival));
		return "admin/arrival";
	}
	
	private String redirectArrival(ModelMap model) {
		return sendArrival(model, (Arrival) model.getAttribute("arrival"));
	}
	
	@RequestMapping(value = "/{id}.html")
    public String getArrival(ModelMap model, @PathVariable("id") int id) {
		return sendArrival(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(@ModelAttribute("arrival.status") ArrivalStatus status,
    		ModelMap model
    		) {
		Arrival arrival = new Arrival();
		arrival.setStatus(status);
		
		return sendArrival(model, arrival);
    }
	
	@ModelAttribute("arrival.flight")
	public Flight requestFlight(@RequestParam(value = "flight_id", defaultValue = "0") final int flight_id) {
		Flight flight;
		if (flight_id == 0) {
			flight = new Flight();
		} else {
			flight = fservice.get(flight_id);
		}
		return flight;
	}
	
	@ModelAttribute("arrival.status")
	public ArrivalStatus requestStatus(@RequestParam(value = "arrStatus", defaultValue = "1") final int statusId) {
		ArrivalStatus arrivalStatus = null;
		ArrivalStatus[] statuses = ArrivalStatus.values();
		for (ArrivalStatus status : statuses) {
			if (status.getId() == statusId) {
				arrivalStatus = status;
			}
		}
		return arrivalStatus;
	}
	
	@PostMapping("/save.html")
	public String saveArrival(
			@ModelAttribute("arrival") Arrival arrival, BindingResult result, 
			HttpServletRequest req,
			ModelMap model
			) {
		
		arrival.setFlight((Flight) model.getAttribute("arrival.flight"));
		String date = req.getParameter("arrScheduledDate");
		arrival.setScheduledDate(DateTime.getDate(date, req.getParameter("scheduledTime")));
		arrival.setStatusTime(DateTime.getDate(date, req.getParameter("arrStatusTime")));
		arrival.setStatus((ArrivalStatus) model.getAttribute("arrival.status"));
		model.addAttribute("arrival", arrival);
		
		validator.validate(model, result);
		if (result.hasErrors()) {
			return redirectArrival(model);
		}

		service.save(arrival);
		return getRedirect();
	}
	
	@PostMapping(path = "/darrivals.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (Exception e) {
			session.setAttribute("error", "I can't delete record: " + e.getMessage());
		}
		return getRedirect();
    }

}

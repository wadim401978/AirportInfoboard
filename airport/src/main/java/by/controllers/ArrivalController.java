package by.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.Arrival;
import by.services.ArrivalService;
import by.services.FlightService;


@Controller
@RequestMapping("/admin/arrival")
public class ArrivalController  extends AbstractEntityController {
	
	@Autowired
	private ArrivalService service;
	
	@Autowired
	private FlightService fservice;
	
	
//    @Autowired(required = true)
//	public ArrivalController(ArrivalService arrivalService) {
//		super();
//		this.service = arrivalService;
//	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.arrival") +": ";
    }
    
	private String getRedirect() {
		return "redirect:../arrivals.html";
	}
	
	private String getReturn() {
		return "admin/arrival";
	}

	@RequestMapping(value = "/{id}.html")
    public String getArrival(ModelMap model, @PathVariable("id") int id) {
		Arrival arrival = service.get(id);
		String title = getTitle() + arrival.getFlight().getIataNumber() + " " + arrival.getScheduledDate();
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", arrival);
    	model.addAttribute("flights", fservice.getFlights(true));
		return getReturn();
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("arrival", new Arrival());
    	model.addAttribute("flights", fservice.getFlights(true));
		return getReturn();
    }
	
	@PostMapping("/save.html")
	public String saveArrival(HttpServletRequest req) {
		Arrival arrival = service.getArrival(req);
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

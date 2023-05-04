package by.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import by.services.LanguageService;
import by.services.ArrivalService;
import by.services.DepartureService;
import by.services.Service;
import by.services.TextBlockService;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:operator.properties")
public class OperatorController extends AbstractController {
	private AirportService airportService;
	private AirlineService airlineService;
	private FlightService flightService;
	
	@Autowired
	private Environment env;
	
	@Autowired(required = true)
	public OperatorController(ArrivalService arrivalService,
			DepartureService departureService, TextBlockService textBlockService,
			LanguageService langService, AirportService airportService, AirlineService airlineService,
			FlightService flightService) {
		super(arrivalService, departureService, textBlockService, langService);
		this.airportService = airportService;
		this.airlineService = airlineService;
		this.flightService = flightService;
	}


    private <T> void setListParameters(
    		ModelMap model, Service<T> service, String title, HttpSession session) {
		model.addAttribute("items", service.getAll());
        model.addAttribute("title", env.getProperty(title));
        if (session.getAttribute("error") !=null) {
        	model.addAttribute("error", session.getAttribute("error") );
        }
        session.setAttribute("error", null);
    }

    @GetMapping(path = "/langs.html")
    public String langs(ModelMap model, HttpSession session) {
        setListParameters(model, getLangService(), "admin.languages.title", session);
        
		return "admin/langs";
    }
	
	
    @RequestMapping("/airports.html")
    public String airports(ModelMap model, HttpSession session) {
        setListParameters(model, airportService, "admin.airports.title", session);
		return "admin/airports";
    }
	
    @RequestMapping("/airlines.html")
    public String airlines(ModelMap model, HttpSession session) {
        setListParameters(model, airlineService, "admin.airlines.title", session);
		return "admin/airlines";
    }
	
    @RequestMapping("/flights.html")
    public String flights(ModelMap model, HttpSession session) {
        setListParameters(model, flightService, "admin.flights.title", session);
		return "admin/flights";
    }
	
    @RequestMapping("/arrivals.html")
    public String arrivals(ModelMap model, HttpSession session, HttpServletRequest req) {
        setListParameters(model, getArrivalService(), "admin.arrivals.title", session);
		return "admin/arrivals";
    }
	
    @RequestMapping("/departures.html")
    public String departures(ModelMap model, HttpSession session) {
        setListParameters(model, getDepartureService(), "admin.departures.title", session);
		return "admin/departures";
    }
	
    @RequestMapping("/announcments.html")
    public String announcments(ModelMap model, HttpSession session) {
        setListParameters(model, getTextBlockService(), "admin.announcments.title", session);
		return "admin/announcments";
    }
	
}

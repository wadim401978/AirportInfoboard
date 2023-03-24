package by.controller;

import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;
import by.services.Service;
import by.services.TextBlockService;

@Controller
@RequestMapping("/admin")
public class OperatorController extends AbstractController {
	private ResourceBundle operatorResourceBundle;
	private AirportService airportService;
	private AirlineService airlineService;
	private FlightService flightService;
	
	@Autowired(required = true)
	public OperatorController(ScheduledArrivalFlightService arrivalService,
			ScheduledDepartureFlightService departureService, TextBlockService textBlockService,
			LanguageService langService, AirportService airportService, AirlineService airlineService,
			FlightService flightService) {
		super(arrivalService, departureService, textBlockService, langService);
		this.airportService = airportService;
		this.airlineService = airlineService;
		this.flightService = flightService;
		this.operatorResourceBundle =getOperatorResourceBundle();
	}


	private ResourceBundle getOperatorResourceBundle() {
		if(this.operatorResourceBundle==null) {
			this.operatorResourceBundle = ResourceBundle.getBundle("operator");
		}
		return this.operatorResourceBundle;
	}

    
    private <T> void setListParameters(ModelMap model, Service<T> service, String title) {
		model.addAttribute("items", service.getAll());
        model.addAttribute("title", getOperatorResourceBundle().getObject(title));
    }

    @RequestMapping("/langs.html")
    public String langs(ModelMap model) {
        setListParameters(model, getLangService(), "admin.languages.title");
		return "admin/langs";
    }
	
    @RequestMapping("/airports.html")
    public String airports(ModelMap model) {
        setListParameters(model, airportService, "admin.airports.title");
		return "admin/airports";
    }
	
    @RequestMapping("/airlines.html")
    public String airlines(ModelMap model) {
        setListParameters(model, airlineService, "admin.airlines.title");
		return "admin/airlines";
    }
	
    @RequestMapping("/flights.html")
    public String flights(ModelMap model) {
        setListParameters(model, flightService, "admin.flights.title");
		return "admin/flights";
    }
	
    @RequestMapping("/arrivals.html")
    public String arrivals(ModelMap model) {
        setListParameters(model, getArrivalService(), "admin.arrivals.title");
		return "admin/arrivals";
    }
	
    @RequestMapping("/departures.html")
    public String departures(ModelMap model) {
        setListParameters(model, getDepartureService(), "admin.departures.title");
		return "admin/departures";
    }
	
    @RequestMapping("/announcments.html")
    public String announcments(ModelMap model) {
        setListParameters(model, getTextBlockService(), "admin.announcments.title");
		return "admin/announcments";
    }
	
	
	
}

package by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import by.services.LanguageService;

@Controller
public class ViewerController {
	
	private LanguageService langService;
	private AirportService airportService;
	private AirlineService airlineService;
	private FlightService flightService;
	
	
	
//	@Autowired(required = true)
//	public ViewerController(LanguageService langService, AirportService airportService, AirlineService airlineService) {
//		super();
//		this.langService = langService;
//		this.airportService = airportService;
//		this.airlineService = airlineService;
//	}

	
	@Autowired(required = true)
	public void setLangService(LanguageService langService) {
		this.langService = langService;
	}
	
	@Autowired(required = true)
	public void setAirportService(AirportService airportService) {
		this.airportService = airportService;
	}
	
	@Autowired(required = true)
	public void setAirlineService(AirlineService airlineService) {
		this.airlineService = airlineService;
	}
	
	@Autowired(required = true)
	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;
	}

	@RequestMapping("/arr.html")
    public String arr(ModelMap model) {
        model.addAttribute("title", "Arrivals");
        model.addAttribute("text", "Таблица прилёта");
        model.addAttribute("code", langService.getDefaultLang().toString());
        Airport airport = airportService.getAll().get(0);
        model.addAttribute("airport", airport.toString());
        
        Airline airline = airlineService.getDefaultAirline();
        model.addAttribute("airline", airline.toString());
        
        Flight flight = flightService.getByIcaoNumber("B28219");
        model.addAttribute("flightb2", (flight==null?"not found":flight.toString()  ));
        flight = flightService.getByIcaoNumber("BRU8219");
        model.addAttribute("flightbru", (flight==null?"not found":flight.toString()  ));
        
        
        return "arr";
    }

    @RequestMapping("/dep.html")
    public String dep(ModelMap model) {
        model.addAttribute("title", "Departures");
        model.addAttribute("text", "Таблица вылета");
        return "dep";
    }

    @RequestMapping("/arrdep.html")
    public String arrdep(ModelMap model) {
        model.addAttribute("title", "Arrivals-Departures");
        model.addAttribute("text", "Таблица прилёта-вылета");
        return "arrdep";
    }

    @RequestMapping("/info.html")
    public String info(ModelMap model) {
        model.addAttribute("title", "Infomsg");
        model.addAttribute("text", "Информационные объявления");
        return "info";
    }

    @RequestMapping("/index.html")
    public String index(ModelMap model) {
        model.addAttribute("title", "Airport Vitebsk!");
        model.addAttribute("text", "Аэропорт Витебск!");
        return "index";
    }

//    @RequestMapping("/")
//    public String indexRedirect(ModelMap model) {
//        return "redirect:index.html";
//    }



}

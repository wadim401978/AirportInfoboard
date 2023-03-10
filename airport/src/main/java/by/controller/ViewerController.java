package by.controller;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;

@Controller
public class ViewerController {
	
	private LanguageService langService;
	private AirportService airportService;
	private AirlineService airlineService;
	private FlightService flightService;
	private ScheduledArrivalFlightService arrivalService;
	private ScheduledDepartureFlightService departureService;
	
	
	
	@Autowired(required = true)
	public void setArrivalService(ScheduledArrivalFlightService arrivalService) {
		this.arrivalService = arrivalService;
	}
	
	@Autowired(required = true)
	public void setDepartureService(ScheduledDepartureFlightService departureService) {
		this.departureService = departureService;
	}

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
        model.addAttribute("langTag", langService.getDefaultLang().toString());
        Airport airport = airportService.getAll().get(0);
        model.addAttribute("airport", airport.toString());
        
        Airline airline = airlineService.getDefaultAirline();
        model.addAttribute("airline", airline.toString());
        
        Flight flight = flightService.getByIcaoNumber("B28218");//IATA number
        model.addAttribute("flightb2", (flight==null?"not found":flight.toString()  ));
        flight = flightService.getByIcaoNumber("BRU8218");//ICAO number
        model.addAttribute("flightbru", (flight==null?"not found":flight.toString()  ));
        
        ScheduledArrivalFlight arrival = arrivalService.getAll().get(0);
//        ScheduledArrivalFlight arrival = arrivalService.getAllByFlight(flight).get(0);
        String pattern = ResourceBundle.getBundle("viewer").getString(arrival.getStatus().property);
        model.addAttribute("arrival", arrival.toString()
        		+ MessageFormat.format(pattern, arrival.getStatusTimeFormatted()));
		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.HOUR, 11);
//		cal.set(Calendar.MINUTE, 40);
//		cal.set(Calendar.YEAR, 2023);
//		cal.set(Calendar.MONTH, 04);
//		cal.set(Calendar.DATE, 22);
//		String pattern = ResourceBundle.getBundle("viewer").getString(arrival.getStatus().property);	
//		Date date = cal.getTime();
//		model.addAttribute("arrival", MessageFormat.format(pattern, DateFormat.getTimeInstance().format(date)));
		
		
        
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

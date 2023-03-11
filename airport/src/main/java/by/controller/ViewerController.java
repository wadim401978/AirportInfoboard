package by.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.services.AirlineService;
import by.services.AirportService;
import by.services.FlightService;
import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;

@Controller
public class ViewerController {
	
	private ScheduledArrivalFlightService arrivalService;
	private ScheduledDepartureFlightService departureService;
	
//	@Autowired
//	private ApplicationContext context;
	
//	@Autowired(required = true)
//	public void setContext(ApplicationContext context) {
//		this.context = context;
//	}

	@Autowired(required = true)
	public void setArrivalService(ScheduledArrivalFlightService arrivalService) {
		this.arrivalService = arrivalService;
	}
	
	@Autowired(required = true)
	public void setDepartureService(ScheduledDepartureFlightService departureService) {
		this.departureService = departureService;
	}


	@RequestMapping("/arr.html")
    public String arr(ModelMap model) {
        model.addAttribute("title", "Arrivals");
        model.addAttribute("text", "Таблица прилёта");
        
        List<ScheduledArrivalFlight> arrivals = arrivalService.getAll();
        ScheduledArrivalFlight arrival = arrivals.get(0);
        String pattern = ResourceBundle.getBundle("viewer").getString(arrival.getStatus().property);
        model.addAttribute("arrivals", arrivals);
        model.addAttribute("arrival", arrival);
        model.addAttribute("arrivalToString", arrival.toString()
        		+ MessageFormat.format(pattern, arrivalService.getTimeFormatted(arrival.getStatusTime())));
		
        
        return "arr";
    }

    @RequestMapping("/dep.html")
    public String dep(ModelMap model) {
        model.addAttribute("title", "Departures");
        model.addAttribute("text", "Таблица вылета");
        List<ScheduledDepartureFlight> departures = departureService.getAll();
        ScheduledDepartureFlight departure = departures.get(0);
        String pattern = ResourceBundle.getBundle("viewer").getString(departure.getStatus().property);
        model.addAttribute("departures", departures);
        model.addAttribute("departure", departure);
        model.addAttribute("departureToString", departure.toString()
        		+ MessageFormat.format(pattern, departureService.getTimeFormatted(departure.getStatusTime())));
        
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

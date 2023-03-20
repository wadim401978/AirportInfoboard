package by.controller;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.dao.model.common.Language;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;
import by.services.TextBlockService;

@Controller
public class ViewerController {
	
	private ScheduledArrivalFlightService arrivalService;
	private ScheduledDepartureFlightService departureService;
	private TextBlockService textBlockService;
	private LanguageService langService;
	private ResourceBundle initialResourceBundle;

	private ResourceBundle getInitialResourceBundle() {
		if(this.initialResourceBundle==null) {
			this.initialResourceBundle = ResourceBundle.getBundle("initial");
		}
		return this.initialResourceBundle;
	}

	@Autowired(required = true)
	public void setLangService(LanguageService langService) {
		this.langService = langService;
	}
	@Autowired(required = true)
	public void setTextBlockService(TextBlockService textBlockService) {
		this.textBlockService = textBlockService;
	}

	@Autowired(required = true)
	public void setArrivalService(ScheduledArrivalFlightService arrivalService) {
		this.arrivalService = arrivalService;
	}
	
	@Autowired(required = true)
	public void setDepartureService(ScheduledDepartureFlightService departureService) {
		this.departureService = departureService;
	}
	
    private void setGetRequestModelAttributes(ModelMap model, String timeOutSource) {
    	this.initialResourceBundle = getInitialResourceBundle();
    	model.addAttribute("lang", langService.getDefaultLang());
    	model.addAttribute("timeOutSource", timeOutSource);
    	model.addAttribute("timeOutValue", initialResourceBundle.getString("timeout"));
    }
    
    private void setPostRequestModelAttributes(ModelMap model, HttpServletRequest req) {
    	int langId = Integer.parseInt(req.getParameter("langid"));
    	Language lang = langService.get(++langId);
    	if(lang==null) {
    		lang = langService.get(1);
    	} 
    	model.addAttribute("lang", lang);
    }
    
    
	@RequestMapping(value = "/arr.html", method = RequestMethod.GET)
    public String arrival(ModelMap model) {
		List<ScheduledArrivalFlight> arrivals = arrivalService.getAll();
		model.addAttribute("date", arrivalService.getDateFormatted(new Date()));
        model.addAttribute("arrivals", arrivals);
        setGetRequestModelAttributes(model, "arr.html");
        model.addAttribute("emptyRows", getEmptyRowsNumber(arrivals.size()));
        return "arr";
    }
	

	@RequestMapping(value = "/arr.html", method = RequestMethod.POST)
    public String arrivalPost(ModelMap model, HttpServletRequest req) {
		arrival(model);
        setPostRequestModelAttributes(model, req);
        return "arr";
    }
	
	private int getEmptyRowsNumber(int listSize) {
		int rowsNumber = Integer.parseInt(getInitialResourceBundle().getString("rows.number"));
        int emptyRows = 0;
        if (rowsNumber >= listSize) {
        	emptyRows = rowsNumber - listSize;
        }	
        return emptyRows;
	}

    @RequestMapping(value = "/dep.html", method = RequestMethod.GET)
    public String departure(ModelMap model) {
    	List<ScheduledDepartureFlight> departures = departureService.getAll();
    	model.addAttribute("departures", departures);
        model.addAttribute("date", departureService.getDateFormatted(new Date()));
        setGetRequestModelAttributes(model, "dep.html");
        model.addAttribute("emptyRows", getEmptyRowsNumber(departures.size()));
        return "dep";
    }

    @RequestMapping(value = "/dep.html", method = RequestMethod.POST)
    public String departurePost(ModelMap model, HttpServletRequest req) {
    	departure(model);
        setPostRequestModelAttributes(model, req);
        return "dep";
    }

    @RequestMapping(value = "/arrdep.html", method = RequestMethod.GET)
    public String arrdep(ModelMap model) {
        model.addAttribute("text", "Under construction");
        setGetRequestModelAttributes(model, "arrdep.html");
        return "arrdep";
    }
    
    @RequestMapping(value = "/arrdep.html", method = RequestMethod.POST)
    public String arrdeppost(ModelMap model, HttpServletRequest req) {
    	arrdep(model);
    	setPostRequestModelAttributes(model, req);
        return "arrdep";
    }
    
    

    @RequestMapping("/info.html")
    public String info(ModelMap model) {
        model.addAttribute("title", "Информационные объявления");
        model.addAttribute("text", "under construction");
        model.addAttribute("blocks", textBlockService.getAll());
        return "info";
    }

    @RequestMapping("/index.html")
    public String index(ModelMap model) {
        return "index";
    }

//    @RequestMapping("/")
//    public String indexRedirect(ModelMap model) {
//        return "redirect:index.html";
//    }
    

}

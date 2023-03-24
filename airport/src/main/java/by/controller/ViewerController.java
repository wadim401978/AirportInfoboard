package by.controller;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.dao.model.infomsg.TextBlock;
import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;
import by.services.TextBlockService;

@Controller
public class ViewerController extends AbstractController {
	
	private ResourceBundle initialResourceBundle;

	private ResourceBundle getInitialResourceBundle() {
		if(this.initialResourceBundle==null) {
			this.initialResourceBundle = ResourceBundle.getBundle("initial");
		}
		return this.initialResourceBundle;
	}


	@Autowired(required = true)
	public ViewerController(ScheduledArrivalFlightService arrivalService,
			ScheduledDepartureFlightService departureService, TextBlockService textBlockService,
			LanguageService langService) {
		super(arrivalService, departureService, textBlockService, langService);
		this.initialResourceBundle = getInitialResourceBundle();
	}



	private void setGetRequestModelAttributes(ModelMap model, String timeOutSource) {
    	this.initialResourceBundle = getInitialResourceBundle();
    	TextBlockService textBlockService = getTextBlockService();
    	LanguageService langService = getLangService();
    	model.addAttribute("lang", langService.getDefaultLang());
    	model.addAttribute("langCount", langService.getActiveLanguages().size());
    	Gson json = new Gson();
    	String str = json.toJson(langService.getIds(langService.getActiveLanguages()));
    	model.addAttribute("activeLangs", str);
    	
    	List<TextBlock> tbList = textBlockService.getActiveBlocks();
    	if(tbList.isEmpty()) {
    		str = json.toJson(tbList.toArray());
    	} else {
    		str = json.toJson(textBlockService.getIds(tbList));
    	}
    	model.addAttribute("announcments", str);
    	
    	model.addAttribute("timeOutSource", timeOutSource);
    	model.addAttribute("timeOutValue", initialResourceBundle.getString("timeout"));
    }
    
    
	private void setPostRequestModelAttributes(ModelMap model, String sLangId) {
    	int langId = Integer.parseInt(sLangId);
    	model.addAttribute("lang", getLangService().getNextActiveLanguage(langId));
    }
    
    
	@GetMapping("/arr.html")
    public String arrival(ModelMap model) {
		ScheduledArrivalFlightService arrivalService = getArrivalService();
		List<ScheduledArrivalFlight> arrivals = arrivalService.getAll();
		Date date = new Date();
		model.addAttribute("date", arrivalService.getDateFormatted(date));
		model.addAttribute("time", arrivalService.getTimeFormattedSec(date));
        model.addAttribute("arrivals", arrivals);
        setGetRequestModelAttributes(model, "arr.html");
        model.addAttribute("emptyRows", getEmptyRowsNumber(arrivals.size()));
        return "arr";
    }
	
	
	@PostMapping("/arr.html")
    public String arrivalPost(ModelMap model, @RequestParam(value = "langid") String langId) {
		arrival(model);
		setPostRequestModelAttributes(model, langId);
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

	@GetMapping("/dep.html")
    public String departure(ModelMap model) {
    	ScheduledDepartureFlightService departureService = getDepartureService();
    	List<ScheduledDepartureFlight> departures = departureService.getAll();
    	model.addAttribute("departures", departures);
    	Date date = new Date();
        model.addAttribute("date", departureService.getDateFormatted(date));
        model.addAttribute("time", departureService.getTimeFormattedSec(date));
        setGetRequestModelAttributes(model, "dep.html");
        model.addAttribute("emptyRows", getEmptyRowsNumber(departures.size()));
        return "dep";
    }

	@PostMapping("/dep.html")
    public String departurePost(ModelMap model, @RequestParam(value = "langid") String langId) {
    	departure(model);
        setPostRequestModelAttributes(model, langId);
        return "dep";
    }

    @GetMapping("/arrdep.html")
    public String arrdep(ModelMap model) {
        model.addAttribute("text", "Under construction");
        setGetRequestModelAttributes(model, "arrdep.html");
        return "arrdep";
    }
    
    @PostMapping("/arrdep.html")
    public String arrdeppost(ModelMap model, @RequestParam(value = "langid") String langId)  {
    	arrdep(model);
    	setPostRequestModelAttributes(model, langId);
        return "arrdep";
    }

    @RequestMapping(value = "/info.html", method = RequestMethod.GET)
    public String info(ModelMap model) {
    	setGetRequestModelAttributes(model, "info.html");
        model.addAttribute("block", getTextBlockService().get(0));
        return "info";
    }

    @PostMapping("/info.html")
    public String infoPost(ModelMap model, @RequestParam(value = "blockid") String blockid) {
    	info(model);
    	int blockId = Integer.parseInt(blockid);
    	model.addAttribute("block",getTextBlockService().getNextActiveBlock(blockId));
        return "info";
    }

    @RequestMapping("/index.html")
    public String index(ModelMap model) {
        return "index";
    }
    
    @RequestMapping("/admin.html")
    public String admin(ModelMap model) {
        model.addAttribute("title", getInitialResourceBundle().getObject("admin.board"));
    	return "admin";
    }

}

package by.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import by.dao.model.flight.Arrival;
import by.dao.model.flight.Departure;
import by.services.LanguageService;
import by.services.ArrivalService;
import by.services.DepartureService;
import by.services.TextBlockService;

@Controller
@PropertySource("classpath:initial.properties")
public class ViewerController extends AbstractController {
	
	@Autowired
	private Environment env;

	@Autowired(required = true)
	public ViewerController(ArrivalService arrivalService,
			DepartureService departureService, TextBlockService textBlockService,
			LanguageService langService) {
		super(arrivalService, departureService, textBlockService, langService);
	}

	private void setGetRequestModelAttributes(ModelMap model, String timeOutSource) {
    	LanguageService langService = getLangService();
    	model.addAttribute("lang", langService.getDefaultLang());
    	model.addAttribute("langCount", langService.getActiveItems().size());
    	model.addAttribute("timeOutSource", timeOutSource);
    	model.addAttribute("timeOutValue", env.getProperty("timeout"));
    }
    
	private void setPostRequestModelAttributes(ModelMap model, String sLangId) {
    	int langId = Integer.parseInt(sLangId);
    	model.addAttribute("lang", getLangService().getNextActiveItem(langId));
    }
    
	@GetMapping("/arr.html")
    public String arrival(ModelMap model) {
		ArrivalService arrivalService = getArrivalService();
		List<Arrival> arrivals = arrivalService.getAll();
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
		int rowsNumber = Integer.parseInt(env.getProperty("rows.number"));
        int emptyRows = 0;
        if (rowsNumber >= listSize) {
        	emptyRows = rowsNumber - listSize;
        }	
        return emptyRows;
	}

	@GetMapping("/dep.html")
    public String departure(ModelMap model) {
    	DepartureService departureService = getDepartureService();
    	List<Departure> departures = departureService.getAll();
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
        String htmlField = null;
        int activeAnnotationsNum = getTextBlockService().getActiveBlocks().size();
        if (activeAnnotationsNum > 0) {
        	htmlField = getTextBlockService().getActiveBlocks().get(0).getHtml();
        }
        model.addAttribute("activeAnnotationsNum", activeAnnotationsNum);
        model.addAttribute("htmlField", htmlField);
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
        model.addAttribute("block", getTextBlockService().getActiveBlocks().get(0));
        return "info";
    }

    @PostMapping("/info.html")
    public String infoPost(ModelMap model, @RequestParam(value = "blockid") String blockid) {
    	info(model);
    	int blockId = Integer.parseInt(blockid);
    	model.addAttribute("block",getTextBlockService().getNextActiveBlock(blockId));
        return "info";
    }
    
    @RequestMapping(value = "/info/{id}.html", method = RequestMethod.GET)
    public String infoId(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("block", getTextBlockService().get(id));
        return "info";
    }



    @RequestMapping("/index.html")
    public String index(ModelMap model) {
        return "index";
    }
    
    @RequestMapping("/admin.html")
    public String admin(ModelMap model) {
        model.addAttribute("title", env.getProperty("admin.board"));
    	return "admin";
    }

}

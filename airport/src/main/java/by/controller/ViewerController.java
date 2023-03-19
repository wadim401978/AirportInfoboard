package by.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.dao.model.common.Language;
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


	@RequestMapping("/arr.html")
    public String arr(ModelMap model) {
		model.addAttribute("lang", langService.getLangByTag("ru"));
        model.addAttribute("date", arrivalService.getDateFormatted(new Date()));
        model.addAttribute("arrivals", arrivalService.getAll());
        return "arr";
    }

    @RequestMapping("/dep.html")
    public String dep(ModelMap model) {
    	model.addAttribute("lang", langService.getLangByTag("en"));
        model.addAttribute("departures", departureService.getAll());
        model.addAttribute("date", departureService.getDateFormatted(new Date()));
        return "dep";
    }

    @RequestMapping(value = "/arrdep.html", method = RequestMethod.GET)
    public String arrdep(ModelMap model) {
    	model.addAttribute("lang", langService.getLangByTag("ru"));
        model.addAttribute("text", "Under construction");
        return "arrdep";
    }
    
    @RequestMapping(value = "/arrdep.html", method = RequestMethod.POST)
    public String arrdeppost(ModelMap model, HttpServletRequest req) {
    	model.addAttribute("lang", langService.getLangByTag("ru"));
    	int langId = Integer.parseInt(req.getParameter("langid"));
    	Language lang = langService.get(++langId);
    	if(lang==null) {
    		lang = langService.get(1);
    	} 
    	model.addAttribute("lang", lang);
        model.addAttribute("text", "Under construction");
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

package by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import by.services.AirportService;
import by.services.LanguageService;

@Controller
public class ViewerController {
	
	private LanguageService langService;
	private AirportService airportService;
	
	@Autowired(required = true)
	public ViewerController(LanguageService langService, AirportService airportService) {
		super();
		this.langService = langService;
		this.airportService = airportService;
	}

//	@Autowired(required = true)
//    public void setLangService(LanguageService langService) {
//		this.langService = langService;
//	}

	@RequestMapping("/arr.html")
    public String arr(ModelMap model) {
        model.addAttribute("title", "Arrivals");
        model.addAttribute("text", "Таблица прилёта");
        model.addAttribute("code", langService.getDefaultLang().toString());
        model.addAttribute("airport", airportService.getAll().get(0).toString());
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

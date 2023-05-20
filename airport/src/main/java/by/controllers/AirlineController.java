package by.controllers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import by.app.exception.DeleteException;
import by.controllers.validators.AirlineValidator;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.services.AirlineService;
import by.services.LanguageService;
import by.services.util.Images;

@Controller
@RequestMapping("/admin/airline")
public class AirlineController extends AbstractEntityController {
	
	private static final Logger logger = Logger.getLogger(AirlineController.class);
	
	@Autowired
	private AirlineService service;
	
	@Autowired
	private LanguageService langService;
	
	@Autowired
	private AirlineValidator validator;
	
	private String getRedirect() {
		return "redirect:../airlines.html";
	}
    
    private String getTitle() {
    	return getEnv().getProperty("admin.airline") +": ";
    }

    private String getTitle(Airline airline) {
    	if (airline.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		return getTitle() + airline.getName();
    	}
    }
    
    private String sendAirline(ModelMap model, Airline airline) {
    	model.addAttribute("title", getTitle(airline));
    	model.addAttribute("airline", airline);
    	model.addAttribute("langs", langService.getActiveItems());
		return "admin/airline";
    }
    
    private String redirectAirline(ModelMap model) {
    	return sendAirline(model, (Airline) model.getAttribute("airline"));
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		Airline airline = service.get(id);
		String logoPath = airline.getLogo();
		if (logoPath!=null && !logoPath.trim().equals("")) {
			if(!Images.isExists(logoPath)) {
				airline.setLogo(null);
			}
		}
		return sendAirline(model, airline);
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		return sendAirline(model, new Airline());
    }
	
	@ModelAttribute("names")
	public Map<Language, String> getNames(HttpServletRequest req, ModelMap model) {
		return getNames(req, model, langService);
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String saveAirport(
			@ModelAttribute("names") Map<Language, String> names,
			@ModelAttribute("airline") Airline airline, BindingResult result, 
			@RequestParam("logoFile") MultipartFile file,
			ModelMap model,
			HttpServletRequest req
			) {
		airline.setNames(names);
		airline.setDefaultLanguage(langService.getDefaultLang());
		model.addAttribute("isEmpty", req.getParameter("isEmpty")); 
		validator.validate(model, result);
		if(result.hasErrors()) {
			return redirectAirline(model);
		}
		
		try {
			service.saveWithUpload(airline, file);
		} catch (IIOException e) {
			result.reject("logo", getEnv().getProperty("admin.error.not.image"));
			return redirectAirline(model);
		} catch (IOException e) {
			result.reject("logo", getEnv().getProperty("admin.error.image"));
			return redirectAirline(model);
		}
		
		model.remove("isEmpty");
		return getRedirect();
	}

	
	@PostMapping(path = "/dairlines.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (DeleteException e) {
			Airline airline = service.get(e.getEntityId());
			String msg = MessageFormat.format(
					getEnv().getProperty(e.getMessage()), 
					airline.getIataCode() + "-" + airline.getName()
					);
			session.setAttribute("error", msg);
			logger.info(msg);
		}
		
		return getRedirect();
    }

}

package by.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.dao.model.infomsg.TextBlock;
import by.services.TextBlockService;


@Controller
@RequestMapping("/admin/announcment")
public class AnnouncmentController extends AbstractEntityController {
	
	private TextBlockService announcmentService;
	
    @Autowired(required = true)
	public AnnouncmentController(TextBlockService announcmentService) {
		super();
		this.announcmentService = announcmentService;
	}
    
	private String getRedirect() {
		return "redirect:../announcments.html";
	}

    
    private String getTitle() {
    	return getEnv().getProperty("admin.announcment") +": ";
    }
    
    private String getTitle(TextBlock announcment) {
    	if (announcment.getId() == 0) {
    		return getTitle() + getEnv().getProperty("admin.new.title");
    	} else {
    		return getTitle() + announcment.getId();
    	}
    }
    
    private String sendAnnouncment(ModelMap model, TextBlock announcment) {
    	model.addAttribute("title", getTitle(announcment));
    	model.addAttribute("announcment", announcment);
		return "admin/announcment";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		return sendAnnouncment(model, announcmentService.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		return sendAnnouncment(model, new TextBlock());
    }
	
	@PostMapping("/save.html")
	public String saveAirport(
//			@ModelAttribute("airline") Airline airline, BindingResult result, 
			ModelMap model,
			HttpServletRequest req
			) {
		//TODO
		return getRedirect();
	}
	
	@PostMapping(path = "/dannouncments.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		//TODO
		return getRedirect();
	}



}

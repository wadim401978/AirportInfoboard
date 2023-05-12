package by.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.controllers.validators.TextAnnouncmentValidator;
import by.dao.model.infomsg.TextBlock;
import by.services.TextBlockService;


@Controller
@RequestMapping("/admin/announcment")
public class AnnouncmentController extends AbstractEntityController {
	
	@Autowired
	private TextBlockService service;
	
	@Autowired
	private TextAnnouncmentValidator validator;
	
//    @Autowired(required = true)
//	public AnnouncmentController(TextBlockService announcmentService) {
//		super();
//		this.service = announcmentService;
//	}
    
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
    
    private String redirectAnnouncment(ModelMap model) {
    	return sendAnnouncment(model, (TextBlock) model.getAttribute("announcment"));
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		return sendAnnouncment(model, service.get(id));
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		return sendAnnouncment(model, new TextBlock());
    }
	
	@PostMapping("/save.html")
	public String saveAirport(@ModelAttribute("announcment") TextBlock  announcment, 
			BindingResult result,
			ModelMap model,
			HttpServletRequest req
			) {
		validator.validate(announcment, result);
    	if (result.hasErrors()) {
    		model.addAttribute("announcment", announcment);
    		return redirectAnnouncment(model);
    	}
		
		service.save(announcment);
		return getRedirect();
	}
	
	@PostMapping(path = "/dannouncments.html")
	public String deleteItems(HttpServletRequest req, HttpSession session) {
		try {
			service.simpleRemoveItems(req);
		} catch (Exception e) {
			session.setAttribute("error", "You can't delete record: " + e.getMessage());
		}
		return getRedirect();
	}

}

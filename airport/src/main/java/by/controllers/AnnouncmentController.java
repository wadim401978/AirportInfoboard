package by.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    private String getTitle() {
    	return getEnv().getProperty("admin.announcment") +": ";
    }

	@RequestMapping(value = "/{id}.html")
    public String lang(ModelMap model, @PathVariable("id") int id) {
		TextBlock announcment = announcmentService.get(id);
		String title = getTitle() + announcment.getId();
    	model.addAttribute("title", title);
    	model.addAttribute("announcment", announcment);
		return "admin/announcment";
    }
    
	@GetMapping(path = "/add.html")
    public String add(ModelMap model) {
		String title = getTitle() + getEnv().getProperty("admin.new.title");
    	model.addAttribute("title", title);
    	model.addAttribute("announcment", new TextBlock());
		return "admin/announcment";
    }

}

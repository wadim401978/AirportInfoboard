package by.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import by.dao.model.infomsg.TextBlock;
import by.services.LanguageService;
import by.services.TextBlockService;

@RestController
@PropertySource("classpath:initial.properties")
public class RestServiceController {

	@Autowired
	private TextBlockService blockService;
	
	@Autowired 
	private LanguageService langService;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(method = RequestMethod.GET, value = "blocks")
	public List<TextBlock> getAnnouncments() {
		return blockService.getActiveBlocks();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "blocksIds")
	public int[] getBlockIds() {
		return blockService.getIds(getAnnouncments());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "langsIds")
	public int[] getLangIds() {
		return langService.getIds(langService.getActiveItems());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "deflangid")
	public int getDefaultLangId() {
		return langService.getDefaultLang().getId();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "timeout")
	public int getTimeOut() {
		int timeOut;
		try {
			timeOut = Integer.parseInt(env.getProperty("timeout"));
		} catch (Exception e) {
			timeOut = 1000;
		}
		return timeOut;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "tb{id}", produces = "text/plain;charset=UTF-8")
	public String getAnnouncmentHtml(@PathVariable("id") int id) {
		String announcmentHtml = null;
		try {
			return blockService.get(id).getHtml();
		} catch (Exception e) {
			return announcmentHtml;
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "nextlang{id}")
	public int getNextActiveLangId(@PathVariable("id") int id) {
		return langService.getNextActiveItem(id).getId();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "nexttb{id}")
	public int getNextActiveAnnouncmentId(@PathVariable("id") int id) {
		TextBlock tb = blockService.getNextActiveBlock(id);
		if (tb==null) {
			tb = new TextBlock();
			tb.setId(0);
		}
		return tb.getId();
	}
	
}

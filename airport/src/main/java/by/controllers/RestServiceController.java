package by.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import by.dao.model.infomsg.TextBlock;
import by.services.LanguageService;
import by.services.TextBlockService;

@RestController
public class RestServiceController {

	@Autowired
	private TextBlockService blockService;
	
	@Autowired LanguageService langService;
	
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
		return langService.getIds(langService.getActiveLanguages());
	}
	
}

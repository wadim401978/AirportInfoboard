package by.controllers;

import by.services.LanguageService;
import by.services.ArrivalService;
import by.services.DepartureService;
import by.services.TextBlockService;

public abstract class AbstractController {
	private ArrivalService arrivalService;
	private DepartureService departureService;
	private TextBlockService textBlockService;
	private LanguageService langService;
	
	public AbstractController(ArrivalService arrivalService,
			DepartureService departureService, TextBlockService textBlockService,
			LanguageService langService) {
		super();
		this.arrivalService = arrivalService;
		this.departureService = departureService;
		this.textBlockService = textBlockService;
		this.langService = langService;
	}

	public ArrivalService getArrivalService() {
		return arrivalService;
	}

	public DepartureService getDepartureService() {
		return departureService;
	}

	public TextBlockService getTextBlockService() {
		return textBlockService;
	}

	public LanguageService getLangService() {
		return langService;
	}
	
	

}

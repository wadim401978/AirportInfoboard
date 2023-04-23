package by.controllers;

import by.services.LanguageService;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;
import by.services.TextBlockService;

public abstract class AbstractController {
	private ScheduledArrivalFlightService arrivalService;
	private ScheduledDepartureFlightService departureService;
	private TextBlockService textBlockService;
	private LanguageService langService;
	
	public AbstractController(ScheduledArrivalFlightService arrivalService,
			ScheduledDepartureFlightService departureService, TextBlockService textBlockService,
			LanguageService langService) {
		super();
		this.arrivalService = arrivalService;
		this.departureService = departureService;
		this.textBlockService = textBlockService;
		this.langService = langService;
	}

	public ScheduledArrivalFlightService getArrivalService() {
		return arrivalService;
	}

	public ScheduledDepartureFlightService getDepartureService() {
		return departureService;
	}

	public TextBlockService getTextBlockService() {
		return textBlockService;
	}

	public LanguageService getLangService() {
		return langService;
	}
	
	

}

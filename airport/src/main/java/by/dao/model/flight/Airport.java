package by.dao.model.flight;

import java.util.Map;
import by.dao.model.common.Language;

public class Airport extends AirEntity{

	public Airport(int id, String iataCode, String icaoCode, Map<Language, String> names, Language defaultLanguage) {
		super(id, iataCode, icaoCode, names, defaultLanguage);
	}
	
	public Airport() {
		
	}
}

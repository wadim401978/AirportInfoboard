package by.dao.model.flight;

import java.util.Map;
import by.dao.model.common.Language;

public class Airline extends AirEntity{
    private String logo;

	public Airline(int id, String iataCode, String icaoCode, Map<Language, String> names, Language defaultLanguage, String logo) {
		super(id, iataCode, icaoCode, names, defaultLanguage);
		this.logo = logo;
	}
	
	public Airline() {
		
	}

	public String getLogo() {
        return logo;
    }

	@Override
	public String toString() {
		return super.toString() + " (" + getLogo() + ")";
	}
}

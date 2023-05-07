package by.dao.model.flight;

import java.util.Map;
import by.dao.model.common.Language;

public class Airline extends AirEntity{
	private String logo;   
	
	public Airline() {
		super();
	}

	public Airline(int id) {
		super(id);
	}

	public Airline(int id, String iataCode, String icaoCode, Map<Language, String> names, Language defaultLanguage) {
		super(id, iataCode, icaoCode, names, defaultLanguage);
	}


	public Airline(int id, String iataCode, String icaoCode, Map<Language, String> names, Language defaultLanguage, String logo) {
		super(id, iataCode, icaoCode, names, defaultLanguage);
		this.logo = logo;
	}
	
	
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
        return logo;
    }

	@Override
	public String toString() {
		return super.toString() + " (" + getLogo() + ")";
	}
}

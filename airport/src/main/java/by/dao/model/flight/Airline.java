package by.dao.model.flight;

import java.util.Map;
import by.dao.model.AirEntity;
import by.dao.model.common.Language;

public class Airline extends AirEntity{
    private String logo;

    public Airline() {
		super();
	}

	public Airline(int id, String iataCode, String icaoCode, Map<Language, String> names, String logo) {
		super(id, iataCode, icaoCode, names);
		this.logo = logo;
	}

	public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
	@Override
	public String toString() {
		return super.toString() + " (" + getLogo() + ")";
	}
}

package by.dao.model.flight;

import java.util.Map;

import by.dao.model.AirEntity;
import by.dao.model.common.Language;

public class Airport extends AirEntity{

	public Airport() {
		super();
	}

	public Airport(int id, String iataCode, String icaoCode, Map<Language, String> names) {
		super(id, iataCode, icaoCode, names);
	}
	
	@Override
	public String toString() {
		return getId()+ ") " + getIataCode() + "/" + getIcaoCode() + " | " + hashCode();
	}

	
	

}

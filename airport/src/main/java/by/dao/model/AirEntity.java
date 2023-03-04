package by.dao.model;

import java.util.Map;

import by.dao.model.common.Language;

public abstract class AirEntity extends Entity {
    private String iataCode;
    private String icaoCode;
    private Map<Language, String> names;
    
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getIcaoCode() {
		return icaoCode;
	}
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}
	public Map<Language, String> getNames() {
		return names;
	}
	public void setNames(Map<Language, String> names) {
		this.names = names;
	}
    
    

}

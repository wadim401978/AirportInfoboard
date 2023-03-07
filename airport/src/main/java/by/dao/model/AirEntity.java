package by.dao.model;

import java.util.Map;

import by.dao.model.common.Language;

public abstract class AirEntity extends Entity {
    private Map<Language, String> names;
    private String iataCode;
    private String icaoCode;

    
	public AirEntity() {
		super();
	}
	
	
	public AirEntity(int id, String iataCode, String icaoCode, Map<Language, String> names) {
		super(id);
		this.names = names;
		this.iataCode = iataCode;
		this.icaoCode = icaoCode;
	}


	public Map<Language, String> getNames() {
		return names;
	}
	public void setNames(Map<Language, String> names) {
		this.names = names;
	}
	
	

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

	@Override
	public String toString() {
		Map<Language, String> map = this.getNames();
		String names = null;
		if (map==null||map.isEmpty()) {
			names = "[name is empty]";
		} else {
			names = "/";
			for (Map.Entry<Language, String> entry : map.entrySet()) {
				names += (entry.getValue() + "/");
			}
		}
		return "(id = " + getId()+ ") " + this.getClass().getSimpleName() + " " + names + " " + getIataCode() + "/" + getIcaoCode() + " | " + hashCode() + " ";
	}
    
	

}

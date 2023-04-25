package by.dao.model.flight;

import java.util.Map;
import by.dao.model.Entity;
import by.dao.model.common.Language;

public abstract class AirEntity extends Entity {
    public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}


	private Map<Language, String> names;
    private String iataCode;
    private String icaoCode;
    private Language defaultLanguage;

    
	public AirEntity(int id, String iataCode, String icaoCode, Map<Language, String> names, Language defaultLanguage) {
		super(id);
		this.names = names;
		this.iataCode = iataCode;
		this.icaoCode = icaoCode;
		this.defaultLanguage = defaultLanguage;
	}
	public AirEntity() {
		
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

	public String getIcaoCode() {
		return icaoCode;
	}

	private String getFirstName() {
		String name = "";
		Map<Language, String> map = this.getNames();
		for (Map.Entry<Language, String> entry : map.entrySet()) {
			name = entry.getValue();
			break;
		}
		return name;
	}
	
	public String getName() {
		String name = getNames().get(this.defaultLanguage);
		return name == null ? this.getFirstName() : name;
	}
	
	public String getName(Language lang) {
		String name = getNames().get(lang);
		return name == null ? getName() : name;
	}
	
	private String toStringNames() {
		Map<Language, String> map = this.getNames();
		String names = null;
		if (map==null||map.isEmpty()) {
			names = "[name is empty]";
		} else {
			names = "=";
			for (Map.Entry<Language, String> entry : map.entrySet()) {
				names += (entry.getValue() + "=");
			}
		}
		return names;
	}
	
	
	@Override
	public String toString() {
		return super.toString() +  this.toStringNames() + " " + getIataCode() + "/" + getIcaoCode() + " ";
	}
    
	

}

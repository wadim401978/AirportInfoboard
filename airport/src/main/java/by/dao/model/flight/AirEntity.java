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

	
	
	
	public AirEntity(int id) {
		super(id);
	}
	
	@Override
	public String getPresentation() {
		String result = getName();
		if (getIataCode()!=null && getIcaoCode()!=null) {
			result += " (" + getIataCode() + "/" + getIcaoCode() + ")";
		} else {
			String icao = getIataCode();
			String iata = getIataCode();
			if (icao!=null) {
				result += " " + icao;
			}
			if (iata!=null) {
				result += " " + iata;
			}
		}
		
		if (result.equals("")) {
			result = "---";
		}
		return result;
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
		Map<Language, String> lnames = getNames();
		
		if (lnames == null) {
			return "";
		} else {
			String name = lnames.get(this.defaultLanguage);
			return name == null ? this.getFirstName() : name;
		}
	}
	
	public String getName(Language lang) {
		String name = getNames().get(lang);
		return name == null ? getName() : name;
	}
	
	public void setDefaultLanguageByTag(String tag) {
		Map<Language, String> map = this.getNames();
		for (Map.Entry<Language, String> entry : map.entrySet()) {
			if (entry.getValue().equals(tag)) {
				this.defaultLanguage = entry.getKey();
			};
		}
	}
	
	public Language getDefaultLanguage() {
		return defaultLanguage;
	}
	public void setDefaultLanguage(Language defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
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

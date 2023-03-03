package by.model.flight;

import by.model.common.Language;

import java.util.Map;

public class Airport {
    private int id;
    private String iataCode;
    private String icaoCode;
    private Map<Language, String> names;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Map<Language, String> getNames() {
        return names;
    }

    public void setNames(Map<Language, String> names) {
        this.names = names;
    }

    public String getNameByLang(Language lang) {
        return this.names.get(lang);
    }

    public void setNameByLang(String name, Language lang) {
        this.names.put(lang, name);
    }
}

package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import by.app.util.DateTime;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledFlight;

public class ExtractAssistant {
	
	private Language language;
	private Map<String, Language> languages = new HashMap<>();
	private String defaultLangTag;
	
	private Flight flight;
	private Airline airline;
	private Airport airport;
	
	private Map<Language, String> airlineNames;
	private Map<String, Airport> airportMap = new HashMap<>();
	private Map<Language, String> airportNames;
	
	public String getDefaultLangTag() {
		return defaultLangTag;
	}

	public void setDefaultLangTag(String defaultLangTag) {
		this.defaultLangTag = defaultLangTag;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguageByTag(String tag) {
		Language lang = languages.get(tag);
		if (lang == null) {
			lang = new Language();
			this.languages.put(tag, this.language);
		}
		setLanguage(lang);
		return this.language;
	}
	
	public void setAirportByIcao(String icao) {
		setAirport(this.airportMap.get(icao));
		if (getAirport() == null) {
			setAirport(new Airport());
			this.airportMap.put(icao, getAirport());
		}
	}

	
	public Map<Language, String> getAirlineNames(String name) {
		this.airlineNames = getAirline().getNames();
		if (this.airlineNames == null) {
			this.airlineNames = new HashMap<Language, String>();
		}
		this.airlineNames.put(getLanguage(), name);
		return this.airlineNames;
	}

	public Map<Language, String> getAirportNames(String name) {
		this.airportNames = getAirport().getNames();
		if (this.airportNames == null) {
			this.airportNames = new HashMap<Language, String>();
		}
		this.airportNames.put(getLanguage(), name);
		return this.airportNames;
	}

	public void setLanguageValues(ResultSet rs, Language language, String pseudoname) throws SQLException {
		language.setId(rs.getInt(pseudoname + "id"));
		language.setName(rs.getString(pseudoname + "name"));
		language.setTag(rs.getString(pseudoname + "tag"));
		language.setActive(rs.getBoolean(pseudoname + "active"));
	}
	
	public void setAirlineValues(ResultSet rs) throws SQLException {
		//AIRLINE filling
		setLanguage(getLanguageByTag(
				rs.getString("larl.tag")));
		setLanguageValues(rs, getLanguage(), "larl.");
		
		getAirline().setId(rs.getInt("arl.id"));
		getAirline().setIcaoCode(rs.getString("arl.ICAO"));
		getAirline().setIataCode(rs.getString("arl.IATA"));
		getAirline().setLogo(rs.getString("arl.path"));
		getAirline().setNames(getAirlineNames(
				rs.getString("arl18.name")));
		getAirline().setDefaultLanguage(languages.get(getDefaultLangTag()));
	}
	
	public void setAirportValues(ResultSet rs) throws SQLException {
		//AIRPORT filling
		setLanguage(getLanguageByTag(
				rs.getString("larp.tag")));
		setLanguageValues(rs, getLanguage(), "larp.");
		
		getAirport().setId(rs.getInt("arp.id"));
		getAirport().setIataCode(rs.getString("arp.IATA"));
		getAirport().setIcaoCode(rs.getString("arp.ICAO"));
		getAirport().setNames(getAirportNames(
				rs.getString("arp18.name")));
		
		getAirport().setDefaultLanguage(languages.get(getDefaultLangTag()));
		
	}
	
	public void setFlightValues(ResultSet rs) throws SQLException {
		setAirlineValues(rs);
		setAirportByIcao(
				rs.getString("arp.ICAO"));
		setAirportValues(rs);
		
		getFlight().setId(rs.getInt("flt.id"));
		getFlight().setNumber(rs.getInt("flt.aviacompany_flight_number"));
		getFlight().setArrival(rs.getString("flt.type"));
		getFlight().setAirline(getAirline());
		getFlight().setAirport(getAirport());
	}
	
	public void setSchFlightValues(ScheduledFlight schFlight, ResultSet rs, String pseudoname) throws SQLException {
		setFlightValues(rs);
		schFlight.setFlight(getFlight());
		
		java.sql.Date date = rs.getDate(pseudoname + "scheduledDate");
		Time time = rs.getTime(pseudoname + "scheduledDate"); 
		schFlight.setScheduledDate(DateTime.getDateFromSqlTypes(date, time));
		
		date = rs.getDate(pseudoname + "statusTime");
		time = rs.getTime(pseudoname + "statusTime");
		schFlight.setStatusTime(DateTime.getDateFromSqlTypes(date, time)); 
	}

}

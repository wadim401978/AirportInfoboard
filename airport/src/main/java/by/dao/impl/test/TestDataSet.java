package by.dao.impl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;

public final class TestDataSet {
	private static TestDataSet instance;
	
	private Map<String, Language> langMap;
	private List<Language> languages;
	private Map<String, Airport> airportMap;
	private List<Airport> airports;
	private Map<String, Airline> airlineMap;
	private List<Airline> airlines;
	private Map<String, Flight> flightMap;
	private List<Flight> flights;
	
	
	private TestDataSet() {
	}

	public static TestDataSet getInstance() {
		if (instance==null) {
			instance = new TestDataSet();
			setLang(instance);
			setAirports(instance);
			setAirlines(instance);
			setFlights(instance);
		}
		return instance;
	}

	
	public Map<String, Language> getLangMap() {
		return langMap;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}

	public List<Airport> getAirports() {
		return airports;
	}
	
	public Map<String, Airline> getAirlineMap() {
		return airlineMap;
	}

	public List<Airline> getAirlines() {
		return airlines;
	}
	
	public Map<String, Flight> getFlightMap() {
		return flightMap;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	private static void setLang(TestDataSet inst) {
		inst.langMap = new HashMap<String, Language>();
		int i = 0;
		Language lang = new Language(++i, "русский", "ru");
		inst.langMap.put(lang.getCode(), lang);
		inst.languages = new ArrayList<>();
		inst.languages.add(lang);
		lang = new Language(++i, "английский-english", "en");
		inst.langMap.put(lang.getCode(), lang);
		inst.languages.add(lang);
		lang = new Language(++i, "белорусский-беларуская", "be");
		inst.langMap.put(lang.getCode(), lang);
		inst.languages.add(lang);
		lang = new Language(++i, "испанский-espanol", "es");
		inst.langMap.put(lang.getCode(), lang);
		inst.languages.add(lang);
	}
	
	private static void generateAirportDefinitions(TestDataSet inst, List<Airport> airports, Map<String, Airport> airportMap, Airport airport, String ru, String en) {
		Map<Language, String> nameMap = new HashMap<Language, String>();
		nameMap.put(inst.langMap.get("ru"), ru);
		nameMap.put(inst.langMap.get("en"), en);
		airport.setNames(nameMap);
		
		airports.add(airport);
		airportMap.put(airport.getIcaoCode(), airport);
	}
	
	private static void setAirports(TestDataSet inst) {
		inst.airportMap = new HashMap<String, Airport>();
		int i = 0;
		
		inst.airports = new ArrayList<>();
		generateAirportDefinitions(inst, inst.airports, inst.airportMap, new Airport(++i, "VTB", "UMII", null), "Витебск", "Vitebsk");
		generateAirportDefinitions(inst, inst.airports, inst.airportMap, new Airport(++i, "HRG", "HEGN", null), "Хургада", "Hurghada");
		generateAirportDefinitions(inst, inst.airports, inst.airportMap, new Airport(++i, "SSH", "HESH", null), "Шарм-эль-Шех", "Sharm-ash-Sheikh");
		
	}
	
	
	private static void generateAirlineDefinitions(TestDataSet inst, List<Airline> airlines, Map<String, Airline> airlineMap, Airline airline, String ru, String en) {
		Map<Language, String> nameMap = new HashMap<Language, String>();
		nameMap.put(inst.langMap.get("ru"), ru);
		nameMap.put(inst.langMap.get("en"), en);
		airline.setNames(nameMap);
		
		airlines.add(airline);
		airlineMap.put(airline.getIcaoCode(), airline);
	}
	
	private static void setAirlines(TestDataSet inst) {
		inst.airlineMap = new HashMap<String, Airline>();
		int i = 0;
		
		inst.airlines = new ArrayList<>();
		generateAirlineDefinitions(inst, inst.airlines, inst.airlineMap, new Airline(++i, "B2", "BRU", null, "belavia.jpg"), "Белавиа", "Belavia");
		generateAirlineDefinitions(inst, inst.airlines, inst.airlineMap, new Airline(++i, "SU", "AFL", null, "aeroflot.jpg"), "Аэрофлот", "Aeroflot");
		
	}
	
	private static void generateFlightDefinitions(TestDataSet inst, List<Flight> flights, Map<String, Flight> flightMap, int apId, int alId, int num, boolean isArr, int incr) {
		Airline al = TestDataSet.getInstance().airlines.get(alId);
		Flight flight = new Flight(incr, isArr, TestDataSet.getInstance().airports.get(apId), al, num);
		flights.add(flight);
		flightMap.put(al.getIcaoCode()+num, flight);
	}
	
	private static void setFlights(TestDataSet inst) {
		inst.flightMap = new HashMap<String, Flight>();
		int i = 0;
		inst.flights = new ArrayList<>();
		generateFlightDefinitions(inst, inst.flights, inst.flightMap, 1, 0, 8218, true, ++i);
		generateFlightDefinitions(inst, inst.flights, inst.flightMap, 1, 0, 8219, false, ++i);
		generateFlightDefinitions(inst, inst.flights, inst.flightMap, 2, 0, 8208, true, ++i);
		generateFlightDefinitions(inst, inst.flights, inst.flightMap, 2, 0, 8209, false, ++i);
	}
	

}

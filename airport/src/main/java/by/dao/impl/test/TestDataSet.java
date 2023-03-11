package by.dao.impl.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.ArrivalStatus;
import by.dao.model.flight.DepartureStatus;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.services.ScheduledArrivalFlightService;
import by.services.ScheduledDepartureFlightService;
import by.services.impl.ScheduledArrivalFlightServiceImpl;
import by.services.impl.ScheduledDepartureFlightServiceImpl;

public final class TestDataSet {
	private static TestDataSet instance;
	
	private Map<String, Language> langMap = null;
	private List<Language> languages = null;
	private Map<String, Airport> airportMap = null;
	private List<Airport> airports = null;
	private Map<String, Airline> airlineMap = null;
	private List<Airline> airlines = null;
	private Map<String, Flight> flightMap = null;
	private List<Flight> flights = null;
	private List<ScheduledArrivalFlight> arrivals = null;
	private List<ScheduledDepartureFlight> departures = null;

	private final ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
	
	
	private TestDataSet() {
	}

	public static TestDataSet getInstance() {
		if (instance==null) {
			instance = new TestDataSet();
			setLang();
			setAirports();
			setAirlines();
			setFlights();
			setArrivals();
			setDepartures();
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
	
	public List<ScheduledArrivalFlight> getArrivals() {
		return arrivals;
	}
	
	public List<ScheduledDepartureFlight> getDepartures() {
		return departures;
	}


	private static void generateLangDefinitions(Language lang) {
		TestDataSet inst = getInstance();
		
		if(inst.languages==null) {
			inst.languages = new ArrayList<>();
		}
		inst.languages.add(lang);
		
		if(inst.langMap == null) {
			inst.langMap = new HashMap<String, Language>();
		}
		inst.langMap.put(lang.getLangTag(), lang);

	}
	
	private static void setLang() {
		int i = 0;
		generateLangDefinitions(new Language(++i, "русский", "ru"));
		generateLangDefinitions(new Language(++i, "английский-english", "en"));
		generateLangDefinitions(new Language(++i, "белорусский-беларуская", "be"));
		generateLangDefinitions(new Language(++i, "испанский-espanol", "es"));
		
	}
	
	private static void generateAirportDefinitions(Airport airport, String ru, String en) {
		TestDataSet inst = getInstance();
		Map<Language, String> nameMap = new HashMap<Language, String>();
		nameMap.put(inst.langMap.get("ru"), ru);
		nameMap.put(inst.langMap.get("en"), en);
		airport.setNames(nameMap);
		
		if(inst.airports==null) {
			inst.airports = new ArrayList<>();
		}
		inst.airports.add(airport);
		
		if(inst.airportMap == null) {
			inst.airportMap = new HashMap<String, Airport>();
		}
		inst.airportMap.put(airport.getIcaoCode(), airport);
	}
	
	private static void setAirports() {
		int i = 0;
		generateAirportDefinitions(new Airport(++i, "VTB", "UMII", null), "Витебск", "Vitebsk");
		generateAirportDefinitions(new Airport(++i, "HRG", "HEGN", null), "Хургада", "Hurghada");
		generateAirportDefinitions(new Airport(++i, "SSH", "HESH", null), "Шарм-эль-Шех", "Sharm-ash-Sheikh");
		
	}
	
	
	private static void generateAirlineDefinitions(Airline airline, String ru, String en) {
		TestDataSet inst = getInstance();
		Map<Language, String> nameMap = new HashMap<Language, String>();
		nameMap.put(inst.langMap.get("ru"), ru);
		nameMap.put(inst.langMap.get("en"), en);
		airline.setNames(nameMap);
		
		if(inst.airlines==null) {
			inst.airlines = new ArrayList<>();
		}
		inst.airlines.add(airline);
		
		if(inst.airlineMap == null) {
			inst.airlineMap = new HashMap<String, Airline>();
		}
		inst.airlineMap.put(airline.getIcaoCode(), airline);
	}
	
	private static void setAirlines() {
		int i = 0;
		generateAirlineDefinitions(new Airline(++i, "B2", "BRU", null, "belavia.jpg"), "Белавиа", "Belavia");
		generateAirlineDefinitions(new Airline(++i, "SU", "AFL", null, "aeroflot.jpg"), "Аэрофлот", "Aeroflot");
		
	}
	
	private static void generateFlightDefinitions(int flightId, int aPortId, int aLineId, int flightNum, boolean isArr) {
		TestDataSet inst = getInstance();
		Airline airline = inst.airlines.get(aLineId);
		Airport airport = inst.airports.get(aPortId);
		Flight flight = new Flight(flightId, isArr, airport, airline, flightNum);
		
		if(inst.flights==null) {
			inst.flights = new ArrayList<>();
		}
		inst.flights.add(flight);
		
		if(inst.flightMap == null) {
			inst.flightMap = new HashMap<String, Flight>();
		}
		inst.flightMap.put(airline.getIcaoCode()+flightNum, flight);
	}
	
	private static void setFlights() {
		int i = 0;
		generateFlightDefinitions(++i, 1, 0, 8218, true);
		generateFlightDefinitions(++i, 1, 0, 8219, false);
		generateFlightDefinitions(++i, 2, 0, 8208, true);
		generateFlightDefinitions(++i, 2, 0, 8209, false);
	}
	
	private static void setArrivals() {
		TestDataSet inst = getInstance();
		ScheduledArrivalFlightService service = new ScheduledArrivalFlightServiceImpl();
		int i = 0;
		inst.arrivals = new ArrayList<>();
		Flight flight0 = inst.getFlights().get(0);
		Flight flight2 = inst.getFlights().get(2);
		LocalDate lDate = LocalDate.of(2023, 04, 22);
		LocalTime tTime0 = LocalTime.of(11, 25);
		LocalTime tTime2 = LocalTime.of(13, 45);
		
		LocalDateTime ldt = LocalDateTime.of(lDate, tTime0);
		Date date = Date.from(ldt.toInstant(inst.offset));
		tTime0 = LocalTime.of(11, 30);
		Date tDate = service.getDateWithLocalTime(date, tTime2);
		inst.arrivals.add(new ScheduledArrivalFlight(++i, flight0, date, tDate, ArrivalStatus.EXPECTED));
		
		ldt = LocalDateTime.of(lDate, tTime2);
		date = Date.from(ldt.toInstant(inst.offset));
		inst.arrivals.add(new ScheduledArrivalFlight(++i, flight2, date, ArrivalStatus.EXPECTED));
		
		lDate = LocalDate.of(2023, 04, 23);
		ldt = LocalDateTime.of(lDate, tTime0);
		date = Date.from(ldt.toInstant(inst.offset));
		tTime0 = LocalTime.of(12, 40);
		tDate = service.getDateWithLocalTime(date, tTime0);
		inst.arrivals.add(new ScheduledArrivalFlight(++i, flight0, date, tDate, ArrivalStatus.DELAYED));
	}
	
	private static void setDepartures() {
		TestDataSet inst = getInstance();
		ScheduledDepartureFlightService service = new ScheduledDepartureFlightServiceImpl();
		int i = 0;
		inst.departures = new ArrayList<>();
		Flight flight1 = inst.getFlights().get(1);
		Flight flight3 = inst.getFlights().get(3);
		LocalDate lDate = LocalDate.of(2023, 04, 22);
		LocalTime tTime1 = LocalTime.of(14, 25);
		LocalTime tTime3 = LocalTime.of(16, 50);
		
		LocalDateTime ldt = LocalDateTime.of(lDate, tTime1);
		Date date = Date.from(ldt.toInstant(inst.offset));
		tTime1 = LocalTime.of(14, 35);
		Date tDate = service.getDateWithLocalTime(date, tTime3);
		inst.departures.add(new ScheduledDepartureFlight(++i, flight1, date, tDate, DepartureStatus.CHECKIN_AT));
		
		ldt = LocalDateTime.of(lDate, tTime3);
		date = Date.from(ldt.toInstant(inst.offset));
		inst.departures.add(new ScheduledDepartureFlight(++i, flight3, date, DepartureStatus.CHECKIN_NOW));
		
		lDate = LocalDate.of(2023, 04, 23);
		ldt = LocalDateTime.of(lDate, tTime1);
		date = Date.from(ldt.toInstant(inst.offset));
		tTime1 = LocalTime.of(15, 41);
		tDate = service.getDateWithLocalTime(date, tTime1);
		inst.departures.add(new ScheduledDepartureFlight(++i, flight1, date, tDate, DepartureStatus.CANCELLED));
	}
	

}

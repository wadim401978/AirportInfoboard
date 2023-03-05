package by.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import by.dao.AirportDAO;
import by.dao.model.AirEntity;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

@Repository
//public class TestAirportDAOImpl extends AirEntityDAOImpl {
public class TestAirportDAOImpl implements AirportDAO  {
	private Map<String, Airport> airportMap;
	private List<Airport> airports;
	
	public TestAirportDAOImpl() {
		this.airportMap = new HashMap<String, Airport>();
		int i = 0;
		Airport airport = new Airport(++i, "VTB", "UMII", null);
		airports = new ArrayList<>();
		airports.add(airport);
		airportMap.put(airport.getIcaoCode(), airport);
		airport = new Airport(++i, "HRG", "HEGN", null);
		airports.add(airport);
		airportMap.put(airport.getIcaoCode(), airport);
		airport = new Airport(++i, "SSH", "HESH", null);
		airports.add(airport);
		airportMap.put(airport.getIcaoCode(), airport);
	}

	@Override
	public Airport read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return airports.get(id);
	}

	@Override
	public String getNameByLang(Language lang, Airport obj) {
		return obj.getNames().get(lang);
	}

	@Override
	public void setNameByLang(String name, Language lang, Airport obj) {
		obj.getNames().put(lang, name);
		
	}

	@Override
	public List<Airport> getAirports() {
		return airports;
	}
	
	

}

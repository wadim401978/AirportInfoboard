package by.dao;

import java.util.List;

import by.dao.model.flight.Airport;

public interface AirportDAO extends AirEntityDAO<Integer, Airport> {
	public List<Airport> getAirports();
}

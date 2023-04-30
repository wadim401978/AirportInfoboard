package by.dao;

import java.util.List;

import by.dao.model.flight.Airline;

public interface AirlineDAO extends AirEntityDAO<Integer, Airline> {
	public List<Airline> findAirlines();
}

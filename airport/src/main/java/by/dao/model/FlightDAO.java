package by.dao.model;

import java.util.List;

import by.dao.GenericDAO;
import by.dao.model.flight.Flight;

public interface FlightDAO extends GenericDAO<Integer, Flight> {
	public List<Flight> getFlights();
	public Flight getFlightByIcaoNumber(String icao);
}

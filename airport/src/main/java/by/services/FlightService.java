package by.services;

import by.dao.model.flight.Flight;

public interface FlightService extends Service<Flight> {
	public Flight getByIcaoNumber(String icao);
}

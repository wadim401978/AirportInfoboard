package by.services;

import by.dao.model.flight.Airport;

public interface AirportService extends AirEntityService<Airport> {
	public Airport getDefaultAirport();
}

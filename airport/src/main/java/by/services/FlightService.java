package by.services;

import java.util.List;
import by.dao.model.flight.Flight;

public interface FlightService extends Service<Flight> {
	public List<Flight> getFlights(boolean isArrival);
	public int getScheduledFlightsCount(Flight flight);
}

package by.dao;

import java.util.List;
import by.dao.model.flight.Flight;

public interface FlightDAO extends GenericDAO<Integer, Flight> {
	public List<Flight> findFlights();
	public List<Flight> findFlights(boolean isArrival);
	public int countScheduledFlights(Flight flight);
}

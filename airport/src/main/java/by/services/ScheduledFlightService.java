package by.services;

import java.sql.Date;
import java.util.List;

import by.dao.model.flight.Flight;

public interface ScheduledFlightService<T> extends Service<T> {
	public List<T> getAllByFlight(Flight flight);
	public List<T> getByPeriod(Date startDate, Date endDate);
	public T getScheduledFlight(Date date, Flight flight);
}

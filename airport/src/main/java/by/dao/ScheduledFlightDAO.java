package by.dao;

import java.sql.Date;
import java.util.List;
import by.dao.model.flight.Flight;

public interface ScheduledFlightDAO<ID, T> extends GenericDAO<ID, T> {
	public List<T> findAll();
	public List<T> findAllByFlight(Flight flight);
	public List<T> findByPeriod(Date startDate, Date endDate);
	public T findScheduledFlight(Date date, Flight flight);
}

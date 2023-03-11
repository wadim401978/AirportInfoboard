package by.dao;

import java.util.Date;
import java.util.List;
import by.dao.model.flight.Flight;

public interface ScheduledFlightDAO<ID, T> extends GenericDAO<ID, T> {
	public List<T> findAll();
	public List<T> findAllByFlight(Flight flight);
	public List<T> findByPeriod(Date startDate, Date endDate);
	public T findScheduledFlight(Date date, Flight flight);
	public default  boolean isDateInBand(Date date, Date startDate, Date endDate) {
		boolean isInBand = false;
		if(date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
			isInBand = true;
		}
		return isInBand;
	}
}

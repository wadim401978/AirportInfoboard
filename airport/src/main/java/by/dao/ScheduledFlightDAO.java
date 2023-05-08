package by.dao;

import java.util.Date;
import java.util.List;

public interface ScheduledFlightDAO<ID, T> extends GenericDAO<ID, T> {
	public List<T> findAll();
	
	public default  boolean isDateInBand(Date date, Date startDate, Date endDate) {
		boolean isInBand = false;
		if(date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
			isInBand = true;
		}
		return isInBand;
	}
}

package by.dao.model.flight;

import java.util.Date;
public class ScheduledArrivalFlight extends ScheduledFlight {

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate) {
		super(id, flight, scheduledDate);
	}

}

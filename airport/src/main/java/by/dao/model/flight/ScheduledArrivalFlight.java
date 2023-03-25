package by.dao.model.flight;

import java.util.Date;
public class ScheduledArrivalFlight extends ScheduledFlight {
	private ArrivalStatus status;

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate, ArrivalStatus status) {
		super(id, flight, scheduledDate);
		this.status = status;
	}
	
	public ScheduledArrivalFlight() {
		
	}

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate, Date statusTime, ArrivalStatus status) {
		super(id, flight, scheduledDate, statusTime);
		this.status = status;
	}

	public ArrivalStatus getStatus() {
		return status;
	}

	public void setStatus(ArrivalStatus status) {
		this.status = status;
	}

}

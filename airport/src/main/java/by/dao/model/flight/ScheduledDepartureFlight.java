package by.dao.model.flight;

import java.time.LocalDateTime;
import java.util.Date;

public class ScheduledDepartureFlight extends ScheduledFlight {
	private DepartureStatus status;

    public ScheduledDepartureFlight(int id, Flight flight, Date scheduledDate, DepartureStatus status) {
		super(id, flight, scheduledDate);
		this.setStatus(status);
	}

	public DepartureStatus getStatus() {
		return status;
	}

	public void setStatus(DepartureStatus status) {
		this.status = status;
	}


}

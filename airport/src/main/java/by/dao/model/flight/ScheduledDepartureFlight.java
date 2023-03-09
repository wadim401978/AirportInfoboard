package by.dao.model.flight;

import java.time.LocalDateTime;

public class ScheduledDepartureFlight extends ScheduledFlight {
	private DepartureStatus status;

    public ScheduledDepartureFlight(int id, Flight flight, LocalDateTime scheduledDate, DepartureStatus status) {
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

package by.dao.model.flight;

import java.time.LocalDateTime;
public class ScheduledArrivalFlight extends ScheduledFlight {
	private ArrivalStatus status;

	public ScheduledArrivalFlight(int id, Flight flight, LocalDateTime scheduledDate, ArrivalStatus status) {
		super(id, flight, scheduledDate);
		this.status = status;
	}

	public ArrivalStatus getStatus() {
		return status;
	}

	public void setStatus(ArrivalStatus status) {
		this.status = status;
	}

}

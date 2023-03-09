package by.dao.model.flight;

import by.dao.model.Entity;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class ScheduledFlight extends Entity {
    private Flight flight;
    private LocalDateTime scheduledDate;
    private LocalTime statusTime;

    public ScheduledFlight(int id, Flight flight, LocalDateTime scheduledDate) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		this.setStatusTime(LocalTime.of(scheduledDate.getHour(), scheduledDate.getMinute()));
	}

	public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

	public LocalDateTime getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDateTime scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public LocalTime getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(LocalTime statusTime) {
		this.statusTime = statusTime;
	}
    
}

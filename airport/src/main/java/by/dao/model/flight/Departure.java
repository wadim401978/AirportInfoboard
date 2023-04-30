package by.dao.model.flight;

import java.util.Date;

public class Departure extends ScheduledFlight {
	private DepartureStatus status;

    public Departure(int id, Flight flight, Date scheduledDate, DepartureStatus status) {
		super(id, flight, scheduledDate);
		this.setStatus(status);
	}
    
    public Departure() {
    	
    }

    public Departure(int id, Flight flight, Date scheduledDate, Date statusTime, DepartureStatus status) {
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

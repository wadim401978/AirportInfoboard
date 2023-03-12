package by.dao.model.flight;

import by.dao.model.Entity;
import java.util.Date;

public abstract class ScheduledFlight extends Entity {
    private Flight flight;
    private Date scheduledDate;
    private Date statusTime;

    public ScheduledFlight(int id, Flight flight, Date scheduledDate) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		this.setStatusTime(scheduledDate);
	}


	public ScheduledFlight(int id, Flight flight, Date scheduledDate, Date statusTime) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		this.statusTime = statusTime;
	}

	public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public Date getStatusTime() {
		return statusTime;
	}

    
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	
	@Override
	public String toString() {
		Flight fl = getFlight();
		return super.toString()
				+ ": " + " " + fl.getIcaoNumber() + "/" + fl.getIataNumber() + ": " 
				+ fl.getAirline().getName() + "; " 
				+ "(" + fl.getAirport().getIataCode() + ") " + fl.getAirport().getName() + " | ";
	}
    
}

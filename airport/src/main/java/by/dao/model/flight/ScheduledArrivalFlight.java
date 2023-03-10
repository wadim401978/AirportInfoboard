package by.dao.model.flight;

import java.time.LocalTime;
import java.util.Date;
public class ScheduledArrivalFlight extends ScheduledFlight {
	private ArrivalStatus status;

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate, ArrivalStatus status) {
		super(id, flight, scheduledDate);
		this.status = status;
	}

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate, Date statusTime, ArrivalStatus status) {
		super(id, flight, scheduledDate, statusTime);
		this.status = status;
	}

	public ScheduledArrivalFlight(int id, Flight flight, Date scheduledDate, LocalTime statusTime, ArrivalStatus status) {
		super(id, flight, scheduledDate, statusTime);
		this.status = status;
	}

	public ArrivalStatus getStatus() {
		return status;
	}

	public void setStatus(ArrivalStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		Flight fl = getFlight();
		return super.toString()
				+ ": " + getScheduledDateFormatted() + " " + fl.getIcaoNumber() + "/" + fl.getIataNumber() + ": " 
				+ fl.getAirline().toStringNames() + "; " 
				+ "(" + fl.getAirport().getIataCode() + ") " + fl.getAirport().toStringNames() + " | ";
			
	}

}

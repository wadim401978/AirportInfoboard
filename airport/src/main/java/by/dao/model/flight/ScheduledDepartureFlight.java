package by.dao.model.flight;

import java.time.LocalTime;
import java.util.Date;

public class ScheduledDepartureFlight extends ScheduledFlight {

    public ScheduledDepartureFlight(int id, Flight flight, Date scheduledDate) {
		super(id, flight, scheduledDate);
	}

	private LocalTime regTime;
    
    

    public LocalTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalTime regTime) {
        this.regTime = regTime;
    }

}

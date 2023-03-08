package by.dao.model.flight;

import by.dao.model.Entity;
import by.dao.model.common.Language;
import java.util.Date;
import java.util.Map;

public abstract class ScheduledFlight extends Entity {
    private Flight flight;
    private Date scheduledDate;
    private Date actualDate;
    private Map<Language, String> comment;

    public ScheduledFlight(int id, Flight flight, Date scheduledDate) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		this.actualDate = scheduledDate;
	}

	public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Map<Language, String> getComment() {
        return comment;
    }

    public void setComment(Map<Language, String> comment) {
        this.comment = comment;
    }

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

    
    
}

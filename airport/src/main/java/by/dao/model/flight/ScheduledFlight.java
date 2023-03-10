package by.dao.model.flight;

import by.dao.model.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class ScheduledFlight extends Entity {
    private Flight flight;
    private Date scheduledDate;
    private Date statusTime;
    private final ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());

    public ScheduledFlight(int id, Flight flight, Date scheduledDate) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		this.setStatusTime(scheduledDate);
	}

	public ScheduledFlight(int id, Flight flight, Date scheduledDate, LocalTime statusTime) {
		super(id);
		this.flight = flight;
		this.scheduledDate = scheduledDate;
		setStatusTime(statusTime);
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

	public void setScheduledDate(LocalDateTime scheduledDate) {
		this.scheduledDate = Date.from(scheduledDate.toInstant(offset));
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public Date getStatusTime() {
		return statusTime;
	}

	public LocalTime getStatusTimeAsLocalTime() {
		return LocalDateTime.ofInstant(statusTime.toInstant(), offset).toLocalTime();
	}

	public String getStatusTimeFormatted() {
		return getStatusTimeAsLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	public String getScheduledDateFormatted() {
		return LocalDateTime
				.ofInstant(scheduledDate.toInstant(), offset)
				.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
	}

	public void setStatusTime(LocalTime statusTime) {
		LocalDateTime ldt = LocalDateTime.ofInstant(this.scheduledDate.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		this.statusTime = Date.from(LocalDateTime.of(ld, statusTime).toInstant(offset));
	}
    
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	


    
}

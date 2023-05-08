package by.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import by.dao.model.flight.ScheduledFlight;

public interface ScheduledFlightService<T extends ScheduledFlight> extends Service<T> {
	final ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());

	//1
	public default Date getDateFromLocalDateTime(LocalDateTime date) {
		return Date.from(date.toInstant(offset));
	}

	//2
	public default LocalTime getDateAsLocalTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), offset).toLocalTime();
	}
	
	//3
	public default String getTimeFormatted(Date date) {
		if(date==null) {
			return "";
		}
		return getDateAsLocalTime(date).format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	public default String getTimeFormattedSec(Date date) {
		if(date==null) {
			return "";
		}
		return getDateAsLocalTime(date).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	//4
	public default String getDateFormatted(Date date) {
		return LocalDateTime
				.ofInstant(date.toInstant(), offset)
				.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

}

package by.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import by.dao.model.flight.Flight;

public interface ScheduledFlightService<T> extends Service<T> {
	final ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());

	public List<T> getAllByFlight(Flight flight);

	public List<T> getByPeriod(Date startDate, Date endDate);

	public T getScheduledFlight(Date date, Flight flight);

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
	
	//5
	public default Date getDateWithLocalTime(Date date, LocalTime statusTime) {
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		return Date.from(LocalDateTime.of(ld, statusTime).toInstant(offset));
	}

	

}

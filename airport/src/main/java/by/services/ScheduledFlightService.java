package by.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.app.util.DateTime;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledFlight;

public interface ScheduledFlightService<T extends ScheduledFlight> extends Service<T> {
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

	public default T getScheduledFlight(HttpServletRequest req, T schFlight) {
		schFlight.setId(Integer.parseInt(req.getParameter("id")));
		
		Flight flight = new Flight();
		flight.setId(Integer.parseInt(req.getParameter("flight")));
		schFlight.setFlight(flight);
		
		String date = req.getParameter("scheduledDate");
		LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String time = req.getParameter("scheduledTime");
		LocalTime lt = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
		schFlight.setScheduledDate(DateTime.getDate(ld, lt));
		time = req.getParameter("statusTime");
		if (time == null||time.isEmpty()) {
			schFlight.setStatusTime(null);
		} else {
			lt = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
			schFlight.setStatusTime(DateTime.getDate(ld, lt));
		}
		return schFlight;
	}

}

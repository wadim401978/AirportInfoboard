package by.app.util;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTime {
	final static ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());

	public static Date getDateWithLocalTime(Date date, LocalTime statusTime) {
		LocalDateTime ldt = LocalDateTime.ofInstant(
				date.toInstant(), 
				ZoneId.systemDefault());
		
		LocalDate ld = LocalDate.of(
				ldt.getYear(), 
				ldt.getMonthValue(), 
				ldt.getDayOfMonth());
		
		return Date.from(LocalDateTime.of(ld, statusTime).toInstant(offset));
	}
	
	public static Date getDateFromSqlTypes(java.sql.Date date, Time time) {
		if (date == null || time == null) {
			return null;
		} else {
			return DateTime.getDate(
					date.toLocalDate(), 
					time.toLocalTime());
		}
	}
	
	public static Date getDate(LocalDate localDate, LocalTime localTime) {
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		return Date.from(localDateTime.toInstant(offset));
	}
	
	public static Date getDate(String stringDate, String stringTime) {
		Date  date = null;
		if (stringDate == null || stringTime == null || stringTime.equals("")) {
			date = null;
		} else {
			LocalDate ld = getLocalDate(stringDate);
			LocalTime lt = getLocalTime(stringTime);
			date = getDate(ld, lt);
		}
		return date;
	}
	
	public static LocalDate getLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static LocalTime getLocalTime(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
	}
	
}

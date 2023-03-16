package by.dao.impl.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.ScheduledDepartureFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledDepartureFlight;

@Repository
public class TestScheduledDepartureFlightDAOImpl implements ScheduledDepartureFlightDAO {

	@Override
	public List<ScheduledDepartureFlight> findAll() {
		return TestDataSet.getInstance().getDepartures();
	}

	@Override
	public List<ScheduledDepartureFlight> findAllByFlight(Flight flight) {
		List<ScheduledDepartureFlight> departures = new ArrayList<>();
		List<ScheduledDepartureFlight> allDepartures = findAll();
		for (ScheduledDepartureFlight departure : allDepartures) {
			if(flight.getIcaoNumber().equals(departure.getFlight().getIcaoNumber())) {
				departures.add(departure);
			}
		}
		return departures;
	}

	@Override
	public List<ScheduledDepartureFlight> findByPeriod(Date startDate, Date endDate) {
		List<ScheduledDepartureFlight> departures = new ArrayList<>();
		List<ScheduledDepartureFlight> allDepartures = findAll();
		
		for (ScheduledDepartureFlight departure : allDepartures) {
			Date arrivalDate = departure.getScheduledDate(); 
			if(isDateInBand(arrivalDate, startDate, endDate)) {
				departures.add(departure);
			}
		}
		return departures;
	}

	@Override
	public ScheduledDepartureFlight findScheduledFlight(Date date, Flight flight) {
		List<ScheduledDepartureFlight> allDepartures = findAll();
		ScheduledDepartureFlight foundDeparture = null;
		
		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		LocalTime lt = LocalTime.of(0, 0, 0);
		Date startDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		lt = LocalTime.of(23, 59, 59);
		Date endDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		
		for (ScheduledDepartureFlight departure : allDepartures) {
			Date departureDate = departure.getScheduledDate(); 
			if(departure.getFlight().getIcaoNumber().equals(flight.getIcaoNumber()) && isDateInBand(departureDate, startDate, endDate)) {
				foundDeparture = departure;
				break;
			}
		}
		return foundDeparture;
	}

	@Override
	public ScheduledDepartureFlight read(Integer id) {
		return TestDataSet.getInstance().getDepartures().get(id);
	}
	

}

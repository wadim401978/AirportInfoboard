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
import by.dao.DepartureDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.Departure;

@Repository
public class TestDepartureDAOImpl implements DepartureDAO {

	@Override
	public List<Departure> findAll() {
		return TestDataSet.getInstance().getDepartures();
	}

	@Override
	public List<Departure> findAllByFlight(Flight flight) {
		List<Departure> departures = new ArrayList<>();
		List<Departure> allDepartures = findAll();
		for (Departure departure : allDepartures) {
			if(flight.getIcaoNumber().equals(departure.getFlight().getIcaoNumber())) {
				departures.add(departure);
			}
		}
		return departures;
	}

	@Override
	public List<Departure> findByPeriod(Date startDate, Date endDate) {
		List<Departure> departures = new ArrayList<>();
		List<Departure> allDepartures = findAll();
		
		for (Departure departure : allDepartures) {
			Date arrivalDate = departure.getScheduledDate(); 
			if(isDateInBand(arrivalDate, startDate, endDate)) {
				departures.add(departure);
			}
		}
		return departures;
	}

	@Override
	public Departure findScheduledFlight(Date date, Flight flight) {
		List<Departure> allDepartures = findAll();
		Departure foundDeparture = null;
		
		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		LocalTime lt = LocalTime.of(0, 0, 0);
		Date startDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		lt = LocalTime.of(23, 59, 59);
		Date endDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		
		for (Departure departure : allDepartures) {
			Date departureDate = departure.getScheduledDate(); 
			if(departure.getFlight().getIcaoNumber().equals(flight.getIcaoNumber()) && isDateInBand(departureDate, startDate, endDate)) {
				foundDeparture = departure;
				break;
			}
		}
		return foundDeparture;
	}

	@Override
	public Departure read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getDepartures().get(id);
	}
	

}

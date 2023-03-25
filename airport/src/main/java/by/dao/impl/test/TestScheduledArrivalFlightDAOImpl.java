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
import by.dao.ScheduledArrivalFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;

@Repository
public class TestScheduledArrivalFlightDAOImpl implements ScheduledArrivalFlightDAO {

	@Override
	public List<ScheduledArrivalFlight> findAll() {
		return TestDataSet.getInstance().getArrivals();
	}

	@Override
	public List<ScheduledArrivalFlight> findAllByFlight(Flight flight) {
		List<ScheduledArrivalFlight> arrivals = new ArrayList<>();
		List<ScheduledArrivalFlight> allArrivals = findAll();
		for (ScheduledArrivalFlight arrival : allArrivals) {
			if(flight.getIcaoNumber().equals(arrival.getFlight().getIcaoNumber())) {
				arrivals.add(arrival);
			}
		}
		return arrivals;
	}

	
	@Override
	public List<ScheduledArrivalFlight> findByPeriod(Date startDate, Date endDate) {
		List<ScheduledArrivalFlight> arrivals = new ArrayList<>();
		List<ScheduledArrivalFlight> allArrivals = findAll();
		
		for (ScheduledArrivalFlight arrival : allArrivals) {
			Date arrivalDate = arrival.getScheduledDate(); 
			if(isDateInBand(arrivalDate, startDate, endDate)) {
				arrivals.add(arrival);
			}
		}
		
		return arrivals;
	}
	

	@Override
	public ScheduledArrivalFlight findScheduledFlight(Date date, Flight flight) {
		List<ScheduledArrivalFlight> allArrivals = findAll();
		ScheduledArrivalFlight foundArrival = null;
		
		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		LocalTime lt = LocalTime.of(0, 0, 0);
		Date startDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		lt = LocalTime.of(23, 59, 59);
		Date endDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		
		for (ScheduledArrivalFlight arrival : allArrivals) {
			Date arrivalDate = arrival.getScheduledDate(); 
			if(arrival.getFlight().getIcaoNumber().equals(flight.getIcaoNumber()) && isDateInBand(arrivalDate, startDate, endDate)) {
				foundArrival = arrival;
				break;
			}
		}
		return foundArrival;
	}

	@Override
	public ScheduledArrivalFlight read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getArrivals().get(id);
	}

}

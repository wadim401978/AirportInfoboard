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
import by.dao.ArrivalDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.Arrival;

@Repository
public class TestArrivalDAOImpl implements ArrivalDAO {

	@Override
	public List<Arrival> findAll() {
		return TestDataSet.getInstance().getArrivals();
	}

	public List<Arrival> findAllByFlight(Flight flight) {
		List<Arrival> arrivals = new ArrayList<>();
		List<Arrival> allArrivals = findAll();
		for (Arrival arrival : allArrivals) {
			if(flight.getIcaoNumber().equals(arrival.getFlight().getIcaoNumber())) {
				arrivals.add(arrival);
			}
		}
		return arrivals;
	}

	
	public List<Arrival> findByPeriod(Date startDate, Date endDate) {
		List<Arrival> arrivals = new ArrayList<>();
		List<Arrival> allArrivals = findAll();
		
		for (Arrival arrival : allArrivals) {
			Date arrivalDate = arrival.getScheduledDate(); 
			if(isDateInBand(arrivalDate, startDate, endDate)) {
				arrivals.add(arrival);
			}
		}
		
		return arrivals;
	}
	

	public Arrival findScheduledFlight(Date date, Flight flight) {
		List<Arrival> allArrivals = findAll();
		Arrival foundArrival = null;
		
		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate ld = LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
		LocalTime lt = LocalTime.of(0, 0, 0);
		Date startDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		lt = LocalTime.of(23, 59, 59);
		Date endDate = Date.from(LocalDateTime.of(ld, lt).toInstant(offset));
		
		for (Arrival arrival : allArrivals) {
			Date arrivalDate = arrival.getScheduledDate(); 
			if(arrival.getFlight().getIcaoNumber().equals(flight.getIcaoNumber()) && isDateInBand(arrivalDate, startDate, endDate)) {
				foundArrival = arrival;
				break;
			}
		}
		return foundArrival;
	}

	@Override
	public Arrival read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getArrivals().get(id);
	}

}

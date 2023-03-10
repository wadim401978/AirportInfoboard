package by.dao.impl.test;

//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
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
			if(arrival.getFlight().getIcaoNumber() == flight.getIcaoNumber()) {
				arrivals.add(arrival);
			}
		}
		return arrivals;
	}

	
//	private Date getConvertedLocalDate(LocalDateTime arrivalDateLocal, ZoneOffset offset, boolean dateOnly) {
//		int yyyy = arrivalDateLocal.getYear();
//		int MM = arrivalDateLocal.getMonthValue();
//		int dd = arrivalDateLocal.getDayOfMonth();
//		int min, hh, sec;
//		
//		if(dateOnly) {
//			min =0; hh = 0; sec = 0;
//		} else {
//			hh = arrivalDateLocal.getHour();
//			min = arrivalDateLocal.getMinute();
//			sec = arrivalDateLocal.getSecond();
//		}
//		arrivalDateLocal = LocalDateTime.of(yyyy, MM, dd, hh, min, sec);
//		Date arrivalDate = Date.from(arrivalDateLocal.toInstant(offset)); 
//		return arrivalDate;
//	}
	
	@Override
	public List<ScheduledArrivalFlight> findByPeriod(Date startDate, Date endDate) {
		List<ScheduledArrivalFlight> arrivals = new ArrayList<>();
		List<ScheduledArrivalFlight> allArrivals = findAll();
//		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		
		for (ScheduledArrivalFlight arrival : allArrivals) {
//			Date arrivalDate = getConvertedLocalDate(arrival.getScheduledDate(), offset, true); 
			Date arrivalDate = arrival.getScheduledDate(); 
			
			if(arrivalDate.getTime() >= startDate.getTime() && arrivalDate.getTime() <= endDate.getTime()) {
				arrivals.add(arrival);
			}
		}
		
		return arrivals;
	}
	

	@Override
	public ScheduledArrivalFlight findScheduledFlight(Date date, Flight flight) {
		List<ScheduledArrivalFlight> allArrivals = findAll();
		ScheduledArrivalFlight foundArrival = null;
//		ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
		
		for (ScheduledArrivalFlight arrival : allArrivals) {
//			Date arrivalDate = getConvertedLocalDate(arrival.getScheduledDate(), offset, true); 
			Date arrivalDate = arrival.getScheduledDate(); 
			
			if(arrival.getFlight().getIcaoNumber() == flight.getIcaoNumber() && arrivalDate.equals(date)) {
				foundArrival = arrival;
				break;
			}
		}
		return foundArrival;
	}

	@Override
	public ScheduledArrivalFlight read(Integer id) {
		return TestDataSet.getInstance().getArrivals().get(id);
	}

}

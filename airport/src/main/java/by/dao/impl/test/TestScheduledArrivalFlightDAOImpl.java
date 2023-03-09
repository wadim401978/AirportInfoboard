package by.dao.impl.test;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.dao.ScheduledArrivalFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;

@Repository
public class TestScheduledArrivalFlightDAOImpl implements ScheduledArrivalFlightDAO {

	@Override
	public List<ScheduledArrivalFlight> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledArrivalFlight> findAllByFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledArrivalFlight> findByPeriod(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledArrivalFlight findScheduledFlight(Date date, Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledArrivalFlight read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

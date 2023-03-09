package by.dao.impl.test;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.dao.ScheduledDepartureFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledDepartureFlight;

@Repository
public class TestScheduledDepartureFlightDAOImpl implements ScheduledDepartureFlightDAO {
	
	@Override
	public List<ScheduledDepartureFlight> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledDepartureFlight> findAllByFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledDepartureFlight> findByPeriod(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledDepartureFlight findScheduledFlight(Date date, Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledDepartureFlight read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

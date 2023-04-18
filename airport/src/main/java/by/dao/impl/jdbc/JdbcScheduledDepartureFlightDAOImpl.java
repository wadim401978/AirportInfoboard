package by.dao.impl.jdbc;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.ScheduledDepartureFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledDepartureFlight;

@Transactional
@Repository
public class JdbcScheduledDepartureFlightDAOImpl implements ScheduledDepartureFlightDAO {

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

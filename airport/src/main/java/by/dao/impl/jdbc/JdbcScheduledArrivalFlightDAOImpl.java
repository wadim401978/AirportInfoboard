package by.dao.impl.jdbc;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.ScheduledArrivalFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;

@Transactional
@Repository
public class JdbcScheduledArrivalFlightDAOImpl extends JdbcAbstractDao implements ScheduledArrivalFlightDAO {

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

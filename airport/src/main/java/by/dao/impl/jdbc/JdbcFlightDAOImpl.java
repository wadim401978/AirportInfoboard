package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.FlightDAO;
import by.dao.model.flight.Flight;

@Transactional
@Repository
public class JdbcFlightDAOImpl implements FlightDAO {

	@Override
	public Flight read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight getFlightByIcaoNumber(String icao) {
		// TODO Auto-generated method stub
		return null;
	}

}

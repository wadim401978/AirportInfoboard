package by.dao.impl.test;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.FlightDAO;
import by.dao.model.flight.Flight;

@Repository
public class TestFlightDAOImpl implements FlightDAO {

	@Override
	public Flight read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getFlights().get(id);
	}

	@Override
	public List<Flight> findFlights() {
		return TestDataSet.getInstance().getFlights();
	}

	public Flight getFlightByIcaoNumber(String icao) {
		return TestDataSet.getInstance().getFlightMap().get(icao);
	}

	@Override
	public List<Flight> findFlights(boolean isArrival) {
		return findFlights();
	}

	@Override
	public int countScheduledFlights(Flight flight) {
		// TODO Auto-generated method stub
		return 0;
	}

}

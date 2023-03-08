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
	public List<Flight> getFlights() {
		return TestDataSet.getInstance().getFlights();
	}

	@Override
	public Flight getFlightByIcaoNumber(String icao) {
		return TestDataSet.getInstance().getFlightMap().get(icao);
	}

}

package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Flight;

public class FlightsExtractor extends ExtractAssistant implements ResultSetExtractor<List<Flight>> {

	@Override
	public List<Flight> extractData(ResultSet rs) throws SQLException, DataAccessException {
		int instanceId = -1;
		List<Flight> flights = new ArrayList<>();
		while (rs.next()) {
			int recordId = rs.getInt("flt.id");
			if (rs.getRow() == 0 || recordId != instanceId) {
				instanceId = recordId;
				setAirline(new Airline());
				setFlight(new Flight());
				getFlight().setId(recordId);
				flights.add(getFlight());
			}
			setFlightValues(rs);
		}
		return flights;
	}

}

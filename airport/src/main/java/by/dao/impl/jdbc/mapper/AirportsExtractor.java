package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.flight.Airport;

public class AirportsExtractor extends ExtractAssistant implements ResultSetExtractor<List<Airport>> {

	@Override
	public List<Airport> extractData(ResultSet rs) throws SQLException, DataAccessException {
		int instanceId = -1;
		Airport airport = null;
		List<Airport> airports = new ArrayList<>();
		
		while (rs.next()) {
			int recordId = rs.getInt("arp.id");
			if (rs.getRow() == 0 || recordId != instanceId) {
				instanceId = recordId;
				airport = new Airport();
				setAirport(airport);
				airports.add(airport);
				
			}
			setAirportValues(rs);
		}
		return airports;
	}

}

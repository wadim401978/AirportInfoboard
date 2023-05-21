package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Arrival;
import by.dao.model.flight.ArrivalStatus;
import by.dao.model.flight.Flight;

public class ArrivalsExtractor extends AbstractExtractor implements ResultSetExtractor<List<Arrival>> {
	
	@Override
	public List<Arrival> extractData(ResultSet rs) throws SQLException, DataAccessException {
		int instanceId = -1;
		Arrival arrival = null;
		List<Arrival> arrivals = new ArrayList<>();
		while (rs.next()) {
			int recordId = rs.getInt("arr.id");
			if (rs.getRow() == 0 || recordId != instanceId) {
				instanceId = recordId;
				setAirline(new Airline());
				setFlight(new Flight());
				
				arrival = new Arrival();
				arrival.setId(instanceId);
				arrivals.add(arrival);
			}
			setSchFlightValues(arrival, rs, "arr.");
			
			ArrivalStatus[] enums = ArrivalStatus.values();
			int enumId = rs.getInt("ArrivalStatus");
			for (ArrivalStatus item : enums) {
				if (item.getId() == enumId) {
					arrival.setStatus(item);
				}
			}
			
		}
		return arrivals;
	}
}

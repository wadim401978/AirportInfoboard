package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.flight.Airline;
import by.dao.model.flight.DepartureStatus;
import by.dao.model.flight.Departure;
import by.dao.model.flight.Flight;

public class DeparturesExtractor extends AbstractExtractor implements ResultSetExtractor<List<Departure>> {

	
	@Override
	public List<Departure> extractData(ResultSet rs) throws SQLException, DataAccessException {
		int instanceId = -1;
		
		Departure departure = null;
		List<Departure> departures = new ArrayList<>();
		
		while (rs.next()) {
			int recordId = rs.getInt("dep.id");
			if (rs.getRow() == 0 || recordId != instanceId) {
				instanceId = recordId;
				setAirline(new Airline());
				setFlight(new Flight());
				
				departure = new Departure();
				departure.setId(instanceId);
				departures.add(departure);
			}
			
			setSchFlightValues(departure, rs, "dep.");
			
			DepartureStatus[] enums = DepartureStatus.values();
			int enumId = rs.getInt("DepartureStatus");
			for (DepartureStatus item : enums) {
				if (item.getId() == enumId) {
					departure.setStatus(item);
				}
			}
		}
		 
		return departures;
	}

}

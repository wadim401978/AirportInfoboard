package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.flight.Airline;

public class AirlinesExtractor extends AbstractExtractor implements ResultSetExtractor<List<Airline>> {

	@Override
	public List<Airline> extractData(ResultSet rs) throws SQLException, DataAccessException {
		int instanceId = -1;
		
		List<Airline> airlines = new ArrayList<>();
		
		while (rs.next()) {
			int recordId = rs.getInt("arl.id");
			if (rs.getRow() == 0 || recordId != instanceId) {
				instanceId = recordId;
				setAirline(new Airline());
				airlines.add(getAirline());
				
			}
			setAirlineValues(rs);
			
		}
		 
		return airlines;
	}
	

}

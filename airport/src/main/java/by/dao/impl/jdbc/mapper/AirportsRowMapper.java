package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

public class AirportsRowMapper implements RowMapper<Airport> {

	@Override
	public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
		Airport airport = new Airport();
		airport.setId(rs.getInt("a.id"));
		airport.setIcaoCode(rs.getString("a.ICAO"));
		airport.setIataCode(rs.getString("a.IATA"));
		Language defaultLanguage = new Language(
				rs.getInt("l.id"), 
				rs.getString("l.name"), 
				rs.getString("l.tag"), 
				rs.getBoolean("l.active"));
		
		Map<Language, String> names = new HashMap<>();
		names.put(defaultLanguage, rs.getString("a18.name"));
		airport.setNames(names);
		return airport;
	}

}

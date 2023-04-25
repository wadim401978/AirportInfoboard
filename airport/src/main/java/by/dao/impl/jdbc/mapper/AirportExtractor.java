package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

public class AirportExtractor implements ResultSetExtractor<Airport> {

	@Override
	public Airport extractData(ResultSet rs) throws SQLException, DataAccessException {
		Airport airport = new Airport();
		Map<Language, String> names = new HashMap<>();
		while (rs.next()) {
			airport.setId(rs.getInt("a.id"));
			airport.setIcaoCode(rs.getString("a.ICAO"));
			airport.setIataCode(rs.getString("a.IATA"));
			Language lang = new Language(
					rs.getInt("l.id"), 
					rs.getString("l.name"), 
					rs.getString("l.tag"), 
					rs.getBoolean("l.active"));
			
			names.put(lang, rs.getString("a18.name"));
			airport.setNames(names);
		}
		return airport;
	}
	

}

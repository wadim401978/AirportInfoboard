package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

public class AirlineExtractor implements ResultSetExtractor<Airline> {

	@Override
	public Airline extractData(ResultSet rs) throws SQLException, DataAccessException {
		Airline airline = new Airline();
		Map<Language, String> names = new HashMap<>();
		while (rs.next()) {
			airline.setId(rs.getInt("a.id"));
			airline.setIcaoCode(rs.getString("a.ICAO"));
			airline.setIataCode(rs.getString("a.IATA"));
			airline.setLogo(rs.getString("a.path"));
			Language lang = new Language(
					rs.getInt("l.id"), 
					rs.getString("l.name"), 
					rs.getString("l.tag"), 
					rs.getBoolean("l.active"));
			
			names.put(lang, rs.getString("a18.name"));
			airline.setNames(names);
		}
		return airline;
	}
	

}

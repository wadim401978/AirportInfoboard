package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

public class AirlinesRowMapper implements RowMapper<Airline> {

	@Override
	public Airline mapRow(ResultSet rs, int rowNum) throws SQLException {
		Airline airline = new Airline();
		airline.setId(rs.getInt("a.id"));
		airline.setIcaoCode(rs.getString("a.ICAO"));
		airline.setIataCode(rs.getString("a.IATA"));
		airline.setLogo(rs.getString("a.path"));
		Language defaultLanguage = new Language(
				rs.getInt("l.id"), 
				rs.getString("l.name"), 
				rs.getString("l.tag"), 
				rs.getBoolean("l.active"));
		
		Map<Language, String> names = new HashMap<>();
		names.put(defaultLanguage, rs.getString("a18.name"));
		airline.setNames(names);
		return airline;
	}

}

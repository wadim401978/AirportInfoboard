package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;

public class FlightRowMapper implements RowMapper<Flight> {

	@Override
	public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
		Flight flight = new Flight();
		flight.setId(rs.getInt("f.id"));
		flight.setNumber(rs.getInt("f.aviacompany_flight_number"));
		String type = rs.getString("f.type");
		flight.setArrival(type.equals("arrival")?true:false);
		
		Airline airline = new Airline();
		airline.setId(rs.getInt("al.id"));
		airline.setIataCode(rs.getString("al.IATA"));
		airline.setIcaoCode(rs.getString("al.ICAO"));
		airline.setLogo(rs.getString("al.path"));
		
		Language lang = new Language();
		lang.setId(rs.getInt("l.id"));
		lang.setName(rs.getString("l.name"));
		lang.setTag(rs.getString("l.tag"));
		lang.setActive(rs.getBoolean("l.active"));
		
		Map<Language,String> map = new HashMap<>();
		map.put(lang, rs.getString("a18.name"));
		
		Airport airport = new Airport();
		airport.setId(rs.getInt("ap.id"));
		airport.setIataCode(rs.getString("ap.IATA"));
		airport.setIcaoCode(rs.getString("ap.ICAO"));
		airport.setNames(map);
		
		flight.setAirline(airline);
		flight.setAirport(airport);
		return flight;
	}

}

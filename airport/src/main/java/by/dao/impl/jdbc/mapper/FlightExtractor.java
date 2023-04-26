package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.dao.model.common.Language;
import by.dao.model.flight.Airline;
import by.dao.model.flight.Airport;
import by.dao.model.flight.Flight;

public class FlightExtractor implements ResultSetExtractor<Flight> {

	@Override
	public Flight extractData(ResultSet rs) throws SQLException, DataAccessException {
		Flight flight = new Flight();
		Map<Language, String> airportI18n = new HashMap<>();
		Map<Language, String> airlineI18n = new HashMap<>();
		Airline airline = new Airline();
		Airport airport = new Airport();
		while (rs.next()) {
			Language language = new Language();
			language.setId(rs.getInt("l.id"));
			language.setName(rs.getString("l.name"));
			language.setTag(rs.getString("l.tag"));
			language.setActive(rs.getBoolean("l.active"));
			
			flight.setId(rs.getInt("fap.fid"));
			flight.setNumber(rs.getInt("fap.aviacompany_flight_number"));
			String type = rs.getString("fap.type");
			flight.setArrival(type.equals("arrival")?true:false);
			
			airline.setId(rs.getInt("fal.alid"));
			airline.setIataCode(rs.getString("fal.IATA"));
			airline.setIcaoCode(rs.getString("fal.ICAO"));
			airline.setLogo(rs.getString("fal.path"));
			airlineI18n.put(language, rs.getString("fal.alname"));
			airline.setNames(airlineI18n);
			
			airport.setId(rs.getInt("fap.apid"));
			airport.setIataCode(rs.getString("fap.IATA"));
			airport.setIcaoCode(rs.getString("fap.ICAO"));
			airportI18n.put(language, rs.getString("fap.apname"));
			airport.setNames(airportI18n);
			
			flight.setAirline(airline);
			flight.setAirport(airport);

		}
		return flight;
	}

}

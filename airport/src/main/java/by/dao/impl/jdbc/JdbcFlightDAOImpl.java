package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import by.dao.FlightDAO;
import by.dao.impl.jdbc.mapper.FlightExtractor;
import by.dao.impl.jdbc.mapper.FlightRowMapper;
import by.dao.model.flight.Flight;

@Repository
public class JdbcFlightDAOImpl extends JdbcAbstractDao implements FlightDAO {

	@Override
	public Flight read(Integer id) {
		Flight flight = getJdbcTemplate().query(
				getQuery("flight.select.where.id"), 
				new FlightExtractor(), 
				id);
		flight.getAirline().setDefaultLanguageByTag(getDefaultLangTag());
		flight.getAirport().setDefaultLanguageByTag(getDefaultLangTag());
		
		return flight;
	}

	@Override
	public List<Flight> getFlights() {
		return getJdbcTemplate().query(
				getQuery("flight.select.all"), 
				new FlightRowMapper(), 
				getDefaultLangTag());
	}

	@Override
	public Flight getFlightByIcaoNumber(String icao) {
		// TODO Auto-generated method stub
		return null;
	}

}

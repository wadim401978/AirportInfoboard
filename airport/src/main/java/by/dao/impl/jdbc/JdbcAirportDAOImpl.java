package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import by.dao.AirportDAO;
import by.dao.impl.jdbc.mapper.AirportExtractor;
import by.dao.impl.jdbc.mapper.AirportsRowMapper;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

//@Transactional
@Repository
@PropertySource("classpath:initial.properties")
public class JdbcAirportDAOImpl extends JdbcAbstractDao implements AirportDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	
	@Override
	public String readName(Language lang, Airport obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNameByLang(String name, Language lang, Airport obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Airport read(Integer id) {
		return jdbcTemplate.query(
				getQuery("airport.select.where.id"), 
				new AirportExtractor(), 
				id);
	}

	@Override
	public List<Airport> findAirports() {
		return jdbcTemplate.query(
				getQuery("airport.select.all"), 
				new AirportsRowMapper(), 
				 env.getProperty("language.default"));
	}

	@Override
	public Airport findAirportByIcaoCode(String icaoCode) {
		return jdbcTemplate.query(
				getQuery("airport.select.where.ICAO"), 
				new AirportExtractor(), 
				icaoCode);
	}

}

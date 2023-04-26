package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirportDAO;
import by.dao.impl.jdbc.mapper.AirportExtractor;
import by.dao.impl.jdbc.mapper.AirportsRowMapper;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

//@Transactional
@Repository
public class JdbcAirportDAOImpl extends JdbcAbstractDao implements AirportDAO {
	
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
		return getJdbcTemplate().query(
				getQuery("airport.select.where.id"), 
				new AirportExtractor(), 
				id);
	}

	@Override
	public List<Airport> findAirports() {
		return getJdbcTemplate().query(
				getQuery("airport.select.all"), 
				new AirportsRowMapper(), 
				getDefaultLangTag());
	}

	@Override
	public Airport findAirportByIcaoCode(String icaoCode) {
		return getJdbcTemplate().query(
				getQuery("airport.select.where.ICAO"), 
				new AirportExtractor(), 
				icaoCode);
	}

}

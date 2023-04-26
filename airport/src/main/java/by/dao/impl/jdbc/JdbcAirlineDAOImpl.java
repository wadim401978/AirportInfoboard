package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirlineDAO;
import by.dao.impl.jdbc.mapper.AirlineExtractor;
import by.dao.impl.jdbc.mapper.AirlinesRowMapper;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

@Repository
public class JdbcAirlineDAOImpl  extends JdbcAbstractDao implements AirlineDAO {

	@Override
	public String readName(Language lang, Airline obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNameByLang(String name, Language lang, Airline obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Airline read(Integer id) {
		return getJdbcTemplate().query(
				getQuery("airline.select.where.id"), 
				new AirlineExtractor(), 
				id);
	}

	@Override
	public List<Airline> findAirlines() {
		return getJdbcTemplate().query(
				getQuery("airline.select.all"), 
				new AirlinesRowMapper(), 
				getDefaultLangTag());
	}

	@Override
	public Airline findAirlineByIcaoCode(String icaoCode) {
		return getJdbcTemplate().query(
				getQuery("airline.select.where.ICAO"), 
				new AirlineExtractor(), 
				icaoCode);
	}

}

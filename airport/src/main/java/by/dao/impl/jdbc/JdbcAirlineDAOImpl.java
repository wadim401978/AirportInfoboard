package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.AirlineDAO;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

//@Transactional
@Repository
public class JdbcAirlineDAOImpl implements AirlineDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Airline> findAirlines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airline findAirlineByIcaoCode(String icaoCode) {
		// TODO Auto-generated method stub
		return null;
	}

}

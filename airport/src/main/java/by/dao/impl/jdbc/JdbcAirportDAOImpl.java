package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.AirportDAO;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

//@Transactional
@Repository
public class JdbcAirportDAOImpl implements AirportDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Airport> findAirports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport findAirportByIcaoCode(String icaoCode) {
		// TODO Auto-generated method stub
		return null;
	}

}

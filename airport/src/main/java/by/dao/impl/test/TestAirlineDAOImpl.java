package by.dao.impl.test;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirlineDAO;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

@Repository
public class TestAirlineDAOImpl implements AirlineDAO {

	@Override
	public String readName(Language lang, Airline obj) {
		return obj.getNames().get(lang);
	}

	@Override
	public void saveNameByLang(String name, Language lang, Airline obj) {
		obj.getNames().put(lang, name);
	}

	@Override
	public Airline read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getAirlines().get(id);
	}

	@Override
	public List<Airline> findAirlines() {
		return TestDataSet.getInstance().getAirlines();
	}

	@Override
	public Airline findAirlineByIcaoCode(String icaoCode) {
		return TestDataSet.getInstance().getAirlineMap().get(icaoCode);
	}

}

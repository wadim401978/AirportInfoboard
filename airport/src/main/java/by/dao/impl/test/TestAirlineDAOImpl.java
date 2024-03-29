package by.dao.impl.test;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirlineDAO;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

@Repository(value = "TestAirlineDAO")
public class TestAirlineDAOImpl implements AirlineDAO {

	public String readName(Language lang, Airline obj) {
		return obj.getNames().get(lang);
	}

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

	public Airline findAirlineByIcaoCode(String icaoCode) {
		return TestDataSet.getInstance().getAirlineMap().get(icaoCode);
	}


}

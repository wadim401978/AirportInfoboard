package by.dao.impl.test;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirportDAO;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

@Repository
public class TestAirportDAOImpl implements AirportDAO  {

	@Override
	public Airport read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getAirports().get(id);
	}

	@Override
	public String readName(Language lang, Airport obj) {
		return obj.getNames().get(lang);
	}

	@Override
	public void saveNameByLang(String name, Language lang, Airport obj) {
		obj.getNames().put(lang, name);
	}

	@Override
	public List<Airport> findAirports() {
		return TestDataSet.getInstance().getAirports();
	}

	@Override
	public Airport findAirportByIcaoCode(String icaoCode) {
		return TestDataSet.getInstance().getAirportMap().get(icaoCode);
	}
	

}

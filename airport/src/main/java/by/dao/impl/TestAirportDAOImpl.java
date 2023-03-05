package by.dao.impl;

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
	public String getNameByLang(Language lang, Airport obj) {
		return obj.getNames().get(lang);
	}

	@Override
	public void setNameByLang(String name, Language lang, Airport obj) {
		obj.getNames().put(lang, name);
		
	}

	@Override
	public List<Airport> getAirports() {
		return TestDataSet.getInstance().getAirports();
	}
	
	//TODO getAirportByIcaoCode

}

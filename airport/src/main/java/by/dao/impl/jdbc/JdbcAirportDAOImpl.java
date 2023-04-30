package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirportDAO;
import by.dao.impl.jdbc.mapper.AirportsExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

//@Transactional
@Repository
public class JdbcAirportDAOImpl extends JdbcAbstractDao implements AirportDAO {
	
	public JdbcAirportDAOImpl() {
		super();
		setExtractor(new AirportsExtractor());
	}

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
		String query = getQuery("airport.select.all")
				+ getQuery("airport.where.id")
				+ getQuery("airport.order.id");
		
		AirportsExtractor extr = (AirportsExtractor)getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		List<Airport> list = 
				getJdbcTemplate().query(query, extr, id);
		
		return list.isEmpty()?(new Airport()):list.get(0);
	}

	@Override
	public List<Airport> findAirports() {
		String query = getQuery("airport.select.all")
				+ getQuery("airport.order.id");
		
		AirportsExtractor extr = (AirportsExtractor)getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}


}

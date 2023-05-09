package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirportDAO;
import by.dao.impl.jdbc.mapper.AirportsExtractor;
import by.dao.model.flight.Airport;

//@Transactional
@Repository
public class JdbcAirportDAOImpl extends JdbcAbstractDao implements AirportDAO {
	
	public JdbcAirportDAOImpl() {
		super();
		setExtractor(new AirportsExtractor());
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

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("airport.delete.where.id"), id);
	}

	private void deleteNames(Airport obj) {
		// TODO Auto-generated method stub
	}
	
	private void createNames(Airport obj) {
		// TODO Auto-generated method stub
	}


	@Override
	public void create(Airport obj) {
		// TODO Auto-generated method stub
		//TRANSACTIONAL
		createNames(obj);
	}

	@Override
	public void update(Airport obj) {
		// TODO Auto-generated method stub
		//TRANSACTIONAL
		deleteNames(obj);
		createNames(obj);
	}

	
	


}

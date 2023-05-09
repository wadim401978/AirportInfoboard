package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirlineDAO;
import by.dao.impl.jdbc.mapper.AirlinesExtractor;
import by.dao.model.flight.Airline;

@Repository
public class JdbcAirlineDAOImpl  extends JdbcAbstractDao implements AirlineDAO {
	
	public JdbcAirlineDAOImpl() {
		super();
		setExtractor(new AirlinesExtractor());
	}

	@Override
	public Airline read(Integer id) {
		String query = getQuery("airline.select.all")
				+ getQuery("airline.where.id")
				+ getQuery("airline.order.id");
		AirlinesExtractor extr = (AirlinesExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		List<Airline> list = getJdbcTemplate().query(query,extr, id);
		return list.isEmpty()?(new Airline()):list.get(0);
	}

	@Override
	public List<Airline> findAirlines() {
		String query = getQuery("airline.select.all")
				+ getQuery("airline.order.id");
		AirlinesExtractor extr = (AirlinesExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}

	@Override
	public void create(Airline obj) {
		// TODO Auto-generated method stub
		//TRANSACTIONAL
		createNames(obj);
	}

	@Override
	public void update(Airline obj) {
		// TODO Auto-generated method stub
		//TRANSACTIONAL
		deleteNames(obj);
		createNames(obj);
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("airline.delete.where.id"), id);
	}

	private void deleteNames(Airline obj) {
		// TODO Auto-generated method stub
		
	}

	private void createNames(Airline obj) {
		// TODO Auto-generated method stub
	}

}

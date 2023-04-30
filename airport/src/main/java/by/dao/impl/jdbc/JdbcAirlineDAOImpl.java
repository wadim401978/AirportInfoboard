package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.AirlineDAO;
import by.dao.impl.jdbc.mapper.AirlinesExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

@Repository
public class JdbcAirlineDAOImpl  extends JdbcAbstractDao implements AirlineDAO {
	
	public JdbcAirlineDAOImpl() {
		super();
		setExtractor(new AirlinesExtractor());
	}

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


}

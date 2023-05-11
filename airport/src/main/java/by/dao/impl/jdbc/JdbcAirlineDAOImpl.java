package by.dao.impl.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import by.dao.AirlineDAO;
import by.dao.impl.jdbc.mapper.AirlinesExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airline;

@Repository
public class JdbcAirlineDAOImpl  extends JdbcAbstractDao implements AirlineDAO {
	
	private SimpleJdbcInsert insertAirline;

	public JdbcAirlineDAOImpl() {
		super();
		setExtractor(new AirlinesExtractor());
	}
	
	public void setAirlineDataSource(DataSource dataSource) {
		this.insertAirline =  new SimpleJdbcInsert(dataSource);
		this.insertAirline.withTableName("airlines");
		this.insertAirline.usingColumns("IATA", "ICAO").usingGeneratedKeyColumns("id");
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

	private void createNames(Airline obj) {
		String query = getQuery("airentity.in18n.airline.insert");
		String queryTemplate = getQuery("airentity.in18n.values");
		Map<Language, String> names = obj.getNames();
		if (!names.isEmpty()) {
			List<Object[]> batch = getNamesMapBatch(names, obj.getId());
			getJdbcTemplate().batchUpdate(query+queryTemplate, batch);
		}
	}
	
	@Transactional
	@Override
	public void create(Airline obj) {
		setAirlineDataSource(getJdbcTemplate().getDataSource());
		Map<String, String> parameters = new HashMap<>();
		parameters.put("IATA", obj.getIataCode());
		parameters.put("ICAO", obj.getIcaoCode());
		parameters.put("path", obj.getLogo());
		try {
			Number newId = insertAirline.executeAndReturnKey(parameters);
			obj.setId(newId.intValue());
			createNames(obj);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//TODO Log4j
		}
	}

	@Transactional
	@Override
	public void update(Airline obj) {
		try {
			getJdbcTemplate().update(
					getQuery("airline.update"),
					obj.getIataCode(),
					obj.getIcaoCode(),
					obj.getLogo(),
	                obj.getId());
			deleteNames(obj);
			createNames(obj);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// TODO: handle exception = Log4j
		}
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("airline.delete.where.id"), id);
	}

	private void deleteNames(Airline obj) {
		getJdbcTemplate().update(
				getQuery("airentity.airline.in18n.delete"), obj.getId());
	}

}

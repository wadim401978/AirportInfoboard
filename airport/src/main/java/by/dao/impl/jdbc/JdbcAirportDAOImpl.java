package by.dao.impl.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import by.dao.AirportDAO;
import by.dao.impl.jdbc.mapper.AirportsExtractor;
import by.dao.model.common.Language;
import by.dao.model.flight.Airport;

@Repository
public class JdbcAirportDAOImpl extends JdbcAbstractDao implements AirportDAO {
	
	private static final Logger logger = Logger.getLogger(JdbcAirportDAOImpl.class);
	
	private SimpleJdbcInsert insertAirport;
	
	public JdbcAirportDAOImpl() {
		super();
		setExtractor(new AirportsExtractor());
	}

	public void setAirportDataSource(DataSource dataSource) {
		this.insertAirport =  new SimpleJdbcInsert(dataSource);
		this.insertAirport.withTableName("airports");
		this.insertAirport.usingColumns("IATA", "ICAO").usingGeneratedKeyColumns("id");
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
		getJdbcTemplate().update(
				getQuery("airentity.airport.in18n.delete"), obj.getId());
	}
	
	private void createNames(Airport obj) {
		String query = getQuery("airentity.in18n.airport.insert");
		String queryTemplate = getQuery("airentity.in18n.values");
		Map<Language, String> names = obj.getNames();
		if (!names.isEmpty()) {
			List<Object[]> batch = getNamesMapBatch(names, obj.getId());
			getJdbcTemplate().batchUpdate(query+queryTemplate, batch);
		}
	}

	@Transactional
	@Override
	public void create(Airport obj) {
		setAirportDataSource(getJdbcTemplate().getDataSource());
		Map<String, String> parameters = new HashMap<>();
		parameters.put("IATA", obj.getIataCode());
		parameters.put("ICAO", obj.getIcaoCode());
		
		try {
			Number newId = insertAirport.executeAndReturnKey(parameters);
			obj.setId(newId.intValue());
			createNames(obj);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void update(Airport obj) {
		try {
			getJdbcTemplate().update(
					getQuery("airport.update"),
					obj.getIataCode(),
					obj.getIcaoCode(),
	                obj.getId());
			deleteNames(obj);
			createNames(obj);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e.getMessage());
		}
	}

}

package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import by.dao.DepartureDAO;
import by.dao.impl.jdbc.mapper.DeparturesExtractor;
import by.dao.model.flight.Departure;

@Transactional
@Repository
public class JdbcDepartureDAOImpl extends JdbcAbstractDao implements DepartureDAO {

	public JdbcDepartureDAOImpl() {
		super();
		setExtractor(new DeparturesExtractor());
	}

	@Override
	public List<Departure> findAll() {
		String query = getQuery("departure.select.all")
				+ getQuery("departure.order.date");
		DeparturesExtractor extr = (DeparturesExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}

	@Override
	public Departure read(Integer id) {
		String query = getQuery("departure.select.all")
				+ getQuery("departure.where.id")
				+ getQuery("departure.order.id");
		DeparturesExtractor extr = (DeparturesExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		List<Departure> list = getJdbcTemplate().query(query, extr, id);
		
		return list.isEmpty()?(new Departure()):list.get(0);
	}

	@Override
	public void create(Departure obj) {
		getJdbcTemplate().update(
				getQuery("departure.insert"),
				obj.getFlight().getId(),
				obj.getScheduledDate(),
				obj.getStatus().getId(),
				obj.getStatusTime());
	}

	@Override
	public void update(Departure obj) {
		getJdbcTemplate().update(
				getQuery("departure.update"),
				obj.getFlight().getId(),
				obj.getScheduledDate(),
				obj.getStatus().getId(),
				obj.getStatusTime(),
                obj.getId());
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("departure.delete.where.id"), id);

	}

}

package by.dao.impl.jdbc;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.ArrivalDAO;
import by.dao.impl.jdbc.mapper.ArrivalsExtractor;
import by.dao.model.flight.Flight;
import by.dao.model.flight.Arrival;

@Repository
public class JdbcArrivalDAOImpl extends JdbcAbstractDao implements ArrivalDAO {
	
	public JdbcArrivalDAOImpl() {
		super();
		setExtractor(new ArrivalsExtractor());
	}

	@Override
	public List<Arrival> findAll() {
		String query = getQuery("arrival.select.all")
				+ getQuery("arrival.order.date");
		ArrivalsExtractor extr = (ArrivalsExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}

	@Override
	public List<Arrival> findAllByFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Arrival> findByPeriod(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrival findScheduledFlight(Date date, Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrival read(Integer id) {
		String query = getQuery("arrival.select.all")
				+ getQuery("arrival.where.id")
				+ getQuery("arrival.order.id");
		ArrivalsExtractor extr = (ArrivalsExtractor) getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		List<Arrival> list = getJdbcTemplate().query(query, extr, id);
		return list.isEmpty()?(new Arrival()):list.get(0);
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("arrival.delete.where.id"), id);
	}

	@Override
	public void update(Arrival obj) {
			getJdbcTemplate().update(
				getQuery("arrival.update"),
				obj.getFlight().getId(),
				obj.getScheduledDate(),
				obj.getStatus().getId(),
				obj.getStatusTime(),
                obj.getId());
	}

}

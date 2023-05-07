package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.FlightDAO;
import by.dao.impl.jdbc.mapper.FlightsExtractor;
import by.dao.model.flight.Flight;

@Repository
public class JdbcFlightDAOImpl extends JdbcAbstractDao implements FlightDAO {
	
	public JdbcFlightDAOImpl() {
		super();
		setExtractor(new FlightsExtractor());
	}

	@Override
	public Flight read(Integer id) {
		String query = getQuery("flight.select.all")
				+ getQuery("flight.where.id")
				+ getQuery("flight.order.id");
		FlightsExtractor extr = (FlightsExtractor)getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		List<Flight> list = getJdbcTemplate().query(query, extr, id);
		return list.isEmpty()?(new Flight()):list.get(0);
	}

	@Override
	public List<Flight> findFlights() {
		String query = getQuery("flight.select.all")
				+ getQuery("flight.order.id");
		
		FlightsExtractor extr = (FlightsExtractor)getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}
	

	@Override
	public void create(Flight obj) {
		getJdbcTemplate().update(
				getQuery("flight.insert"),
				obj.getNumber(),
				obj.getAirport().getId(),
				obj.getAirline().getId(),
				obj.getType());
	}

	@Override
	public void update(Flight obj) {
		getJdbcTemplate().update(
				getQuery("flight.update"),
				obj.getNumber(),
				obj.getAirport().getId(),
				obj.getAirline().getId(),
				obj.getType(),
                obj.getId());
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("flight.delete.where.id"), id);
	}

	@Override
	public List<Flight> findFlights(boolean isArrival) {
		String query;
		if (isArrival) {
			query = getQuery("flight.select.all")
					+ getQuery("flight.where.arrival")
					+ getQuery("flight.order.id");
		} else {
			query = getQuery("flight.select.all")
					+ getQuery("flight.where.departure")
					+ getQuery("flight.order.id");
		}
		FlightsExtractor extr = (FlightsExtractor)getExtractor();
		extr.setDefaultLangTag(getDefaultLangTag());
		return getJdbcTemplate().query(query, extr);
	}
	
}

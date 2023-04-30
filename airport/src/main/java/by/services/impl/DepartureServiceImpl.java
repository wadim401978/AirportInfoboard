package by.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.DAO;
import by.dao.DepartureDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.Departure;
import by.services.DepartureService;

@Service(value = "DepartureService")
public class DepartureServiceImpl implements DepartureService {
	
	private DepartureDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<Departure> dao) {
		this.dao = (DepartureDAO)dao;
	}

	@Override
	public List<Departure> getAllByFlight(Flight flight) {
		return dao.findAllByFlight(flight);
	}

	@Override
	public List<Departure> getByPeriod(Date startDate, Date endDate) {
		return dao.findByPeriod(startDate, endDate);
	}

	@Override
	public Departure getScheduledFlight(Date date, Flight flight) {
		return dao.findScheduledFlight(date, flight);
	}

	@Override
	public List<Departure> getAll() {
		return dao.findAll();
	}

	@Override
	public void save(Departure obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Departure get(int id) {
		return dao.read(id);
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}

}

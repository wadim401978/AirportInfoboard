package by.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.DAO;
import by.dao.ScheduledDepartureFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledDepartureFlight;
import by.services.ScheduledDepartureFlightService;

@Service(value = "ScheduledDepartureFlightService")
public class ScheduledDepartureFlightServiceImpl implements ScheduledDepartureFlightService {
	
	private ScheduledDepartureFlightDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<ScheduledDepartureFlight> dao) {
		this.dao = (ScheduledDepartureFlightDAO)dao;
	}

	@Override
	public List<ScheduledDepartureFlight> getAllByFlight(Flight flight) {
		return dao.findAllByFlight(flight);
	}

	@Override
	public List<ScheduledDepartureFlight> getByPeriod(Date startDate, Date endDate) {
		return dao.findByPeriod(startDate, endDate);
	}

	@Override
	public ScheduledDepartureFlight getScheduledFlight(Date date, Flight flight) {
		return dao.findScheduledFlight(date, flight);
	}

	@Override
	public List<ScheduledDepartureFlight> getAll() {
		return dao.findAll();
	}

	@Override
	public void save(ScheduledDepartureFlight obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

}

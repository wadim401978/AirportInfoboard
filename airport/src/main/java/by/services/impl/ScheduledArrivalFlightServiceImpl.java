package by.services.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.DAO;
import by.dao.ScheduledArrivalFlightDAO;
import by.dao.model.flight.Flight;
import by.dao.model.flight.ScheduledArrivalFlight;
import by.services.ScheduledArrivalFlightService;

@Service(value = "ScheduledArrivalFlightService")
public class ScheduledArrivalFlightServiceImpl implements ScheduledArrivalFlightService {
	
	private ScheduledArrivalFlightDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<ScheduledArrivalFlight> dao) {
		this.dao = (ScheduledArrivalFlightDAO)dao;
	}

	@Override
	public List<ScheduledArrivalFlight> getAllByFlight(Flight flight) {
		return dao.findAllByFlight(flight);
	}

	@Override
	public List<ScheduledArrivalFlight> getByPeriod(Date startDate, Date endDate) {
		return dao.findByPeriod(startDate, endDate);
	}

	@Override
	public ScheduledArrivalFlight getScheduledFlight(Date date, Flight flight) {
		return dao.findScheduledFlight(date, flight);
	}

	@Override
	public List<ScheduledArrivalFlight> getAll() {
		return dao.findAll();
	}

	@Override
	public void save(ScheduledArrivalFlight obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public ScheduledArrivalFlight get(int id) {
		return dao.read(id);
	}


}

package by.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.FlightDAO;
import by.dao.model.flight.Flight;
import by.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	
	private FlightDAO dao;

	@Autowired
	public void setDao(FlightDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Flight> getAll() {
		return dao.getFlights();
	}

	@Override
	public void save(Flight obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Flight getByIcaoNumber(String icao) {
		return dao.getFlightByIcaoNumber(icao);
	}

}

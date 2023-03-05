package by.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.AirportDAO;
import by.dao.model.flight.Airport;
import by.services.AirportService;

@Service
public class TestAirportServiceImpl implements AirportService {

	private AirportDAO dao;
	
	@Autowired
	public void setDao(AirportDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Airport> getAll() {
		return dao.getAirports();
	}

	@Override
	public void save(Airport obj) {
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}
}

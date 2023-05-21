package by.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.AirportDAO;
import by.dao.DAO;
import by.dao.model.flight.Airport;
import by.services.AirportService;

@Service(value = "AirportService")
public class AirportServiceImpl implements AirportService {

	private AirportDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<Airport> dao) {
		this.dao = (AirportDAO)dao;
		
	}
	
	@Override
	public List<Airport> getAll() {
		return dao.findAirports();
	}

	@Override
	public void save(Airport obj) {
        if (obj.getId() == 0) {
        	dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Airport get(int id) {
		return dao.read(id);
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}
}

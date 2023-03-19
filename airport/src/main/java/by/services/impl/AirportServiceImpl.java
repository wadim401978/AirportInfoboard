package by.services.impl;

import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.AirportDAO;
import by.dao.DAO;
import by.dao.model.flight.Airport;
import by.services.AirportService;

@Service(value = "AirportService")
public class AirportServiceImpl implements AirportService {

	private AirportDAO dao;
	private ResourceBundle initialResourceBundle;
	
	@Override
	@Autowired
	public void setDao(DAO<Airport> dao) {
		this.dao = (AirportDAO)dao;
		
	}
	
	public AirportServiceImpl() {
		this.initialResourceBundle = ResourceBundle.getBundle("initial");
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
	public Airport getByIcaoCode(String icaoCode) {
		return this.dao.findAirportByIcaoCode(icaoCode);
	}

	@Override
	public Airport getDefaultAirport() {
		return getByIcaoCode(initialResourceBundle.getString("airport.base.ICAO"));
	}

	@Override
	public Airport get(int id) {
		return dao.read(id);
	}

	
	
}

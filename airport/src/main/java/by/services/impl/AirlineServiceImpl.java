package by.services.impl;

import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.AirlineDAO;
import by.dao.DAO;
import by.dao.model.flight.Airline;
import by.services.AirlineService;

@Service(value = "AirlineService")
public class AirlineServiceImpl implements AirlineService {
	
	private AirlineDAO dao;
	private ResourceBundle initialResourceBundle;
	
	@Override
	@Autowired
	public void setDao(DAO<Airline> dao) {
		this.dao = (AirlineDAO)dao;
	}

	public AirlineServiceImpl() {
		this.initialResourceBundle = ResourceBundle.getBundle("initial");
	}

	@Override
	public Airline getByIcaoCode(String icaoCode) {
		return dao.findAirlineByIcaoCode(icaoCode);
	}

	@Override
	public List<Airline> getAll() {
		return dao.findAirlines();
	}

	@Override
	public void save(Airline obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Airline getDefaultAirline() {
		return getByIcaoCode(initialResourceBundle.getString("airline.base.ICAO"));
	}

	@Override
	public Airline get(int id) {
		return dao.read(id);
	}
	
	

}

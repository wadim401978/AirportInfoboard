package by.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.DAO;
import by.dao.ArrivalDAO;
import by.dao.model.flight.Arrival;
import by.services.ArrivalService;

@Service(value = "ArrivalService")
public class ArrivalServiceImpl implements ArrivalService {
	
	private ArrivalDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<Arrival> dao) {
		this.dao = (ArrivalDAO)dao;
	}

	@Override
	public List<Arrival> getAll() {
		return dao.findAll();
	}

	@Override
	public void save(Arrival obj) {
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Arrival get(int id) {
		return dao.read(id);
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}
}

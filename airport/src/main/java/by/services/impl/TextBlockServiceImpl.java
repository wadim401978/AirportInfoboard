package by.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.DAO;
import by.dao.TextBlockDAO;
import by.dao.model.infomsg.impl.TextBlock;
import by.services.TextBlockService;

@Service(value = "TextBlockService")
public class TextBlockServiceImpl implements TextBlockService {
	private TextBlockDAO dao;

	@Autowired
	@Override
	public void setDao(DAO<TextBlock> dao) {
		this.dao = (TextBlockDAO)dao;
	}
	
	@Override
	public void save(TextBlock obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}




	@Override
	public List<TextBlock> getAll() {
		return dao.findTextBlocks();
	}

	@Override
	public TextBlock get(int id) {
		return dao.read(id);
	}


}

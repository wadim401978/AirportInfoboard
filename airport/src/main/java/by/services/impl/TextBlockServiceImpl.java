package by.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.DAO;
import by.dao.TextBlockDAO;
import by.dao.model.infomsg.TextBlock;
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
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public List<TextBlock> getAll() {
		return dao.findAllBlocks();
	}

	@Override
	public TextBlock get(int id) {
		return dao.read(id);
	}

	@Override
	public List<TextBlock> getActiveBlocks() {
		List<TextBlock> list = dao.findActiveBlocks();
		if (list==null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}

	@Override
	public TextBlock getNextActiveItem(int id) {
		TextBlock activeBlock = null;
		List<TextBlock> list = getActiveBlocks();
		if (list.isEmpty()) {
			activeBlock = new TextBlock();
			activeBlock.setId(0);
		} else {
			for (TextBlock item : list) {
				if(item.getId() > id) {
					activeBlock = item;
					break;
				}
			}
			
			if (activeBlock == null) {
				activeBlock = list.get(0);
			}
		}
		return activeBlock;
	}

	@Override
	public List<TextBlock> getActiveItems() {
		List<TextBlock> list = dao.findActiveBlocks();
		if (list == null) {
			list = new ArrayList<TextBlock>();
		}
		return list;
	}
}

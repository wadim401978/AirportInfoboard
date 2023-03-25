package by.dao.impl.test;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.dao.TextBlockDAO;
import by.dao.model.infomsg.TextBlock;

@Repository(value = "TextBlockDAO")
public class TestTextBlockDAOImpl implements TextBlockDAO, ActiveItemsDAO<TextBlock> {

	@Override
	public TextBlock read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getInfoBlocks().get(id);
	}

	@Override
	public List<TextBlock> findAllBlocks() {
		return TestDataSet.getInstance().getInfoBlocks();
	}

	@Override
	public List<TextBlock> findActiveBlocks() {
		return findActiveItems(this.findAllBlocks());
	}

}

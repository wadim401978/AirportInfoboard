package by.dao.impl.test;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.dao.TextBlockDAO;
import by.dao.model.infomsg.impl.TextBlock;

@Repository(value = "TextBlockDAO")
public class TestTextBlockDAOImpl implements TextBlockDAO {

	@Override
	public TextBlock read(Integer id) {
		return TestDataSet.getInstance().getInfoBlocks().get(id);
	}

	@Override
	public List<TextBlock> findTextBlocks() {
		return TestDataSet.getInstance().getInfoBlocks();
	}

}

package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.TextBlockDAO;
import by.dao.model.infomsg.TextBlock;

@Transactional
@Repository
public class JdbcTextBlockDAOImpl implements TextBlockDAO {

	@Override
	public List<TextBlock> findAllBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TextBlock> findActiveBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextBlock read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

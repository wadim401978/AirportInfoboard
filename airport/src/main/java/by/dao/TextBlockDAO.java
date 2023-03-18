package by.dao;

import java.util.List;

import by.dao.model.infomsg.impl.TextBlock;

public interface TextBlockDAO extends GenericDAO<Integer, TextBlock> {
	public List<TextBlock> findTextBlocks();
}

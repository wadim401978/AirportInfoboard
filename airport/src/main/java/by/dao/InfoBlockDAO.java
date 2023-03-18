package by.dao;

import java.util.List;

import by.dao.model.infomsg.InfoBlock;

public interface InfoBlockDAO<T extends InfoBlock> extends GenericDAO<Integer, InfoBlock> {
	public List<T> findInfoBlocks();
}

package by.dao;

import java.util.List;

public interface InfoBlockDAO<ID, T> extends GenericDAO<ID, T> {
	public List<T> findAllBlocks();
	public List<T> findActiveBlocks();
}

package by.services;

import java.util.List;

import by.dao.DAO;

public interface Service<T> {
	public void setDao(DAO<T> dao);
	public List<T> getAll();
	public void save(T obj);
	public T get(int id);
}

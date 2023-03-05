package by.services;

import java.util.List;

public interface Service<T> {
	public List<T> getAll();
	public void save(T obj);
}

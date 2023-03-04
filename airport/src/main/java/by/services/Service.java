package by.services;

import java.util.List;

public interface Service<T> {
	public List<T> getAll();
	public void saveInstance(T obj);
}

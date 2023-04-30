package by.services;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.dao.DAO;

public interface Service<T> {
	public void setDao(DAO<T> dao);
	public List<T> getAll();
	public void save(T obj);
	public T get(int id);
	public void remove(int id);
	
	public default void simpleRemoveItems(HttpServletRequest req) {
		Enumeration<String> pidEnum = req.getParameterNames();
		while (pidEnum.hasMoreElements()) {
			String pid = pidEnum.nextElement();
			try {
				int id = Integer.parseInt(pid, 10);
				this.remove(id);
			} catch (Exception e) {
			}
		}
	}
}

package by.services;

import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import by.app.exception.DeleteException;
import by.dao.DAO;

public interface Service<T> {
	public void setDao(DAO<T> dao);
	public List<T> getAll();
	public void save(T obj);
	public T get(int id);
	public void remove(int id);
	
	public default void simpleRemoveItems(HttpServletRequest req) throws DeleteException {
		Enumeration<String> pidEnum = req.getParameterNames();
		while (pidEnum.hasMoreElements()) {
			String pid = pidEnum.nextElement();
			if (!pid.equals("delete0")) {
				int id = Integer.parseInt(pid, 10); 
				try {
					this.remove(id);
				} catch (Exception e) {
					DeleteException de = new DeleteException(); 
					de.setEntityId(id);
					throw de; 
				}
			}
		}
	}
}

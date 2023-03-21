package by.dao.impl.test;

import java.util.ArrayList;
import java.util.List;

import by.dao.model.ActiveEntity;

public interface ActiveItemsDAO<T extends ActiveEntity> {
	
	default public List<T> findActiveItems(List<T> list) {
		List<T> active = new ArrayList<T>();
		for(T item : list) {
			if(item.isActive()) {
				active.add(item);
			}
		}
		return active;
	}
}

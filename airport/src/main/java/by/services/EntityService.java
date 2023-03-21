package by.services;

import java.util.List;
import by.dao.model.Entity;

public interface EntityService<T extends Entity> extends Service<T> {
	public default int[] getIds(List<T> list) {
		int[] array;
		if(list.isEmpty()) {
			array = null;
		} else {
			array = new int[list.size()];
			int i = 0;
			for(T item : list) {
				array[i++] = item.getId();
			}
		}
		return array;
	}
}

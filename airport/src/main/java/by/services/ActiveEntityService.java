package by.services;

import java.util.List;

import by.dao.model.ActiveEntity;

public interface ActiveEntityService<T extends ActiveEntity> extends EntityService<T> {
	public T getNextActiveItem(int id);
	public List<T> getActiveItems();
}

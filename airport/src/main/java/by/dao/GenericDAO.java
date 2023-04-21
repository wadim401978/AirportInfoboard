package by.dao;

public interface GenericDAO<ID, T> extends DAO<T> {
	default void create(T obj) {}

    T read(ID id);

    default void update(T obj) {
    	
    }

    default void delete(ID id) {}

}

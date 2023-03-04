package by.dao;

public interface GenericDAO<ID, T> extends DAO<T> {
    void create(T obj);

    T read(ID id);

    void update(T obj);

    void delete(ID id);

}

package by.services;

public interface AirEntityService<T> extends Service<T> {
	public T getByIcaoCode(String icaoCode);
}

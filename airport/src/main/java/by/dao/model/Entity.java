package by.dao.model;

public abstract class Entity {
	private int id;

	public Entity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

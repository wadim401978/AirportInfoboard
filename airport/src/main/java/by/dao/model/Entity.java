package by.dao.model;

public abstract class Entity {
	private int id;

	public Entity() {
		super();
	}

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "(" + this.getClass().getSimpleName() + "_id = " + getId()+ ") ";
	}
	
	
	
}
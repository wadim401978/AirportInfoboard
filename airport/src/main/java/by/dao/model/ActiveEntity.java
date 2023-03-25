package by.dao.model;

public class ActiveEntity extends Entity {
	private boolean active;

	public ActiveEntity(int id, boolean active) {
		super(id);
		this.active = active;
	}
	
	public ActiveEntity() {
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

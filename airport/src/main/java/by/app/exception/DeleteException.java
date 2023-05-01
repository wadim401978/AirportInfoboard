package by.app.exception;

public class DeleteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int entityId;

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public DeleteException() {
		super("cant.delete.linked");
	}

}

package by.dao.model.flight;

public enum ArrivalStatus {
	EXPECTED(1, "status.arrival.expected"), 
	DELAYED(2, "status.arrival.delay"), 
	LANDED(3, "status.arrival.landed"), 
	CANCELLED(4, "status.arrival.cancelled");
	
	public int id;
	public String property;
	
	public String getProperty() {
		return property;
	}
	
	private ArrivalStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getPropertyById(int id) {
		return property;
	}
	
	private ArrivalStatus(int id, String property) {
		this.id = id;
		this.property = property;
	}

}

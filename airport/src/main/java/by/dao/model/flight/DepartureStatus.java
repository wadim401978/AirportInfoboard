package by.dao.model.flight;

public enum DepartureStatus {
	CHECKIN_AT(1, "status.departure.checkin.at"), 
	CHECKIN_NOW(2, "status.departure.checkin.now"), 
	CHECKIN_FINISHED(3, "status.departure.checkin.finished"), 
	DELAYED(4, "status.departure.delayed.till"), 
	CANCELLED(5, "status.departure.cancelled");
	
	public int id;
	public String property;
	
	public String getProperty() {
		return property;
	}

	private DepartureStatus(int id, String property) {
		this.id = id;
		this.property = property;
	}
	
	public int getId() {
		return id;
	}
}

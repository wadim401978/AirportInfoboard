package by.dao.model.flight;

import by.dao.model.Entity;

public class Flight extends Entity {
    private Airline airline;
    private Airport aiport;
    private int number;
    private boolean isArrival = false;
    
    public Flight(int id, boolean isArrival, Airport aiport, Airline airline, int number) {
		super(id);
		this.airline = airline;
		this.aiport = aiport;
		this.number = number;
		this.isArrival = isArrival;
	}

    public Airport getAiport() {
        return aiport;
    }

    
    public Airline getAirline() {
		return airline;
	}


    public String getIcaoNumber() {
        return getAirline().getIcaoCode() + this.number;
    }

    public String getIataNumber() {
        return getAirline().getIataCode() + this.number;
    }
    

	public boolean isArrival() {
		return isArrival;
	}

	@Override
	public String toString() {
		return (isArrival()?"Arrival":"Departure") + ": " + getIcaoNumber() + "/" + getIataNumber() + ": " + getAiport().toString();
	}

}

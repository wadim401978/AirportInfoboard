package by.dao.model.flight;

import by.dao.model.Entity;

public class Flight extends Entity {
    private Airline airline;
    private Airport airport;
    private int number;
    private boolean isArrival = false;
    
    public Flight(int id, boolean isArrival, Airport airport, Airline airline, int number) {
		super(id);
		this.airline = airline;
		this.airport = airport;
		this.number = number;
		this.isArrival = isArrival;
	}

    public Airport getAirport() {
        return airport;
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
		return super.toString()
				+ (isArrival()?"Arrival":"Departure") 
				+ ": " + getIcaoNumber() + "/" + getIataNumber() + ": " 
				+ getAirline().toStringNames() + "; " 
				+ "(" + getAirport().getIataCode() + ") " + getAirport().toStringNames();
	}

}

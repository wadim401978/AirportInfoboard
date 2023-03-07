package by.dao.model.flight;

import by.dao.model.Entity;

public class Flight extends Entity {
    private Airline airline;
    private Airport aiport;
    private int number;
    
    public Flight(int id, Airport aiport, Airline airline, int number) {
		super(id);
		this.airline = airline;
		this.aiport = aiport;
		this.number = number;
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

	@Override
	public String toString() {
		return getIcaoNumber() + "/" + getIataNumber() + ": " + getAiport().toString();
	}

}

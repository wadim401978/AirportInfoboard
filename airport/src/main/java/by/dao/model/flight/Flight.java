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
    
    public Flight() {
    	
    }

    public Airport getAirport() {
        return airport;
    }
    
    
    
    public int getNumber() {
		return number;
	}

	public Airline getAirline() {
		return airline;
	}

    public String getIcaoNumber() {
        return getAirline().getIcaoCode() + number;
    }

    public String getIataNumber() {
        return getAirline().getIataCode() + number;
    }
    

	public boolean isArrival() {
		return isArrival;
	}
	
	

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setArrival(boolean isArrival) {
		this.isArrival = isArrival;
	}
	
	public void setArrival(String type) {
		this.isArrival = type.equals("arrival")?true:false;
	}
	
	public String getType() {
		return isArrival()?"Arrival":"Departure";
	}

	@Override
	public String toString() {
		return super.toString()
				+ getType() 
				+ ": " + getIcaoNumber() + "/" + getIataNumber() + ": " 
				+ getAirline().getName() + "; " 
				+ "(" + getAirport().getIataCode() + ") " + getAirport().getName();
	}

}

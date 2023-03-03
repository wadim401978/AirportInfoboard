package by.model.flight;

import java.time.LocalTime;

public class Flight {
    private Airline airline;
    private Airport aiport;
    private LocalTime scheduleTime;
    private int number;

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getAiport() {
        return aiport;
    }

    public void setAiport(Airport aiport) {
        this.aiport = aiport;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    private int getNumber() {
        return number;
    }

    public String getIcaoNumber() {
        return getAirline().getIcaoCode() + getNumber();
    }

    public String getIataNumber() {
        return getAirline().getIataCode() + getNumber();
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

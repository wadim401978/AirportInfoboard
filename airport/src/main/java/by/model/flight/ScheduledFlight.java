package by.model.flight;

import by.model.common.Language;

import java.util.Date;
import java.util.Map;

public abstract class ScheduledFlight {
    private Flight flight;
    private Date date;
    private Map<Language, String> comment;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Language, String> getComment() {
        return comment;
    }

    public void setComment(Map<Language, String> comment) {
        this.comment = comment;
    }
}

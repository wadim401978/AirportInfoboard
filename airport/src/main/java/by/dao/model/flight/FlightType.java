package by.dao.model.flight;

public enum FlightType {
    ARRIVAL("arr"), DEPARTURE("dep");

    public String type;

    private FlightType(final String type) {
        this.type = type;
    }
}

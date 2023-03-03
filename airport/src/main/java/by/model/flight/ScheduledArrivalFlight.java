package by.model.flight;

import java.time.LocalTime;

public class ScheduledArrivalFlight extends ScheduledFlight {

    private LocalTime regTime;
    private LocalTime depScheduledTime;

    public LocalTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalTime regTime) {
        this.regTime = regTime;
    }

    public LocalTime getDepScheduledTime() {
        return depScheduledTime;
    }

    public void setDepScheduledTime(LocalTime depScheduledTime) {
        this.depScheduledTime = depScheduledTime;
    }
}

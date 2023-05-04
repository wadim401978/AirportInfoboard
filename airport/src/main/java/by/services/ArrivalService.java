package by.services;

import javax.servlet.http.HttpServletRequest;
import by.dao.model.flight.Arrival;
import by.dao.model.flight.ArrivalStatus;

public interface ArrivalService extends ScheduledFlightService<Arrival> {
	public default Arrival getArrival(HttpServletRequest req) {
		Arrival arrival =  getScheduledFlight(req, new Arrival())  ;
		ArrivalStatus[] statuses = ArrivalStatus.values();
		int statusId = Integer.parseInt(req.getParameter("status"));
		for (ArrivalStatus status : statuses) {
			if (status.getId() == statusId) {
				arrival.setStatus(status);
			}
		}
		arrival.getFlight().setArrival(true);
		return arrival;
	}
}

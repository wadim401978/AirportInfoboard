package by.services;

import javax.servlet.http.HttpServletRequest;

import by.dao.model.flight.Departure;
import by.dao.model.flight.DepartureStatus;

public interface DepartureService extends ScheduledFlightService<Departure> {
	public default Departure getDeparture(HttpServletRequest req) {
		Departure departure = getScheduledFlight(req, new Departure());
		DepartureStatus[] statuses = DepartureStatus.values();
		int statusId = Integer.parseInt(req.getParameter("status"));
		for (DepartureStatus status : statuses) {
			if (status.getId() == statusId) {
				departure.setStatus(status);
			}
		}
		departure.getFlight().setArrival(false);
		return departure;
	}
}

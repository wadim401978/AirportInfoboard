package by.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import by.dao.model.flight.Airline;

public interface AirlineService extends AirEntityService<Airline> {
	public void saveWithUpload(Airline airline, MultipartFile file) throws IOException;
}

package by.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.IIOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import by.app.exception.DeleteException;
import by.dao.AirlineDAO;
import by.dao.DAO;
import by.dao.model.flight.Airline;
import by.services.AirlineService;
import by.services.util.Images;

@Service(value = "AirlineService")
@PropertySource("classpath:initial.properties")
public class AirlineServiceImpl implements AirlineService {
	
	@Autowired
	ServletContext context; 
	
	@Autowired
	private Environment env;
	
	private AirlineDAO dao;
	
	@Override
	@Autowired
	public void setDao(DAO<Airline> dao) {
		this.dao = (AirlineDAO)dao;
	}

	public AirlineServiceImpl() {
	}

	@Override
	public List<Airline> getAll() {
		return dao.findAirlines();
	}

	@Override
	@Transactional
	public void save(Airline obj) {
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public void saveWithUpload(Airline airline, MultipartFile file) throws IOException, IIOException {
		if (!file.isEmpty()) {
			String uploadDir = getUploadDir();
			Path dirPath = Paths.get(uploadDir);
			if(Files.notExists(dirPath)) {
				 new File(uploadDir).mkdir(); 
			}
			
			if (context.getMimeType(file.getOriginalFilename()).startsWith("image/")) {
				try {
					Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
					String extension = Images.getExtention(filePath);
					filePath = Paths.get(uploadDir, 
							"airline" + 
							airline.getId() + 
							"." + 
							extension);
					Files.write(filePath, file.getBytes());
					int width = Integer.parseInt(env.getProperty("target.width"));
					int height = Integer.parseInt(env.getProperty("target.height"));
					Images.resizeImage(filePath, width, height);
					airline.setLogo(filePath.toAbsolutePath().toString());
				} catch (IOException e) {
					throw e;
				}
			} else {
				throw new IIOException(file.getContentType() + " is not an image");
			}
			
		}
		save(airline);
	}
	
	@Override
	public void simpleRemoveItems(HttpServletRequest req) throws DeleteException {
		Enumeration<String> pidEnum = req.getParameterNames();
		while (pidEnum.hasMoreElements()) {
			String pid = pidEnum.nextElement();
			if (!pid.equals("delete0")) {
				int id = Integer.parseInt(pid, 10); 
				try {
					String logoPath = this.get(id).getLogo();
					this.remove(id);
					if (logoPath!=null && !logoPath.trim().equals("")) {
						Files.deleteIfExists(Paths.get(logoPath));
					}
				} catch (Exception e) {
					DeleteException de = new DeleteException(); 
					de.setEntityId(id);
					throw de; 
				}
			}
		}
	}

	@Override
	public Airline get(int id) {
		return dao.read(id);
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}
	
	private String getUploadDir() {
		return System.getProperty("user.dir") + env.getProperty("upload.dir") + "/airlines";
	}

}

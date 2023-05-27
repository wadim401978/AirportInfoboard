package by.app.config;

import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import by.dao.model.flight.Airline;
import by.services.util.Images;

public class RootConfig implements WebMvcConfigurer {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/static/");
        exposeDirectory(Airline.SAVE_DIR, registry);
    }
    
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
    	String uploadPath = Images.getUploadDir(dirName);
    	uploadPath = Paths.get(uploadPath).toUri().getPath();
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file://"+ uploadPath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}

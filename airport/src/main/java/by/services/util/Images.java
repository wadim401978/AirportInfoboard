package by.services.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import java.lang.Integer;

public class Images {
	
	private static final int TARGET_WIDTH = 175;
	private static final int TARGET_HEIGHT = 70;
	
	public static boolean isExists(String logoPath) {
		Path filePath = Paths.get(logoPath);
		return Files.exists(filePath);
	}
	
	public static String getExtention(Path source) {
	    String fileName = source.getFileName().toString();
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	private static Map<String, Integer> getScaledCoords(Map<String, Integer> coords) {
		float targetRatio = (float)coords.get("targetWidth")/coords.get("targetHeight");
		float sourceRatio = (float)coords.get("sourceWidth")/coords.get("sourceHeight");
		int targetWidth, targetHeight;
		
		if (targetRatio > sourceRatio) {
			targetWidth = (int)(sourceRatio * coords.get("targetHeight"));
			targetHeight = coords.get("targetHeight");
		} else if (targetRatio < sourceRatio) {
			targetWidth = coords.get("targetWidth");
			targetHeight = (int)(coords.get("targetWidth") / sourceRatio);
		} else {
			targetWidth = coords.get("targetWidth");
			targetHeight = coords.get("targetHeight");
		}
		
		coords.put("targetWidth", Integer.valueOf(targetWidth));
		coords.put("targetHeight", Integer.valueOf(targetHeight));
		return coords;
	}
	
	public static void resizeImage(Path source)
			throws IOException {
		Map<String, Integer> coords = new HashMap<>();
		coords.put("targetWidth", Integer.valueOf(TARGET_WIDTH));
		coords.put("targetHeight", Integer.valueOf(TARGET_HEIGHT));
		try {
			File targetFile = source.toFile();
			InputStream input = new FileInputStream(targetFile);
			BufferedImage originalImage = ImageIO.read(input); 
			coords.put("sourceWidth", Integer.valueOf(originalImage.getWidth()));
			coords.put("sourceHeight", Integer.valueOf(originalImage.getHeight()));
			coords = getScaledCoords(coords);
			
			Image resultingImage = originalImage.getScaledInstance(
					coords.get("targetWidth"), 
					coords.get("targetHeight"), 
					Image.SCALE_AREA_AVERAGING);
		    BufferedImage outputImage = new BufferedImage(
		    		coords.get("targetWidth"), 
		    		coords.get("targetHeight"), 
		    		BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		    ImageIO.write(outputImage, getExtention(source), targetFile);
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static String getUploadDir(String dirName) {
		ResourceBundle bundle = ResourceBundle.getBundle("initial");
        Path uploadDir;
        String uploadAbsolutePath = bundle.getString("upload.asolute.path").trim();
        String uploadPath;
        if (uploadAbsolutePath.equals("")) {
        	uploadDir = Paths.get(System.getProperty("user.dir") + bundle.getString("upload.dir") + "/" + dirName);
        } else {
        	uploadDir = Paths.get(uploadAbsolutePath, dirName);
        }

        uploadPath = uploadDir.toFile().getAbsolutePath();
        
        Path dirPath = Paths.get(uploadPath);
		if(Files.notExists(dirPath)) {
			 new File(uploadPath).mkdirs(); 
		}
		
		return uploadPath;
	}

}

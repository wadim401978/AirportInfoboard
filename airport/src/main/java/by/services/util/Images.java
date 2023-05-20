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
import javax.imageio.ImageIO;
import java.lang.Integer;

public class Images {
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
	
	public static void resizeImage(Path source, int targetWidth, int targetHeight)
			throws IOException {
		Map<String, Integer> coords = new HashMap<>();
		coords.put("targetWidth", Integer.valueOf(targetWidth));
		coords.put("targetHeight", Integer.valueOf(targetHeight));
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
//			File file = dirPath.toFile();		    
		    ImageIO.write(outputImage, getExtention(source), targetFile);
		} catch (IOException e) {
			throw e;
		}
	}

}

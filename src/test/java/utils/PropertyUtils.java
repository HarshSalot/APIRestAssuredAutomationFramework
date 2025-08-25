package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	 private Properties properties;

	    public PropertyUtils(String filePath) throws IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        properties = new Properties();
	        properties.load(fis);
    }

    // Method to retrieve property by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

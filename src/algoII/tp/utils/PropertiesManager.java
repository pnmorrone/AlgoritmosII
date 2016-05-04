package algoII.tp.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	
	String value = "";
	InputStream inputStream;
	
	public String getPropValues(String key) {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			value = prop.getProperty(key);
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return value;
	}
	
	
	
}

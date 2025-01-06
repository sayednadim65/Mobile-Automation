package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Commons {

	public static String getGlobalPropertiesValue(String key) throws IOException {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("src/config.properties"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return prop.getProperty(key);
	}
}
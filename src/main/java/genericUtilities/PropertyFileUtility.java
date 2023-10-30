package genericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

//comment
/*multi line comment*/
/**
 * This class consists of generic methods to read data from property file
 * @author Arpitha
 *
 */
public class PropertyFileUtility {
	
	/**
	 * this method will read data from property file and return the value to caller
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String readDatFromProperteyFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}
}

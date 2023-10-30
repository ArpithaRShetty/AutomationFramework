package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * This class consists of generic methods related to Java
 * 
 * @author user
 *
 */
public class JavaUtility {
	/**
	 * This method will generate a random number for every run and return it to the caller
	 * @return
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		int r = ran.nextInt(10000);
		return r;
	}
	public String getSystemDate() {
		/**
		 * This method will capture the current date in required format
		 */
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String date = formatter.format(d);
		return date;
	}
}

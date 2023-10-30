package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class will provide implementation to the IRetryAnalyser Interface of TestNG
 * @author user
 *
 */
public class RetryAnalyserImplementation implements IRetryAnalyzer {

	int count=0;
	int retryCount=3;
	
	public boolean retry(ITestResult result) {
	while(count<retryCount) {
		count++;
		return true;
	}
		return false;
	}

}

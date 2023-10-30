package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * this class provides implementation to ITestListener interface of testNG
 * @author user
 *
 */

public class ListenersImplementationClass implements ITestListener {

	ExtentReports reports;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+" === test script execution started === ");
		
		//Create a test script  recognize each @Test
		test = reports.createTest(testScriptName);
	}

	public void onTestSuccess(ITestResult result) {
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+" === passed === ");
		
		//log the success
		test.log(Status.PASS, testScriptName+" ==PASS== ");
	}

	public void onTestFailure(ITestResult result) {
		
		//screenshot
		//exception for failure
		
		String testSriptName = result.getMethod().getMethodName();
		System.out.println(testSriptName+" === failed === ");
		
		//exception for failure
		System.out.println(result.getThrowable());
		
		//log for failure
		test.log(Status.FAIL, testSriptName+" ==FAIL== ");
		test.log(Status.INFO, result.getThrowable());
		
		//screenshot
		String screenShotName = testSriptName+new JavaUtility().getSystemDate();
		
		WebDriverUtility w=new WebDriverUtility();
		try {
			String path = w.captureScreenShot(BaseClass.sdriver, screenShotName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		String testSriptName = result.getMethod().getMethodName();
		System.out.println(testSriptName+" === skipped === ");
		
		System.out.println(result.getThrowable());
		
		//log for skip
		test.log(Status.SKIP, testSriptName+" ==SKIP== ");
		test.log(Status.INFO, result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println(" === suite execution started === ");
		
		//Basic report configuration
		ExtentSparkReporter html=new ExtentSparkReporter(".\\ExtentReport\\Report-"+new JavaUtility().getSystemDate()+".html");
		html.config().setTheme(Theme.DARK);
		html.config().setDocumentTitle("Excecution Report");
		html.config().setReportName("Vtiger execution Report");
		
		reports=new ExtentReports();
		reports.attachReporter(html);
		reports.setSystemInfo("Base Browser", "Firefox");
		reports.setSystemInfo("Base Platform", "Windows");
		reports.setSystemInfo("Base Environment", "Testing");
		reports.setSystemInfo("Reporter Name", "Arpitha");
	}

	public void onFinish(ITestContext context) {
		System.out.println(" === suite execution finished === ");
		
		//Report generation
		reports.flush();
	}
	

}

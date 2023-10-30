package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import objectRepository.LoginPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {
	public static void main(String[] args) throws Throwable {

		// Step 1: Create all the required Objects
		JavaUtility jUtil = new JavaUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		WebDriver driver=null;
		
		//Step 2: Read the Required data
		String BROWSER = pUtil.readDatFromProperteyFile("browser");
		String URL = pUtil.readDatFromProperteyFile("url");
		String USERNAME = pUtil.readDatFromProperteyFile("username");
		String PASSWORD = pUtil.readDatFromProperteyFile("password");
		
		String ORGNAME = eUtil.readDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();
		
		//Step 3:launch the browser
		if(BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println(BROWSER+" launched");
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+" launched");
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			System.out.println(BROWSER+" launched");
		}else {
			System.out.println("Invalod browser name");
		}
		wUtil.maximazeWindow(driver);
		wUtil.waitForPageLoad(driver);
		
		//Step 4: Load the url
		driver.get(URL);
		
		//Step 4: Login to the Application
//		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
//		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
//		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		LoginPage lp= new LoginPage(driver);
//		lp.getuserNameEdt().sendKeys(USERNAME);
//		lp.getpasswordEdt().sendKeys(PASSWORD);
//		lp.getloginBtn().click();
		
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 5: Navigate to the organization
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step 6: Click on the Create Organization look up image
		driver.findElement(By.xpath("//img[contains(@title,'Organization')]")).click();

		// Step 7: Create Organization with mandatory information
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);

		// Step 8: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step 9: Validate
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (OrgHeader.contains(ORGNAME)) {
			System.out.println(OrgHeader);
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		// Step 10: LOgout
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOverAction(driver, signout);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("LogOut Successful");
		}
}

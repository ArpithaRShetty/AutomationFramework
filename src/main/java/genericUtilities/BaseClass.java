package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import objectRepository.HomePage;
import objectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public JavaUtility jUtil = new JavaUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver = null;

	// used in listeners
	public static WebDriver sdriver;

	@BeforeSuite(alwaysRun = true)
	public void bsConfig() {
		System.out.println("DB Connection Successful");
	}

	// @Parameters("Browser")
	// @BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/* String Browser */) throws Throwable {
		String BROWSER = pUtil.readDatFromProperteyFile("browser");
		String URL = pUtil.readDatFromProperteyFile("url");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " launched ");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " launched ");
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + " launched ");
		} else {
			System.out.println("Invalod browser name");
		}
		wUtil.maximazeWindow(driver);
		wUtil.waitForPageLoad(driver);

		sdriver = driver;
		driver.get(URL);
	}

	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable {
		String USERNAME = pUtil.readDatFromProperteyFile("username");
		String PASSWORD = pUtil.readDatFromProperteyFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("Login Successful");
	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() throws InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.logOutOfApp(driver);

		System.out.println("Logout Successful");
	}

	// @AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig() {

		driver.quit();
		System.out.println("Browser Closed");
	}

	@AfterSuite(alwaysRun = true)
	public void asConfig() {
		System.out.println("DB Connection Closed");
	}

}

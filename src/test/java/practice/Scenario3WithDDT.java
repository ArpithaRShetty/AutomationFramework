package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3WithDDT {
	public static void main(String[] args) throws Throwable {
		// Step 1: Read all the necessary data

		/* read data from property file - common data */
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String BROWSER = p.getProperty("browser");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");

		/* read data from excel - test data */
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String ORGNAME = wb.getSheet("Organization").getRow(4).getCell(2).getStringCellValue();
		String INDUSTRY = wb.getSheet("Organization").getRow(4).getCell(3).getStringCellValue();

		WebDriver driver = null;

		// Step 2: launch the browser //Run Time Polymorphism - driver

		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " Launched ");
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + " Launched ");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " Launched ");
		} else {
			System.out.println("Invalid Browser Name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		// Step 3: Load the Application
		driver.get(URL);

		// Step 4: Login to the Application
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Step 5: Navigate to the Organizations Link
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step 6: Click on the Create Organization look up image
		driver.findElement(By.xpath("//img[contains(@title,'Organization')]")).click();

		// Step 7: Create Organization
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);

		// Step 8: Select Chemicals in industry dropdown
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		Select s1 = new Select(industry);
		s1.selectByValue(INDUSTRY);

		// Step 9: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step 10: Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (contactHeader.contains(ORGNAME)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		// Step 11: LOgout
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(signout).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("SignOut Successful");
	}
}

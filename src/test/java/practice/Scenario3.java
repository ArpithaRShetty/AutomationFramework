package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3 {
	public static void main(String[] args) throws Throwable {
		// Step 1: Launch the browser
				WebDriverManager.edgedriver().setup();
				WebDriver driver = new EdgeDriver();
				driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

				// Step 2: Load the Application
				driver.get("http://localhost:8888");

				// Step 3: Login to the Application
				driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
				driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
				driver.findElement(By.xpath("//input[@id='submitButton']")).click();

				// Step 4: Navigate to the Organizations Link
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();

				// Step 5: Click on the Create Organization look up image
				driver.findElement(By.xpath("//img[contains(@title,'Organization')]")).click();

				// Step 6: Create Organization
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("ABC");
				driver.findElement(By.xpath("//input[@name='website']")).sendKeys("https://www.abc.com");
				
				// Step 7: Select Chemicals in industry dropdown
				WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
				Select s1 = new Select(industry);
				s1.selectByValue("Chemicals");


				// Step 8: Save
				driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

				// step 9: Validate
				String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

				if (contactHeader.contains("ABC")) {
					System.out.println("PASS");
				} else {
					System.out.println("FAIL");
				}
				
				//Step 10: LOgout
				WebElement signout = driver.findElement(By.xpath("(//img[contains(@style,'padding')])[1]"));
				Actions a = new Actions(driver);
				a.moveToElement(signout).perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Sign Out")).click();
	}
}


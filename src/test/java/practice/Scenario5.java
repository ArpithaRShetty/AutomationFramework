package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5 {
	public static void main(String[] args) throws Throwable {
		//Step 1: Launch the browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Step 2: Load the Application
		driver.get("http://localhost:8888");
		
		//Step 3: Login to the Application
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		//Step 4: Navigate to the contact Link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step 5: Click on the Create contact look up image
		driver.findElement(By.xpath("//img[contains(@title,'Create')]")).click();
		
		//Step 6: Create Contact
		WebElement fn = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		Select s = new Select(fn);
		s.selectByValue("Ms.");
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("moldy");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("R");
		
		//Step 7: Select the organization 
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		String main = driver.getWindowHandle();
		Set<String> windo = driver.getWindowHandles();
		Actions a = new Actions(driver);
		for (String lv : windo) {
			driver.switchTo().window(lv);
			if(!lv.equals(main)){
			driver.findElement(By.xpath("//a[text()='ABC']")).click();
			Thread.sleep(3000);
			}
		}
		driver.switchTo().window(main);
		Thread.sleep(3000);
		
		// Step 7: Save
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		
		//step 8: Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains("moldy")){
			System.out.println("PASS");
		}else {
			System.out.println("FAIL");
		}
		
		//Step 9: LOgout
		WebElement signout = driver.findElement(By.xpath("(//img[contains(@style,'padding')])[1]"));
		a.moveToElement(signout).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("SignOut successful");
	}
}

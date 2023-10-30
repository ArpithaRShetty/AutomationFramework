package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class calenderPopupAnyDateinDOM {
	public static void main(String[] args) {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.makemytrip.com/");
		
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		driver.findElement(By.xpath("//input[@id='fromCity']")).sendKeys("mumbai");
		driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
		
		driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("ben");
		driver.findElement(By.xpath("//p[text()='Bengaluru, India']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Wed Oct 04 2023']")).click();
	}
}
/*
 * directly navigate to departure
 * driver.findElement(By.xpath("//label[@for='departure']")).click();
 */

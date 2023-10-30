package practice;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderpopupCurrentDate {
	public static void main(String[] args) {
		
		Date d = new Date();
		String dateNow = d.toString();
		System.out.println(dateNow);
		String[] dArr = d.toString().split(" ");
		String currentDate = dArr[0]+" "+dArr[1]+" "+dArr[2]+" "+dArr[5];
		System.out.println(currentDate);
		
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
		driver.findElement(By.xpath("//div[@aria-label='"+currentDate+"']")).click();
	}
}


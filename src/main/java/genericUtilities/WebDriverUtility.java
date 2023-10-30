package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all generic methods related to web driver actions
 * 
 * @author Arpitha
 *
 */
public class WebDriverUtility {
	/**
	 * This method will maximize the window
	 * 
	 * @param driver
	 */
	public void maximazeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize the window
	 * 
	 * @param driver
	 */
	public void miniimazeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will wait for the page to load
	 * 
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * This method will wait for a particular element to be visible in the DOM
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait for a particular element to be clickable in the DOM
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will handle dropdown by index
	 * 
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {

		Select s = new Select(element);
		s.selectByIndex(index);
	}

	/**
	 * This method will handle dropdown by value
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {

		Select s = new Select(element);
		s.selectByValue(value);
	}

	/**
	 * This method will handle dropdown by value
	 * 
	 * @param value
	 * @param element
	 */
	public void handleDropDown(String text, WebElement element) {

		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	/**
	 * This method will perform mouse overing action
	 * 
	 * @param driver
	 * @param target
	 */
	public void mouseOverAction(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}

	/**
	 * This method will perform drag and drop action
	 * 
	 * @param driver
	 * @param source
	 * @param target
	 */
	public void dragAndDropAction(WebDriver driver, WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target);
	}

	/**
	 * This method will move the cursor based on offset and click on web page
	 * 
	 * @param driver
	 * @param element
	 */
	public void moveAndClick(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.moveByOffset(10, 10).click().perform();
	}

	/**
	 * This method will perform right click
	 * 
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions a = new Actions(driver);
		a.contextClick().perform();
	}

	/**
	 * This method will scroll down by 500 units
	 * 
	 * @param driver
	 */
	public void scrollDownAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollby(0,500);", "");
	}

	/**
	 * This method will scroll up by 500 units
	 * 
	 * @param driver
	 */
	public void scrollUpAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollby(0,-500);", "");
	}

	/**
	 * This method will handle the frame by index
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method will handle the frame by name or ID
	 * 
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * This method will handle the frame by web element
	 * 
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method will accept the alert popup
	 * 
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method will cancel the alert popup
	 * 
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method will fetch the alert text and return it to caller
	 * 
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	/**
	 * This method will take screenshot and returns the destination path
	 * 
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\screenshot\\" + screenShotName + ".png");
		Files.copy(src, dst);
		return dst.getAbsolutePath();// used for extent reports
	}

	/**
	 * This method will switch 
	 * @param driver
	 * @param partialwinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialwinTitle) {
		// Step 1: get all the window IDs
		Set<String> allwinIDs = driver.getWindowHandles();

		// Step 2:Navigate thru each window
		for (String winID : allwinIDs) {
			// Step 3: switch to each window and capture the title
			String actTitle = driver.switchTo().window(winID).getTitle();

			// Step 4: Compare act title with expected partial title
			if (actTitle.contains(partialwinTitle)) {
				break;
			}
		}
	}
}

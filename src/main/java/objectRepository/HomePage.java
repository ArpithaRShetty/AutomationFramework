package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility { // Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contactLnk;

	@FindBy(xpath = "//a[text()='Organizations']")
	private WebElement organizationLnk;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administartorLnk;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;

	// Rule 3 - Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getAdministartorLnk() {
		return administartorLnk;
	}

	public WebElement getSignOutBtn() {
		return signOutLnk;
	}

	// Business Library - generic methods according to the need of project
	/**
	 * This method will click on organization link
	 */
	public void clickOnOrganizationsLink() {
		organizationLnk.click();
	}

	/**
	 * This method will click on contacts link
	 */
	public void clickOnContactsLink() {
		contactLnk.click();
	}

	/**
	 * This method will logout of application
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public void logOutOfApp(WebDriver driver) throws InterruptedException {
		mouseOverAction(driver, administartorLnk);
		Thread.sleep(1000);
		signOutLnk.click();
	}
}

package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {// Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement CreateOrgLookUpImg;

	// Rule 3 - Initialization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getCreateOrgLookUpImg() {
		return CreateOrgLookUpImg;
	}

	// Business Library - generic methods according to the need of project
	/**
	 * This method will click on click on Organization LookUp Image
	 */
	public void clickOnOrganizationLookUpImg() {
		CreateOrgLookUpImg.click();
	}
}

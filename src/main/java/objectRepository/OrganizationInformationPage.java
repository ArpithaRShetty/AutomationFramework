package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {// Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement OrgHeaderText;

	// Rule 3 - Initialization
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getHeaderTxt() {
		return OrgHeaderText;
	}

	// Business library
	/**
	 * This method will capture the header text and return it to caller
	 */
	public String getOrgHeaderText() {
		return OrgHeaderText.getText();
	}
}

package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {// Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderText;

	// Rule 3 - Initialization
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getContInfoTxt() {
		return contactHeaderText;
	}

	// Business library
	/**
	 * This method will capture the header text and return it to caller
	 */
	public String getcontInfoText() {
		return contactHeaderText.getText();
	}

}

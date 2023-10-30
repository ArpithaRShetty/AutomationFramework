package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {// Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;

	@FindBy(name = "industry")
	private WebElement IndustryDrpDown;

	@FindBy(name = "accounttype")
	private WebElement TypeDropDown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Rule 3 - Initialization
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getOrgNametb() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getIndustryDrpDown() {
		return IndustryDrpDown;
	}

	public WebElement getTypeDropDown() {
		return TypeDropDown;
	}

	// Business Library - generic methods according to the need of project
	/**
	 * This method will create new organization with mandatory fields
	 * 
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME) {
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}

	/**
	 * This method will create new organization with industry drop down
	 * 
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrganization(String ORGNAME, String INDUSTRY) {
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(IndustryDrpDown, INDUSTRY);
		saveBtn.click();
	}

	/**
	 * This method will create new organization with industry drop down and Type
	 * drop down
	 * 
	 * @param ORGNAME
	 * @param INDUSTRY
	 * @param TYPE
	 */
	public void createNewOrganization(String ORGNAME, String INDUSTRY, String TYPE) {
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(IndustryDrpDown, INDUSTRY);
		handleDropDown(TypeDropDown, TYPE);
		saveBtn.click();
	}
}

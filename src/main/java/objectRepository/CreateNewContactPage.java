package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {// Rule 1

	// Rule 2 - declaration
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "(//img[@alt='Select'])[1]")
	private WebElement OrgLookUpImg;

	@FindBy(name = "search_text")
	private WebElement OrgSearchEdt;

	@FindBy(name = "search")
	private WebElement OrgSearchBtn;

	// Rule 3 - Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getLastNameTxt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}

	public WebElement getOrgSearchEdt() {
		return OrgSearchEdt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}

	// business library
	/**
	 * This method will create contact with mandatory fields and save
	 * 
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME) {
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}

	/**
	 * This method will create contact with Organization
	 * 
	 * @param driver
	 * @param LASTNAME
	 * @param ORGNAME
	 */
	public void createNewContact(WebDriver driver, String LASTNAME, String ORGNAME) {
		lastNameEdt.sendKeys(LASTNAME);
		OrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		OrgSearchEdt.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		driver.findElement(By.xpath("//a[.='" + ORGNAME + "']")).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
	}
}

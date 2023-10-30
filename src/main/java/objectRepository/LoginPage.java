package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { // Rule 1

	// Rule 2 - declaration
	@FindBy(name = "user_name")
	private WebElement userNameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindAll({ @FindBy(id = "submitButton"), @FindBy(xpath = "//input[@type='submit']") })
	private WebElement loginBtn;

	// Rule 3 - Initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Rule 4 - Utilization
	public WebElement getuserNameEdt() {
		return userNameEdt;
	}

	public WebElement getpasswordEdt() {
		return passwordEdt;
	}

	public WebElement getloginBtn() {
		return loginBtn;
	}

	// Business Library - generic methods according to the need of project
	public void loginToApp(String USERNAME, String PASSWORD) {
		userNameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();
	}
}
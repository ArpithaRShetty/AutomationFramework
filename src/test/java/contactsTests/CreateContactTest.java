package contactsTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class CreateContactTest extends BaseClass{

	@Test(groups= {"SmokeSuite","RegressionSuite"})
	public void createcont() throws Throwable {
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();
		Reporter.log("Clicked on contacts link");
		
		Thread.sleep(10000);
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUoImg();
		//Assert.fail();
		Reporter.log("Clicked on create contact look up image");
		
		CreateNewContactPage cnop = new CreateNewContactPage(driver);
		cnop.createNewContact(LASTNAME);
		Reporter.log("contact created successfully");
		
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getcontInfoText();
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		Reporter.log("Header captured");
	}
	

}

package contactsTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateContactwithOrganizationTest extends BaseClass {
	@Test(groups="SmokeSuite")
	public void createContactWithOrgTest() throws Throwable {

		String ORGNAME = eUtil.readDataFromExcel("Contacts", 7, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 7, 2);

		// Step 6: Click on Organization
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		// Step 7: click on create organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImg();

		// Step 8: Create new organization with mandatory fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);

		// Step 9: validate for organization
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrgHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader);

		// Step 10:click on contact link
		hp.clickOnContactsLink();

		// step 11: click on create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUoImg();

		// Step 12: create contact with organization
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);

		// Step 13: Validation
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getcontInfoText();
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
	}
}

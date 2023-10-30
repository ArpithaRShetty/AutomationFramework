package practice;

import org.testng.annotations.Test;

public class TestNGPractice {
	@Test
	public void ceateCustomer() {
		System.out.println("create");
	}
	
	@Test(dependsOnMethods="ceateCustomer")
	public void modifyCustomer() {
		System.out.println("modify");
	}
	
	@Test
	public void deleteCustomer() {
		System.out.println("delete");
	}
}

package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {
	
	@Test
	public void practice() {
		
		System.out.println("step 1");
		System.out.println("step 2");
		
		Assert.assertEquals(false, true);   //check 1==1
		
		System.out.println("step 3");
		System.out.println("step 4");
	}
	
	@Test
	public void practice1() {
		SoftAssert sa = new SoftAssert();
		System.out.println("step 1");
		System.out.println("step 2");
		
		sa.assertEquals(true, false);// failed
		
		System.out.println("step 3");
		System.out.println("step 4");
		
		Assert.assertEquals(false, true); //failed
		
		sa.assertEquals("A", "A");// passed
		sa.assertAll(); 
		
	}

}

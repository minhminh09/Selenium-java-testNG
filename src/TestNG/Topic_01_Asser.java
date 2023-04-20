package TestNG;

import org.testng.Assert;

public class Topic_01_Asser {

	public static void main(String[] args) {
		// Thư viện Assert
		String fullName="Automation testing";
		//Mong đợi 1 điều kiện trả về là đúng (true)
		Assert.assertTrue(fullName.contains("Automation"));
		
		//Mong đợi 1 điều kiện trả về là đúng (Fail)
		Assert.assertFalse(fullName.contains("Manual"));
		
		//Mong đợi 2 điều kiện bằng nhau
		//Expect result
		//Actual result
		Assert.assertEquals(fullName, "Automation testing");

	}

}

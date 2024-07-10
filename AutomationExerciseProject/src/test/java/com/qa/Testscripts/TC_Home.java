package com.qa.Testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Home extends TestBase {

	@Test(dataProvider = "getData")
	public void homeContentsVisible(HashMap<String, String> input) throws InterruptedException, IOException {
		String homeTitle = driver.getTitle();
		// Assertion
		Assert.assertEquals(homeTitle, input.get("expectedHomeTitle"));
	}

	@Test
	public void beforeSignupLogin() {
		Assert.assertTrue(home.getSignUpLoginCTA().isDisplayed());
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\HomepageTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

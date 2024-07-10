package com.qa.Testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_SignUp extends TestBase {

	static String name;
	static String emailAddress = utility.randomEmail();
	WebElement title;
	static String SelectedCountry;
	static String SelectedTtile;

	@Test(dataProvider = "getSignUpData")
	public void signupWithNewUserCredentials(HashMap<String, String> input) throws InterruptedException {

		home.getSignUpLoginCTA().click();

		// Assertion
		String signupLoginPageTitle = driver.getTitle();
		Assert.assertEquals(signupLoginPageTitle, input.get("expectedSignupLoginPageTitle"));

		name = input.get("name");
		login.getSignUpNameField().sendKeys(name);

		login.getSignUpEmailField().sendKeys(emailAddress);
		// System.out.println("signup email: " + emailAddress);

		login.getSignUpBtn().click();

		// Assertion
		String signupPageTitle = driver.getTitle();
		Assert.assertEquals(signupPageTitle, input.get("expectedSignupPageTitle"));

		title = signup.getTitle();
		title.click();
		SelectedTtile = title.getAttribute("value");

		// Assertion
		Assert.assertEquals(signup.getName().getAttribute("value"), name);

		String emailId = signup.getEmail().getAttribute("value");
		Assert.assertEquals(emailId, emailAddress);

		signup.getPassword().sendKeys(input.get("password"));

		select = new Select(signup.getDay());
		select.selectByIndex(2);

		select = new Select(signup.getMonth());
		select.selectByValue(input.get("month"));

		select = new Select(signup.getYear());
		select.selectByVisibleText(input.get("year"));

		utility.scroll(signup.getFirstName());
		utility.sleep();

		signup.getFirstName().sendKeys(input.get("fName"));
		signup.getLastName().sendKeys(input.get("lName"));
		signup.getAddress1().sendKeys(input.get("address1"));
		signup.getAddress2().sendKeys(input.get("address2"));

		utility.scroll(signup.getCountry());
		utility.sleep();

		select = new Select(signup.getCountry());
		select.selectByValue("Canada");
		SelectedCountry = select.getFirstSelectedOption().getText();

		signup.getState().sendKeys(input.get("state"));
		signup.getCity().sendKeys(input.get("city"));
		signup.getZipcode().sendKeys(input.get("zipcode"));
		signup.getMobileNumber().sendKeys(input.get("mobileNo"));
		signup.getCreateAccountBtn().click();

	}

	@Test(dependsOnMethods = { "signupWithNewUserCredentials" }, dataProvider = "getSignUpData")
	public void accountCreated(HashMap<String, String> inputValue) throws IOException, InterruptedException {
		// Assertion
		String accountCreatedTitle = driver.getTitle();
		Assert.assertEquals(accountCreatedTitle, inputValue.get("expectedAccountCreatedTitle"));
		account.getContinueBtn().click();
	}

	@Test(dependsOnMethods = { "accountCreated" }, dataProvider = "getSignUpData")
	public void afterSignup(HashMap<String, String> inputData) {
		// Assertion
		Assert.assertTrue(home.getDeleteAccount().isDisplayed());
		Assert.assertTrue(home.getLogoutCTA().isDisplayed());
		Assert.assertEquals(home.getLoginText().getText(), "Logged in as " + inputData.get("name"));
		// System.out.println(home.getLoginText().getText());
	}

	@Test(dependsOnMethods = { "afterSignup" })
	public void signupLogout() {
		utility.logout();
	}

	@DataProvider
	public Object[][] getSignUpData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\SignUpTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

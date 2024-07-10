package com.qa.Testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Login extends TestBase {

	TC_SignUp register = new TC_SignUp();
	private HashMap<String, String> currentInput;

	@Test(priority = 0, dataProvider = "getLoginData")
	public void loginWithInvalidCredentials(HashMap<String, String> input) throws IOException {

		this.currentInput = input;
		String incorrectEmail = utility.randomEmail();
		login.getLoginEmail().sendKeys(incorrectEmail);
		// System.out.println("Incorrect email: " + incorrectEmail);
		login.getLoginPassword().sendKeys(input.get("password"));
		login.getLoginBtn().click();

		Assert.assertTrue(login.getErrorLoginMsg().isDisplayed());
	}

	@Test(priority = 1)
	public void signUpWithExistingCredentials() throws IOException {
		String existEmail = TC_SignUp.emailAddress;
		login.getSignUpNameField().sendKeys(this.currentInput.get("name"));
		login.getSignUpEmailField().sendKeys(existEmail);
		login.getSignUpBtn().click();
		Assert.assertTrue(login.getErrorSignupMsg().isDisplayed());
	}

	@Test(priority = 2)
	public void loginWithValidCredentials() {
		// System.out.println("Login email: " + TC_SignUp.emailAddress);
		login.getLoginEmail().sendKeys(TC_SignUp.emailAddress); // Use the stored email
		login.getLoginPassword().sendKeys(this.currentInput.get("password"));
		login.getLoginBtn().click();
	}

	@Test(dependsOnMethods = { "loginWithValidCredentials" })
	public void afterLogin() {
		// Assertion
		Assert.assertTrue(home.getDeleteAccount().isDisplayed());
		Assert.assertTrue(home.getLogoutCTA().isDisplayed());
		Assert.assertEquals(home.getLoginText().getText(), "Logged in as " + register.name);
	}

	@DataProvider
	public Object[][] getLoginData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\LogintTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

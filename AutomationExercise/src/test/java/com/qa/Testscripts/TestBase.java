package com.qa.Testscripts;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.qa.Pages.AccountCreationPage;
import com.qa.Pages.CartPage;
import com.qa.Pages.CheckoutPage;
import com.qa.Pages.HomePage;
import com.qa.Pages.LoginPage;
import com.qa.Pages.OrderConfirmationPage;
import com.qa.Pages.PaymentPage;
import com.qa.Pages.ProductDetailPage;
import com.qa.Pages.SearchProductPage;
import com.qa.Pages.SignUpPage;

public class TestBase {

	protected static WebDriver driver;
	static HomePage home;
	static SignUpPage signup;
	static LoginPage login;
	static AccountCreationPage account;
	static TC_SignUp tcSignUp;
	static TestUtilities utility;
	static SearchProductPage searchPage;
	static ProductDetailPage prdDetailPage;
	static CartPage cart;
	static CheckoutPage checkout;
	static PaymentPage payment;
	static OrderConfirmationPage confirmOrder;
	static Actions action;
	Select select;

	@BeforeSuite
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home = new HomePage(driver);
		signup = new SignUpPage(driver);
		login = new LoginPage(driver);
		account = new AccountCreationPage(driver);
		tcSignUp = new TC_SignUp();
		utility = new TestUtilities();
		searchPage = new SearchProductPage(driver);
		prdDetailPage = new ProductDetailPage(driver);
		cart = new CartPage(driver);
		checkout = new CheckoutPage(driver);
		payment = new PaymentPage(driver);
		confirmOrder = new OrderConfirmationPage(driver);
		action = new Actions(driver);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}

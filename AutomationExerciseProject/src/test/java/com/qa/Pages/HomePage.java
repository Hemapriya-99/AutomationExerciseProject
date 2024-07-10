package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(linkText = "Signup / Login")
	WebElement signUpLoginCTA;

	@FindBy(xpath = "//a[@href='/logout']")
	WebElement logoutCTA;

	@FindBy(xpath = "//a[@href='/delete_account']")
	WebElement deleteAccount;

	@FindBy(xpath = "//li[10]//a")
	WebElement loginText;

	@FindBy(xpath = "//a[@href='/products']")
	WebElement products;

	@FindBy(xpath = "//a[text()=' Cart']")
	WebElement cartCTA;

	public WebElement getCartCTA() {
		return cartCTA;
	}

	public WebElement getProducts() {
		return products;
	}

	public WebElement getSignUpLoginCTA() {
		return signUpLoginCTA;
	}

	public WebElement getLogoutCTA() {
		return logoutCTA;
	}

	public WebElement getDeleteAccount() {
		return deleteAccount;
	}

	public WebElement getLoginText() {
		return loginText;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

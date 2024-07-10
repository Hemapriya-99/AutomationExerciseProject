package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement loginEmail;

	@FindBy(xpath = "//input[@data-qa='login-password']")
	WebElement loginPassword;

	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@name='name']")
	WebElement signUpNameField;

	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement signUpEmailField;

	@FindBy(xpath = "//button[text()='Signup']")
	WebElement signUpBtn;

	@FindBy(xpath = "//p[contains(text(), 'incorrect')]")
	WebElement errorLoginMsg;

	@FindBy(xpath = "//p[contains(text(), 'exist')]")
	WebElement errorSignupMsg;

	public WebElement getErrorLoginMsg() {
		return errorLoginMsg;
	}

	public WebElement getErrorSignupMsg() {
		return errorSignupMsg;
	}

	public WebElement getLoginEmail() {
		return loginEmail;
	}

	public WebElement getLoginPassword() {
		return loginPassword;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public WebElement getSignUpNameField() {
		return signUpNameField;
	}

	public WebElement getSignUpEmailField() {
		return signUpEmailField;
	}

	public WebElement getSignUpBtn() {
		return signUpBtn;
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationPage {

	WebDriver driver;

	@FindBy(xpath = "//a[text()='Continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//h2//b")
	WebElement accountCreatedTitle;

	public WebElement getContinueBtn() {
		return continueBtn;
	}

	public WebElement getAccountCreatedTitle() {
		return accountCreatedTitle;
	}

	public AccountCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

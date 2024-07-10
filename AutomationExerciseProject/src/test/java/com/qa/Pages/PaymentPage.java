package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@name='name_on_card']")
	WebElement cardholderName;

	@FindBy(xpath = "//input[@name='card_number']")
	WebElement cardNumber;

	@FindBy(xpath = "//input[@name='cvc']")
	WebElement cvc;

	@FindBy(xpath = "//input[@name='expiry_month']")
	WebElement expiryMonth;

	@FindBy(xpath = "//input[@name='expiry_year']")
	WebElement expiryYear;

	@FindBy(id = "submit")
	WebElement confirmOrderButton;

	@FindBy(xpath = "//section[@id='form']//p")
	WebElement confirmOrderMessage;

	public WebElement getCardholderName() {
		return cardholderName;
	}

	public WebElement getCardNumber() {
		return cardNumber;
	}

	public WebElement getCvc() {
		return cvc;
	}

	public WebElement getExpiryMonth() {
		return expiryMonth;
	}

	public WebElement getExpiryYear() {
		return expiryYear;
	}

	public WebElement getConfirmOrderButton() {
		return confirmOrderButton;
	}

	public WebElement getConfirmOrderMessage() {
		return confirmOrderMessage;
	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

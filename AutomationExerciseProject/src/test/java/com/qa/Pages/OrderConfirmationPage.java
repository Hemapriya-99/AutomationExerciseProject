package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

	WebDriver driver;

	@FindBy(xpath = "//h2[@data-qa='order-placed']//b")
	WebElement orderConfirmHeader;

	@FindBy(xpath = "//section[@id='form']//p")
	WebElement confirmationMessage;

	@FindBy(xpath = "//a[text()='Download Invoice']")
	WebElement invoiceButton;

	public WebElement getOrderConfirmHeader() {
		return orderConfirmHeader;
	}

	public WebElement getConfirmationMessage() {
		return confirmationMessage;
	}

	public WebElement getInvoiceButton() {
		return invoiceButton;
	}

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

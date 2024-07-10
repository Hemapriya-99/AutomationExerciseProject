package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

	@FindBy(xpath = "//td[@class='cart_quantity']//button")
	WebElement cartQty;

	@FindBy(xpath = "//a[text()='Proceed To Checkout']")
	WebElement proceedToCheckoutBtn;

	@FindBy(xpath = "//u[text()='Register / Login']")
	WebElement regLoginLink;

	@FindBy(xpath = "//td[@class='cart_price']")
	WebElement price;

	@FindBy(xpath = "//td[@class='cart_total']")
	WebElement total;

	public WebElement getTotal() {
		return total;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getCartQty() {
		return cartQty;
	}

	public WebElement getProceedToCheckoutBtn() {
		return proceedToCheckoutBtn;
	}

	public WebElement getRegLoginLink() {
		return regLoginLink;
	}

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

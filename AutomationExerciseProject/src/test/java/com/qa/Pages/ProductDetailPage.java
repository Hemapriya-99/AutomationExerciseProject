package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

	WebDriver driver;

	@FindBy(xpath = "//div[@class='product-details']//h2")
	WebElement productName;

	@FindBy(xpath = "//button[@class='btn btn-default cart']")
	WebElement addToCartBtn;

	@FindBy(xpath = "//u[text()='View Cart']")
	WebElement viewCartBtnPDP;

	public WebElement getViewCartBtnPDP() {
		return viewCartBtnPDP;
	}

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

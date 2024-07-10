package com.qa.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Testscripts.TestBase;

public class CheckoutPage extends TestBase {

	WebDriver driver;

	@FindBy(xpath = "//a[text()='Place Order']")
	WebElement placeOrderBtn;

	@FindBy(xpath = "//h2[text()='Review Your Order']")
	WebElement reviewOrderHeader;

	@FindBy(xpath = "//td[@class='cart_description']//h4")
	List<WebElement> prdList;

	@FindBy(xpath = "//textarea")
	WebElement commentSection;

	@FindBy(xpath = "//ul[@id='address_delivery']//li")
	List<WebElement> deliveryAddressList;

	@FindBy(xpath = "//ul[@id='address_invoice']//li")
	List<WebElement> billingAddressList;

	@FindBy(xpath = "//h4//a")
	WebElement prdName;

	@FindBy(xpath = "//td[@class=\"cart_price\"]//p")
	WebElement price;

	@FindBy(xpath = "//td[@class=\"cart_quantity\"]//button")
	WebElement quantity;

	@FindBy(xpath = "//td[@class=\"cart_total\"]//p")
	WebElement totalPrice;

	@FindBy(xpath = "//td[@class='cart_total']//p")
	List<WebElement> priceList;

	@FindBy(xpath = "//td[4]//p[@class='cart_total_price']")
	WebElement cartTotalPrice;

	public List<WebElement> getDeliveryAddressList() {
		return deliveryAddressList;
	}

	public List<WebElement> getBillingAddressList() {
		return billingAddressList;
	}

	public WebElement getPlaceOrderBtn() {
		return placeOrderBtn;
	}

	public WebElement getCommentSection() {
		return commentSection;
	}

	public WebElement getReviewOrderHeader() {
		return reviewOrderHeader;
	}

	public WebElement getPrdName() {
		return prdName;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getCartTotalPrice() {
		return cartTotalPrice;
	}

	public WebElement getTotalPrice() {
		return totalPrice;
	}

	public List<WebElement> getPriceList() {
		return priceList;
	}

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

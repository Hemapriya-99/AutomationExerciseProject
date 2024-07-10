package com.qa.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage {

	WebDriver driver;

	@FindBy(id = "search_product")
	WebElement searchBar;

	@FindBy(id = "submit_search")
	WebElement searchIcon;

	@FindBy(xpath = "//div[@class='productinfo text-center']//p")
	List<WebElement> productList;

	@FindBy(xpath = "//a[contains(text(),'View Product')]")
	List<WebElement> viewProductCTA;

	@FindBy(xpath = "//h2[@class='title text-center']")
	WebElement searchTitle;

	@FindBy(linkText = "Add to cart")
	List<WebElement> plpAddToCartBtn;

	@FindBy(xpath = "//button[text()='Continue Shopping']")
	WebElement continueShoppingBtn;

	@FindBy(xpath = "//h2[text()='All Products']")
	WebElement allProductsTitle;

	public WebElement getAllProductsTitle() {
		return allProductsTitle;
	}

	public WebElement getContinueShoppingBtn() {
		return continueShoppingBtn;
	}

	public List<WebElement> getPlpAddToCartBtn() {
		return plpAddToCartBtn;
	}

	public WebElement getSearchTitle() {
		return searchTitle;
	}

	public List<WebElement> getViewProductCTA() {
		return viewProductCTA;
	}

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getSearchIcon() {
		return searchIcon;
	}

	public List<WebElement> getProductList() {
		return productList;
	}

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

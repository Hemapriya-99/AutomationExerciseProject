package com.qa.Testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_AddingProductsToCart extends TestBase {

	private HashMap<String, String> currentInput;
	private List<WebElement> prods;
	private WebElement matchingProduct;
	public int count = 0;
	static int total = 0;
	static int quantity;
	static String price;

	@Test(dataProvider = "getProductsData")
	public void search(HashMap<String, String> input) throws InterruptedException {
		this.currentInput = input;
		home.getProducts().click();

		utility.waitUntilElementIsVisible(searchPage.getAllProductsTitle());

		String productPageTitle = driver.getTitle();
		Assert.assertEquals(productPageTitle, input.get("expectedProductPageTitle"));

		searchPage.getSearchBar().sendKeys(input.get("product"));
		searchPage.getSearchIcon().click();

		String searchHeader = searchPage.getSearchTitle().getText();
		Assert.assertEquals(searchHeader, input.get("expectedSearchHeader"));
	}

	@Test(dependsOnMethods = { "search" })
	public void searchItem() {
		String prod = this.currentInput.get("product1");
		prods = searchPage.getProductList();
		matchingProduct = null;
		for (WebElement element : prods) {
			if (element.getText().equals(prod)) {
				matchingProduct = element;
				utility.scroll(matchingProduct);
				utility.scrollVertically();
				// System.out.println(matchingProduct.getText());
				break;
			}
		}
		Assert.assertNotNull(matchingProduct, "Product not found: " + prod);
		utility.sleep();
	}

	@Test(dependsOnMethods = { "searchItem" })
	public void addToCartFromPLP() throws InterruptedException {
		action.moveToElement(matchingProduct).build().perform();

		List<WebElement> plpCartBtns = searchPage.getPlpAddToCartBtn();

		Assert.assertTrue(plpCartBtns.size() >= prods.indexOf(matchingProduct) + 1,
				"Add to cart button in PLP is not found for the matching product");

		WebElement plpCartBtn = plpCartBtns.get(prods.indexOf(matchingProduct));
		action.moveToElement(plpCartBtn).click().perform();
		Thread.sleep(3000);

		searchPage.getContinueShoppingBtn().click();
		count++;
	}

	@Test(dependsOnMethods = { "searchItem" })
	public void goToProductDetailPage() {

		List<WebElement> viewButtons = searchPage.getViewProductCTA();

		Assert.assertTrue(viewButtons.size() >= prods.indexOf(matchingProduct) + 1,
				"View Product button not found for the matching product");

		WebElement viewButton = viewButtons.get(prods.indexOf(matchingProduct));
		viewButton.click();

		String prdName = prdDetailPage.getProductName().getText();

		prdDetailPage.getAddToCartBtn().click();
		count++;
	}

	@Test(dependsOnMethods = { "goToProductDetailPage" })
	public void goToCartPage() {
		prdDetailPage.getViewCartBtnPDP().click();
	}

	@Test(dependsOnMethods = { "goToCartPage" })
	public void verifyDetails() {
		quantity = Integer.parseInt(cart.getCartQty().getText());
		Assert.assertEquals(quantity, count);
		// System.out.println("qauntity: " + quantity + " count: " + count);

		// total
		price = cart.getPrice().getText();
		String str = price.substring(4, price.length());
		// System.out.println(Integer.parseInt(str));
		total = Integer.parseInt(str) * quantity;

		String prdTotal = cart.getTotal().getText();
		String expectedTotal = "Rs. " + total;

		// System.out.println("expTotal:" + expectedTotal + " prdTotal:" + prdTotal);
		Assert.assertEquals(prdTotal, expectedTotal);
	}

	@DataProvider
	public Object[][] getProductsData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\SearchTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

package com.qa.Testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Checkout extends TestBase {

	@Test
	public void goToCheckoutPage() {
		cart.getProceedToCheckoutBtn().click();
	}

	@Test(priority = 0, dataProvider = "getSignUpTestData")
	public void verifyAddressDetails(HashMap<String, String> input) {
		// Delivery address
		List<WebElement> deliveryAddress = checkout.getDeliveryAddressList();

		String deliveryTitleName = deliveryAddress.get(1).getText();
		String deliveryAddress1 = deliveryAddress.get(3).getText();
		String deliveryAddress2 = deliveryAddress.get(4).getText();
		String deliveryCityStatePostcode = deliveryAddress.get(5).getText();
		String deliveryCountry = deliveryAddress.get(6).getText();
		String deliveryPhoneNumber = deliveryAddress.get(7).getText();

		String expectedTitleName = TC_SignUp.SelectedTtile + ". " + input.get("fName") + " " + input.get("lName");
		String expectedAddress1 = input.get("address1");
		String expectedAddress2 = input.get("address2");
		String expectedCityStatePostcode = input.get("city") + " " + input.get("state") + " " + input.get("zipcode");
		String expectedCountry = TC_SignUp.SelectedCountry;
		String expectedPhoneNumber = input.get("mobileNo");

		Assert.assertEquals(deliveryTitleName, expectedTitleName);
		Assert.assertEquals(deliveryAddress1, expectedAddress1);
		Assert.assertEquals(deliveryAddress2, expectedAddress2);
		Assert.assertEquals(deliveryCityStatePostcode, expectedCityStatePostcode);
		Assert.assertEquals(deliveryCountry, expectedCountry);
		Assert.assertEquals(deliveryPhoneNumber, expectedPhoneNumber);

		// Billing address
		List<WebElement> billingAddress = checkout.getBillingAddressList();
		String billingTitleName = billingAddress.get(1).getText();
		String billingAddress1 = billingAddress.get(3).getText();
		String billingAddress2 = billingAddress.get(4).getText();
		String billingCityStatePostcode = billingAddress.get(5).getText();
		String billingCountry = billingAddress.get(6).getText();
		String billingPhoneNumber = billingAddress.get(7).getText();

		Assert.assertEquals(billingTitleName, expectedTitleName);
		Assert.assertEquals(billingAddress1, expectedAddress1);
		Assert.assertEquals(billingAddress2, expectedAddress2);
		Assert.assertEquals(billingCityStatePostcode, expectedCityStatePostcode);
		Assert.assertEquals(billingCountry, expectedCountry);
		Assert.assertEquals(billingPhoneNumber, expectedPhoneNumber);

	}

	@Test(priority = 1, dataProvider = "getSearchData")
	public void verifyProdctDetails(HashMap<String, String> inputData) {
		utility.scroll(checkout.getReviewOrderHeader());
		String productName = checkout.getPrdName().getText();
		String price = checkout.getPrice().getText();
		String quantity = checkout.getQuantity().getText();
		String total = checkout.getTotalPrice().getText();
		String cartTotal = checkout.getCartTotalPrice().getText();

		// System.out.println(productName + " " + price + " " + quantity + " " + total +
		// " " + cartTotal);

		// to calculate cart total
		int sum = 0;
		List<WebElement> p = checkout.getPriceList();
		for (int i = 0; i < p.size(); i++) {
			String element = p.get(i).getText();
			{
				int s = Integer.parseInt(element.substring(4, element.length()));
				sum += s;
				// System.out.println("s " + s + " " + "sum " + sum);
			}
		}

		String expectedPrdName = inputData.get("product1");
		String expectedPrice = TC_AddingProductsToCart.price;
		int qty = TC_AddingProductsToCart.quantity;
		String expectedQuantity = String.valueOf(qty);
		String expectedTotal = "Rs. " + TC_AddingProductsToCart.total;
		String expectedCartTotal = "Rs. " + sum;

		Assert.assertEquals(productName, expectedPrdName);
		Assert.assertEquals(price, expectedPrice);
		Assert.assertEquals(quantity, expectedQuantity);
		Assert.assertEquals(total, expectedTotal);
		Assert.assertEquals(cartTotal, expectedCartTotal);
	}

	@Test(priority = 2)
	public void addComments() {
		utility.scroll(checkout.getCommentSection());
		checkout.getCommentSection().sendKeys("Call me before delivering this order");
	}

	@Test(priority = 3)
	public void goToPaymentPage() {
		checkout.getPlaceOrderBtn().click();
	}

	@DataProvider
	public Object[][] getSignUpTestData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\SignUpTestData.json");
		return new Object[][] { { data.get(0) } };
	}

	@DataProvider
	public Object[][] getSearchData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\SearchTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

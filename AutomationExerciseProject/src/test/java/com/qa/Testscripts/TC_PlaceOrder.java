package com.qa.Testscripts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_PlaceOrder extends TestBase {

	@Test(priority = 0, dataProvider = "getPaymentDetailsData")
	public void verifyPageTitle(HashMap<String, String> input) {
		String actualPaymentPageTitle = driver.getTitle();
		String expectedPaymentPageTitle = input.get("pageTitle");
		Assert.assertEquals(actualPaymentPageTitle, expectedPaymentPageTitle);
	}

	@Test(priority = 1, dataProvider = "getPaymentDetailsData")
	public void enterPaymentDetails(HashMap<String, String> inputData) {
		String name = inputData.get("cardholderName");
		String cardNo = inputData.get("cardNumber");
		String cvcNo = inputData.get("cvc");
		String expMonth = inputData.get("expiryMonth");
		String expYear = inputData.get("expiryYear");

		payment.getCardholderName().sendKeys(name);
		payment.getCardNumber().sendKeys(cardNo);
		payment.getCvc().sendKeys(cvcNo);
		payment.getExpiryMonth().sendKeys(expMonth);
		payment.getExpiryYear().sendKeys(expYear);

		payment.getConfirmOrderButton().click();

		utility.sleep();

		// System.out.println(payment.getConfirmOrderMessage().getText());
		Assert.assertTrue(payment.getConfirmOrderMessage().isDisplayed(), "Success Message is not displayed");
	}

	@Test(priority = 2, dataProvider = "getOrderConfirmationData")
	public void verifyOrderPlaced(HashMap<String, String> inputValue) {
		String actualHeader = confirmOrder.getOrderConfirmHeader().getText();
		String actualSuccessMessage = confirmOrder.getConfirmationMessage().getText();
		Assert.assertEquals(actualHeader, inputValue.get("expectedHeader"));
		Assert.assertEquals(actualSuccessMessage, inputValue.get("expectedSuccessMessage"));

		// System.out.println(actualHeader + " " + actualSuccessMessage);
	}

	@Test(priority = 3)
	public void downloadInvoice() {
		confirmOrder.getInvoiceButton().click();

		// Wait for the file to download
		String fileName = "invoice.txt";
		File file = new File("C:\\Users\\DELL\\Downloads" + "\\" + fileName);
		boolean isFileDownloaded = waitForFileDownload(file, 30); // Wait up to 30 seconds for the file to download

		// Verify the file is downloaded
		Assert.assertTrue(isFileDownloaded, "The file was not downloaded successfully");
	}

	private boolean waitForFileDownload(File file, int timeoutInSeconds) {
		int timeElapsed = 0;
		while (timeElapsed < timeoutInSeconds) {
			if (file.exists()) {
				return true;
			}
			try {
				Thread.sleep(1000);
				timeElapsed++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@DataProvider
	public Object[][] getPaymentDetailsData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\TestData\\PaymentInfoTestData.json");
		return new Object[][] { { data.get(0) } };
	}

	@DataProvider
	public Object[][] getOrderConfirmationData() throws IOException {
		List<HashMap<String, String>> data = utility.getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\qa\\TestData\\OrderConfirmationPageTestData.json");
		return new Object[][] { { data.get(0) } };
	}
}

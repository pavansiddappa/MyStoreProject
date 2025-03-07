package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobject.AddToCartPage;
import com.mystore.pageobject.AddressPage;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.LoginPage;
import com.mystore.pageobject.OrderConfirmationPage;
import com.mystore.pageobject.OrderPage;
import com.mystore.pageobject.OrderSummaryPage;
import com.mystore.pageobject.PaymentPage;
import com.mystore.pageobject.SearchResultPage;
import com.mystore.pageobject.ShippingPage;
import com.mystore.utility.Log;

public class EndToEndTest extends BaseClass {

	private IndexPage indexPage;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;
	private LoginPage loginPage;
	private AddressPage addressPage;
	private ShippingPage shippingPage;
	private PaymentPage paymentPage;
	private OrderSummaryPage orderSummary;
	private OrderConfirmationPage orderConfirmationPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider = "getProduct", dataProviderClass = DataProviders.class, groups = "Regression")
	public void endToEndTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("endToEndtestCase");
		indexPage = new IndexPage();
//		searchResultPage = indexPage.searchProduct("t-shirt");
		searchResultPage = indexPage.searchProduct(productName);
		addToCartPage = searchResultPage.clickOnProduct();
//		addToCartPage.selectSize("M");
		addToCartPage.selectSize(qty);
//		addToCartPage.enterQuantity("2");
		addToCartPage.enterQuantity(size);
		addToCartPage.clickOnAddToCart();
		orderPage = addToCartPage.clickOnCheckOut();
		loginPage = orderPage.clickOnCheckOut();
		addressPage = loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
		shippingPage = addressPage.clickOnCheckOut();
		shippingPage.checkTheTerms();
		paymentPage = shippingPage.clickOnProceedToCheckOut();
		orderSummary = paymentPage.clickOnPaymentMethod();
		orderConfirmationPage = orderSummary.clickOnconfirmOrderBtn();
		String actualMessage = orderConfirmationPage.validateConfirmMessage();
		String expectedMsg = "Your order on My Shop is complete.";
		Assert.assertEquals(actualMessage, expectedMsg);
		Log.endTestCase("endToEndTestCase");
	}

}

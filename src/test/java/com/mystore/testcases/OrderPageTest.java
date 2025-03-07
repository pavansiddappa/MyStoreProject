package com.mystore.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobject.AddToCartPage;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.OrderPage;
import com.mystore.pageobject.SearchResultPage;
import com.mystore.utility.Log;

public class OrderPageTest extends BaseClass {
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;

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
	public void verifyTotalPrice(String productName, String size, String qty) {
		Log.startTestCase("verifyTotalPrice");
		indexPage = new IndexPage();
//		searchResultPage = indexPage.searchProduct("t-shirt");
		searchResultPage = indexPage.searchProduct(productName);
		addToCartPage = searchResultPage.clickOnProduct();
//		addToCartPage.selectSize("M");
		addToCartPage.selectSize(size);
//		addToCartPage.enterQuantity("2");
		addToCartPage.enterQuantity(qty);
		addToCartPage.clickOnAddToCart();
		orderPage = addToCartPage.clickOnCheckOut();
		Double unitPrice = orderPage.getUnitPrice();
		Double totalPrice = orderPage.getTotalPrice();
		Double totshipping = orderPage.totalShopping();
		Double totalExpectedPrice = (unitPrice * (Double.parseDouble(qty))) + totshipping;
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.endTestCase("verifyTotalPrice");
	}

}

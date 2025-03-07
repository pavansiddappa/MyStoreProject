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
import com.mystore.pageobject.SearchResultPage;
import com.mystore.utility.Log;

public class AddToCartPageTest extends BaseClass {
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider = "getProduct", dataProviderClass = DataProviders.class, groups = {"Regression","Sanity"})
	public void addToCartTest(String productName, String qty, String size) throws InterruptedException {
		Log.startTestCase("addToCartTest");
		indexPage = new IndexPage();
//		searchResultPage = indexPage.searchProduct("t-shirt");
		searchResultPage = indexPage.searchProduct(productName);
		addToCartPage = searchResultPage.clickOnProduct();
//		addToCartPage.selectSize("M");
		addToCartPage.selectSize(qty);
//		addToCartPage.enterQuantity("2");
		addToCartPage.enterQuantity(size);
		addToCartPage.clickOnAddToCart();
		boolean result = addToCartPage.validateAddtoCart();
		Assert.assertTrue(result);
		Log.endTestCase("addToCartTest");
	}

}

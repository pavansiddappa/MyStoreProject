package com.mystore.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.LoginPage;
import com.mystore.pageobject.SearchResultPage;
import com.mystore.utility.Log;

public class SearchResultPageTest extends BaseClass {

	IndexPage indexPage;
	LoginPage loginPage;
	SearchResultPage searchResultPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider = "searchProduct", dataProviderClass = DataProviders.class, groups = "Smoke")
	public void productAvailabilityTest(String productName) {
		Log.startTestCase("productAvailabilityTest");
		indexPage = new IndexPage();
//		searchResultPage = indexPage.searchProduct("t-shirt");
		searchResultPage = indexPage.searchProduct(productName);
		boolean result = searchResultPage.isProductAvailable();
		Assert.assertTrue(result);
		Log.endTestCase("productAvailabilityTest");	
	}
}

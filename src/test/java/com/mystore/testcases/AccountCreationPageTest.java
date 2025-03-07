package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobject.AccountCreationPage;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.LoginPage;
import com.mystore.utility.Log;

public class AccountCreationPageTest extends BaseClass {

	IndexPage indexPage;
	LoginPage loginPage;
	AccountCreationPage accountCreationPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	
	@Test(dataProvider = "email", dataProviderClass = DataProviders.class, groups = "Sanity")
	public void verfiyCreateAccountPageTest(String email) throws Throwable {
		Log.startTestCase("verifyCreateAccountPageTest");
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
	  //accountCreationPage = loginPage.createNewAccount("jbjhdfds@gmail.com");
		accountCreationPage = loginPage.createNewAccount(email);
		boolean result = accountCreationPage.validateAcountCreatePage();
		Assert.assertTrue(result);
		Log.endTestCase("verifyCreateAccountPageTest");

	}
}

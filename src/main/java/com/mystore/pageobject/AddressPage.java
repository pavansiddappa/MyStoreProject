package com.mystore.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddressPage extends BaseClass {

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	private WebElement proceedToCheckOut;

	public AddressPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public ShippingPage clickOnCheckOut() {
		Action.click(getDriver(), proceedToCheckOut);
		return new ShippingPage();
	}
}

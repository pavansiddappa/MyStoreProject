package com.mystore.pageobject;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class OrderSummaryPage extends BaseClass {

	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	private WebElement confirmOrderBtn;

	public OrderSummaryPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public OrderConfirmationPage clickOnconfirmOrderBtn() throws Throwable {
		Action.click(getDriver(), confirmOrderBtn);
		return new OrderConfirmationPage();
	}
}

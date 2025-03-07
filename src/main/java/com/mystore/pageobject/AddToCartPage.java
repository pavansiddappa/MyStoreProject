package com.mystore.pageobject;

import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass {

	@FindBy(id = "quantity_wanted")
	private WebElement quantity;

	@FindBy(id = "group_1")
	private WebElement size;

	@FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
	private WebElement addToCartMessag;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckOutBtn;

	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void enterQuantity(String quantity1) {
		Action.type(quantity, quantity1);
	}

	public void selectSize(String str) {
		Action.selectByVisibleText(str, size);
	}

	public void clickOnAddToCart() {
		Action.click(getDriver(), addToCartBtn);
	}

	public boolean validateAddtoCart() {
		Action.fluentWait(getDriver(), addToCartMessag, 10);
		return Action.isDisplayed(getDriver(), addToCartMessag);
	}

	public OrderPage clickOnCheckOut() {
		Action.fluentWait(getDriver(), addToCartMessag, 10);
		Action.JSClick(getDriver(), proceedToCheckOutBtn);
		return new OrderPage();
	}
}

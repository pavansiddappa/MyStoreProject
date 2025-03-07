package com.mystore.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {

	@FindBy(xpath = "//*[@id=\"best-sellers_block_right\"]/div/ul/li[4]/a/img")
	private WebElement productResult;

	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public boolean isProductAvailable() {
		return Action.isDisplayed(getDriver(), productResult);
	}

	public AddToCartPage clickOnProduct() {
		Action.click(getDriver(), productResult);
		return new AddToCartPage();
	}
}

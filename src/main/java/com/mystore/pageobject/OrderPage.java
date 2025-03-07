package com.mystore.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class OrderPage extends BaseClass {

	@FindBy(xpath = "//td[@class='cart_unit']/ul/li")
	private WebElement unitPrice;

	@FindBy(id = "total_price")
	private WebElement totalPrice;

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	private WebElement proceedToCheckOut;
	
	@FindBy(xpath = "//tr[@class='cart_total_delivery']//td[2]")
	private WebElement totalShoppings;

	public OrderPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public double getUnitPrice() {
		String unitPrice1 = unitPrice.getText();
		String unit = unitPrice1.replaceAll("[^a-zA-Z0-9]", "");
		double finalUnitPrice = Double.parseDouble(unit);
		return finalUnitPrice / 100;
	}

	public double getTotalPrice() {
		String totalPrice1 = totalPrice.getText();
		String tot = totalPrice1.replaceAll("[^a-zA-Z0-9]", "");
		double finalTotalPrice = Double.parseDouble(tot);
		return finalTotalPrice / 100;
	}
	
	public double totalShopping() {
		String totalShip = totalShoppings.getText();
		String totsh = totalShip.replaceAll("[^a-zA-Z0-9]", "");
		double finalTotalPrice = Double.parseDouble(totsh);
		return finalTotalPrice/100;

	}

	public LoginPage clickOnCheckOut() throws Throwable {
		Action.click(getDriver(), proceedToCheckOut);
		return new LoginPage();
	}
}

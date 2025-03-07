package com.mystore.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {

	@FindBy(id = "email")
	WebElement userName;

	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(id = "SubmitLogin")
	WebElement signInBtn;

	@FindBy(name = "email_create")
	WebElement emailForNewAccount;

	@FindBy(name = "SubmitCreate")
	WebElement createNewAccountBtn;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public HomePage login(String uname, String pswd) {
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.click(getDriver(), signInBtn);
		return new HomePage();
	}

	public AddressPage login1(String uname, String pswd) {
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.click(getDriver(), signInBtn);
		return new AddressPage();
	}

	public AccountCreationPage createNewAccount(String newEmail) {
		Action.type(emailForNewAccount, newEmail);
		Action.click(getDriver(), createNewAccountBtn);
		return new AccountCreationPage();
	}
}

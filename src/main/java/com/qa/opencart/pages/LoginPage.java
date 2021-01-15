package com.qa.opencart.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By Locator / OR
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[value = 'Login']");
	private By ForgotPwdLink = By.cssSelector("div.form-group a");
	private By registerLink = By.linkText("Register");
	
	//2. Constructor of page class:
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. page actions/methods/libs:
	@Step("get Login Page Title")
	public String getLoginPageTitle() {
		LOGGER.info("getting page title");
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("checking is Forgot Pwd Link Exist")
	public boolean isForgotPwdLinkExist() {
		LOGGER.info("checking forgot pwd link");
		return elementUtil.doIsDisplayed(ForgotPwdLink);
	}
	
	@Step("Login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		LOGGER.info("Login with : " + un + " " + pwd);
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("navigate To Register Page")
	public RegisterPage navigateToRegisterPage() {
		LOGGER.info("Navigate to Register page....");
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	

}

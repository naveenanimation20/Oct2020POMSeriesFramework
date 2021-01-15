package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 200: Feature Name : Accounts Page for Demo Shop Application")
@Story("User Story - 301: Desgin Accounts Page for application with different test cases")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("accounts Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("acc page title is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, Errors.TITLE_NOT_MATCHED_ERROR);
	}
	
	@Test(priority = 2)
	@Description("verify Accounts Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageHeaderTest() {
		String header = accountsPage.getHeaderValue();
		System.out.println("Acc page header is : " + header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.HEADER_NOT_MATCHED_ERROR);
	}

	@Test(priority = 3)
	@Description("verify Acc Page Sections Count Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccPageSectionsCountTest() {
		Assert.assertTrue(accountsPage.getAccontSectionsCount() == Constants.ACCOUNTS_PAGE_SECTIONS_COUNT, "Accoint section count is not matched...");
	}

	@Test(priority = 4)
	@Description("verify Acc Page Sections List Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccPageSectionsListTest() {
		List<String> accSecList = accountsPage.getAccountSectionsList();
		System.out.println(accSecList);
		Assert.assertEquals(accSecList, Constants.getAccSectionsList());
	}
	
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {{"iMac"}, 
							   {"iPhone"}, 
							   {"Macbook"}};
	}
	
	@Test(priority = 5, dataProvider = "searchData")
	@Description("product search with Macbook")
	@Severity(SeverityLevel.CRITICAL)
	public void searchTest(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));
	}

	@Test(priority = 6)
	@Description("verify Product Results Test for iMac")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
	}
	
	
	
}

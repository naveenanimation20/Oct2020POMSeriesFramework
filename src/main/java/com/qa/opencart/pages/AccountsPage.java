package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By header = By.cssSelector("div#logo a");
	private By accountSectionsHeaders = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItems = By.cssSelector("div.product-thumb h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}
	
	public String getHeaderValue() {
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}

	public int getAccontSectionsCount() {
		return elementUtil.getElements(accountSectionsHeaders).size();
	}

	public List<String> getAccountSectionsList() {
		List<String> accountsList = new ArrayList<>();
		List<WebElement> accSectionList = elementUtil.getElements(accountSectionsHeaders);

		for (WebElement e : accSectionList) {
			String section = e.getText();
			System.out.println(section);
			accountsList.add(section);
		}

		return accountsList;
	}
	
	//Search features Page Actions:
	public boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if(elementUtil.getElements(searchItemResult).size() > 0) {
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = elementUtil.getElements(resultsItems);
		System.out.println("total number of items displayed : " + resultItemsList.size());
		
		for(WebElement e : resultItemsList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
	
	

}

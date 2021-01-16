package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By productImages = By.cssSelector(".thumbnails img");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public Map<String, String> getProductInformation() {
		
		Map<String, String> productInfoMap = new HashMap<String, String>();
		
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader).trim());
		
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		for(WebElement e : productMetaDataList) {
			//Brand: Apple
			//Product Code: Product 14
			//Availability: Out Of Stock
			String meta[] = e.getText().split(":");
			String metaName = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaName, metaValue);
		}
		
		//price:
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
	}
	
	public void selectQuantity(String qty) {
		System.out.println("quantiy : " + qty);
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart() {
		System.out.println("add to cart....");
		elementUtil.doClick(addToCartButton);
	}
	
	public int getProductImages() {
		int imageCount = elementUtil.getElements(productImages).size();
		System.out.println("total product images: " + imageCount);
		return imageCount;
	}
	
	public String getProductInfoPageTitle(String productName) {
		String title = elementUtil.waitForPageTitlePresent(productName, 5);
		System.out.println("Product Page title is : " + title);
		return title;
	}
	
	

}

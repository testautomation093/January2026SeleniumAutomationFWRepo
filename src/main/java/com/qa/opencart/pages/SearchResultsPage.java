package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {
	
    private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resulltsHeader = By.tagName("h1");
	
	
	private WebDriver driver;
	
	private ElementUtils elUtils;

	// public page class constructor:

	public SearchResultsPage(WebDriver driver)
	{
	   this.driver=driver;
	   elUtils=new ElementUtils(driver);
	   
	}
	
	public int getSearchResultsCount()
	{
		int count=
				elUtils.waitforElementsPresence(searchResults, AppConstants.DEFAULT_SHORT_WAIT).size();
		
		
		System.out.println("Total count is : "+count);
		return count;
		
	}
	
	public String getResultsHeaderValue()
	{
		String headerValue=elUtils.doElementGetText(resulltsHeader);
		
		System.out.println("Header value of the search results page is : "+headerValue);
		
		return headerValue;
		
	}
	
	public ProductInfoPage selectAndClickProduct(String productName)
	{
		System.out.println("Clicked Product is : "+productName);
		
		elUtils.doClick(By.linkText(productName));
		
		return new ProductInfoPage(driver);
		
	}
	
	
	
	

}

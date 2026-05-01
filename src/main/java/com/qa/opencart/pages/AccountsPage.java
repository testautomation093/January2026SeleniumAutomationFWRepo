package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private final By headers = By.tagName("h2"); // mutiple headers
	private final By logoutLink = By.linkText("Logout"); // Logout Link
	private final By searchIcon = By.xpath("//div[@id='search']//button");
	private final By searchBar = By.name("search");

	private WebDriver driver;
	
	private ElementUtils elUtils;

	// public page class constructor:

	public AccountsPage(WebDriver driver)
	{
	   this.driver=driver;
	   elUtils=new ElementUtils(driver);
	   
	}

	public List<String> getAccPageHeaders() {
		
		List<String> headerTextList = elUtils.getElementsTextList(headers);
		
		return headerTextList;

	}	
	
	public boolean isLogOutLinkExist()
	{
		boolean flag=elUtils.isElementDisplayed(logoutLink);
		
		return flag;
	}
	
	public SearchResultsPage doSearch(String searchValue)
	{
		System.out.println("Searched Product is : "+searchValue);
		WebElement el=elUtils.waitForElementPresence(searchBar, AppConstants.DEFAULT_SHORT_WAIT);
		
		el.clear();
		elUtils.doSendKeys(searchBar, searchValue);
		
		elUtils.doClick(searchIcon);
		
		return new SearchResultsPage(driver);
			
	}
	
	
	
}

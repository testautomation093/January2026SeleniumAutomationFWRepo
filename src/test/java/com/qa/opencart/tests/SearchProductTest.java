package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchProductTest extends BaseTest {
	
	@BeforeClass
	public void searchProductSetUp() throws InterruptedException
	{
		
		actPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
    		
	}
	
	
	@Test
	public void searchProductTest()
	{
	 searchResultsPage=actPage.doSearch("macbook");
	 
	 productInfoPage=searchResultsPage.selectAndClickProduct("MacBook Pro");
	 
	 String actProductHeaderVal=productInfoPage.getProductHeader();
	 
	 Assert.assertEquals(actProductHeaderVal,"MacBook Pro");
	
	}

}

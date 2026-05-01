package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchResultsPage;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() throws InterruptedException
	{
		
		actPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@DataProvider
	public Object[][] getProductsData()
	{
		Object obj[][]=new Object[3][2];
		obj[0][0]="macbook";
		obj[0][1]="MacBook Air";
		
		obj[1][0]="samsung";
		obj[1][1]="Samsung Galaxy Tab 10.1";
		
		obj[2][0]="canon";
		obj[2][1]="Canon EOS 5D";
		
		return obj;
		
	}
	
	@DataProvider
	public Object[][] getProductDataForImages()
	{
		
		Object obj[][]=new Object[3][3];
		obj[0][0]="macbook";
		obj[0][1]="MacBook Air";
		obj[0][2]=4;
		
		obj[1][0]="samsung";
		obj[1][1]="Samsung Galaxy Tab 10.1";
		obj[1][2]=7;
		
		obj[2][0]="canon";
		obj[2][1]="Canon EOS 5D";
		obj[2][2]=3;
		
		return obj;
		
		
	}
	
	
	
	@Test ( dataProvider = "getProductsData")
	public void productHeaderTest(String searchKey, String productName)
	{
		
		searchResultsPage=actPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectAndClickProduct(productName);
		
		String actHeaderValue=productInfoPage.getProductHeader();
		
		Assert.assertEquals(actHeaderValue, productName);
		
	}
	
	@Test (dataProvider = "getProductDataForImages")
	public void productImagesCountTest(String searchKey, String productName, int count)
	{
		
		searchResultsPage=actPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectAndClickProduct(productName);
		
		int imagesCount=productInfoPage.getProductImagesCount();
		
		Assert.assertEquals(imagesCount, count);
		
	}
	
	@Test
	public void productInfoTest()
	{
		
		 searchResultsPage = actPage.doSearch("macbook");
		 productInfoPage=searchResultsPage.selectAndClickProduct("MacBook Air");
		 Map<String, String> completeProductDetails = productInfoPage.getCompleteProductDetails();
		
		Assert.assertEquals(completeProductDetails.get("Brand"), "Apple");
		Assert.assertEquals(completeProductDetails.get("Availability"), "Out Of Stock");
		Assert.assertEquals(completeProductDetails.get("Product Code"), "Product 18");
		Assert.assertEquals(completeProductDetails.get("ProductPrice"), "$1,202.00");
		Assert.assertEquals(completeProductDetails.get("Reward Points"), "700");
		 
	}
	
	
	
}

package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {

	private final By header=By.tagName("h1"); 
	
    private final By images=By.xpath("//a[@class='thumbnail']"); 
    
    private final By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    
    private final By productPrice=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    
	private WebDriver driver;
	private ElementUtils elUtils;
	
	public Map<String, String> productMap;

	// public page class constructor:

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elUtils = new ElementUtils(driver);

	}

	public String getProductHeader()
	{
		String headerText=elUtils.waitForElementVisibility(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		
		System.out.println("Product header Value is : "+headerText);
		
		return headerText;
		
		
	}
	
	public int getProductImagesCount()
	{
		int imageCount=
				elUtils.waitforElementsVisibility(images, AppConstants.DEFAULT_SHORT_WAIT).size();
		
		System.out.println("Total Images on the Product Info Page is : "+imageCount);
		
		return imageCount;
		
	}
	
	private void getProductMetaData()
	{
		List<WebElement> metaList = elUtils.waitforElementsVisibility(productMetaData, AppConstants.DEFAULT_SHORT_WAIT);
		
		System.out.println("Total size of product meta data is : "+metaList.size());
		
		for(WebElement e : metaList)
		{
			String text=e.getText();
			String meta[]=text.split(":");
			
			String key=meta[0];
			String value=meta[1].trim();
			
			productMap.put(key, value);
			
		}
		
	}
	
	private void getProductPrice()
	{
		
		List<WebElement> priceList = elUtils.waitforElementsVisibility(productPrice, AppConstants.DEFAULT_SHORT_WAIT);

		System.out.println("Total Price size is : "+priceList.size());
		
		String productPrice=priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim(); // $2000.00
		
		productMap.put("ProductPrice", productPrice);
		productMap.put("Ex Tax Price",exTaxPrice);
	
	}
	
	public Map<String, String> getCompleteProductDetails()
	{
		productMap=new HashMap<String, String>();
		getProductMetaData();
		getProductPrice();
		
		System.out.println("Product Complete Details are: \n" +productMap);
		
		return productMap;
	}
	
}

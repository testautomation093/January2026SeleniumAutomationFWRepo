package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegisterPage {
	
	
	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmpassword = By.id("input-confirm");

	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private final By successMessg = By.cssSelector("div#content h1");

	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");	

    private WebDriver driver;
	
	private ElementUtils elUtils;

	// public page class constructor:

	public RegisterPage(WebDriver driver)
	{
	   this.driver=driver;
	   elUtils=new ElementUtils(driver);
	   
	}
	
	public boolean userRegistration(String fName, String lName, String eMail, String phone, String pass, String subscribe)
	{
		elUtils.waitForElementPresence(firstName, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(fName);
		
		elUtils.doSendKeys(lastName, lName);
		elUtils.doSendKeys(email, eMail);
		elUtils.doSendKeys(telephone, phone);
		elUtils.doSendKeys(password, pass);
		
		elUtils.doSendKeys(confirmpassword, pass);
		
		if(subscribe.equals("yes"))
		{
			elUtils.doClick(subscribeYes);
			
		}
		else
		{
			elUtils.doClick(subscribeNo);
		}
		
		elUtils.doClick(agreeCheckBox);
		
		elUtils.doClick(continueButton);
		
		String actualSuccessMessage=elUtils.waitForElementVisibility(successMessg, AppConstants.DEFAULT_SHORT_WAIT).getText();
		
		System.out.println("Registration Success Message is : " +actualSuccessMessage);
		
		elUtils.doClick(logoutLink);
		elUtils.doClick(registerLink);
		if(actualSuccessMessage.equals(AppConstants.USER_REGISTER_SUCCESS_MESSG))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
}
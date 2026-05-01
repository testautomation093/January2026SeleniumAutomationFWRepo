package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {
	
// a. private By locators;
	// b. public page class constructors
	
	// c. page class action methods
	
	
	// private By locators:
    private final By email = By.id("input-email");
	private final By password =By.id("input-password");
	private final By loginBtn= By.xpath("//input[@value='Login']");
	private final By forgotPwdLink=By.xpath("(//a[text()='Forgotten Password'])[1]");
	private final By header=By.tagName("h2");
	
	private final By register=By.xpath("(//a[text()='Register'])[2]");
	
	private WebDriver driver;
	
	private ElementUtils elUtils;
	
	private static final Logger log= LogManager.getLogger(LoginPage.class);
	
	// public page class constructor:
	
	public LoginPage(WebDriver driver)
	{
	   this.driver=driver;
	   elUtils=new ElementUtils(driver);
		
	}
	
	// Public Action Methods:
	public String getLoginPageTitle()
	{
		
		String title=elUtils.waitforexactTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		
		log.info("Title of the page is : "+title);
		//System.out.println("Title of the page is : "+title);
		
		return title;
	}
	
	
	public String getLoginPageUrl()
	{
		
		String currentUrl=elUtils.waitforUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		
		log.info("Url of the page is : "+currentUrl);
		//System.out.println("Url of the page is : "+currentUrl);
		
		return currentUrl;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		boolean flag=elUtils.isElementDisplayed(forgotPwdLink);
		return flag;
		
	}
	
	
	public boolean isHeaderExist()
	{
		log.info("header os the page is : "+driver.findElement(header).getText());
		//System.out.println("header os the page is : "+driver.findElement(header).getText());
		boolean flag=elUtils.isElementDisplayed(header);
		return flag;
		
	}
	
	public AccountsPage doLogin(String uname, String pass) throws InterruptedException
	{
		log.info("App Credentials are : "+uname + ": "+pass);
		//System.out.println("App Credentials are : "+uname + ": "+pass);
		
		elUtils.waitForElementVisibility(email, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(uname);
		elUtils.waitForElementVisibility(password, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(pass);

		
		elUtils.doClick(loginBtn);
		
		Thread.sleep(1000);
		
		//return driver.getTitle();
		
		elUtils.waitforexactTitle(AppConstants.ACC_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		
		return new AccountsPage(driver);
		
		
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		elUtils.waitForElementPresence(register, AppConstants.DEFAULT_SHORT_WAIT).click();
		
		return new RegisterPage(driver);
		
	}
	
	

}
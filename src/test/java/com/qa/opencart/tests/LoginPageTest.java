package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void loginPageTitleTest()
	{
		String actTitle=loginPage.getLoginPageTitle();
		
	   Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	
	@Test
	public void loginPageUrlTest()
	{
		String actUrl=loginPage.getLoginPageUrl();
		
	   Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Test
	public void loginTest() throws InterruptedException
	{
		actPage=loginPage.doLogin("admin@gmail.com", "admin@123");
		
		Assert.assertTrue(actPage.isLogOutLinkExist());
		
	}
	
	

}

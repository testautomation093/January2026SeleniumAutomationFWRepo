package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {
	
	
	
	@BeforeClass
	public void accSetUp() throws InterruptedException
	{
		
		actPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		
		//actPage=new AccountsPage(driver);
		
	}

	@Test
	public void isLogoutLinkExist()
	{
		
		boolean flag=actPage.isLogOutLinkExist();
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void searchProductTest()
	{
		actPage.doSearch("iMac");
	}
	
	@Test
	public void accPageHeaderTest()
	{
		
		List<String> accPageHeaders = actPage.getAccPageHeaders();
		
		Assert.assertEquals(accPageHeaders.size(),AppConstants.EXPECTED_HEADERS);
	}
	
}

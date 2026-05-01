package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtils;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {
	
	
	@BeforeClass
	public void userRegistrationSetup()
	{
	  registerPage=loginPage.navigateToRegisterPage();
		
	}
	
	
	@DataProvider
	public Object[][] getUserDataFromSheet()
	{
		Object obj[][]=ExcelUtils.getTestData("registration");
		
		return obj;
	}
	
	@DataProvider
	public Object[][] getCSVData()
	{
		Object obj[][]=CsvUtil.csvData("registration");
		
		return obj;
		
	}
	
	@Test (dataProvider = "getCSVData")
	public void userRegistrationTest(String fName, String lName,String telephone, String pass, String subs)
	{
		boolean flag=registerPage.userRegistration(fName, lName, StringUtils.generateEmail(), telephone, pass, subs);
		
		Assert.assertTrue(flag);
		
	}

}

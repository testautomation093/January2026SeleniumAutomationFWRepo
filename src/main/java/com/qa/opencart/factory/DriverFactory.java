package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	public WebDriver driver;
	
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	private static final Logger log= LogManager.getLogger(DriverFactory.class);
	
	public OptionsManager optionsManager;
	
	
	public WebDriver initDriver(Properties prop)
	{
		
		optionsManager=new OptionsManager(prop);
		
		String browser=prop.getProperty("browser");
		
		boolean remoteFlag=Boolean.parseBoolean(prop.getProperty("remote"));
		
		log.info("Browser name is : "+browser);
		
		//System.out.println("Browser name is : "+browser);
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			
			//driver=new ChromeDriver();
			if(remoteFlag)
			{
			
				init_remoteDriver(browser);
				
			}
			else
			{
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
			
		case "firefox":
			
			//driver=new FirefoxDriver();
			if(remoteFlag)
			{
				init_remoteDriver(browser);
			}
			else
			{
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
			
		case "edge":
			
			//driver=new EdgeDriver();
			
			if(remoteFlag)
			{
				init_remoteDriver(browser);
			}
			else
			{
			
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
			
		case "safari":
			
			//driver=new SafariDriver();
			
			tlDriver.set(new SafariDriver());
			
			break;

		default:
			
			//System.out.println(AppErrors.INVALID_BROWSER_MESG);
			log.error(AppErrors.INVALID_BROWSER_MESG);
			
			throw new FrameworkException("===INVALID BROWSER===");

		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	
	public Properties initProp()
	{
		prop=new Properties();
		FileInputStream fp=null;
		
		// mvn clean install -Denv="qa";
		
		String envName=System.getProperty("env");
	
		try {
			
			if(envName==null)
			{
			log.info("Test Cases are Running in default env as nothing is passed: "+envName);
		    fp=new FileInputStream("src/test/resources/config/config.properties");
			
			}
			else
			{
				switch (envName.toLowerCase().trim()) {
				case "qa":
					log.info("Test Cases are runnig in : "+envName + " Environment");
				    fp=new FileInputStream("src/test/resources/config/config_qa.properties");

					break;
					
				case "dev":
					log.info("Test Cases are runnig in : "+envName + " Environment");
				    fp=new FileInputStream("src/test/resources/config/config_dev.properties");

					break;
					
				case "stage":
					log.info("Test Cases are runnig in : "+envName + " Environment");
				    fp=new FileInputStream("src/test/resources/config/config_stage.properties");

					break;

				default:
					log.error("Wrong Environment is passed");
					throw new FrameworkException("===INVALID ENVIRONMENT PASSED===");
					
				}			
				
				
			}
		
		   try {
			prop.load(fp);
		
		   
		   
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
		
	}
	
	private void init_remoteDriver(String browser)
	{
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
			
			
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

       case "firefox":
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
			
			
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
			
      case "edge":
	
	try {
		tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getEdgeOptions()));
	
	
	
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	break;	
			
			
		default:
			break;
		}
		
		
	}
	
	public static File getScreenshotAsFile()
	{
		
		File file=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return file;
		
	}
	
	

}

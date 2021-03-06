package com.SunBasket.Utility;

import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.SunBasket.Config.Config;
import com.aventstack.extentreports.Status;

//@Listeners(com.SunBasket.Utility.SBListeners.class)
public class BaseTest_Local extends DriverScript{
	
	@BeforeTest
	public void beforeTest(){
	}
	
/*	@BeforeClass
	public void beforeClass(){
	}
*/
	
/*** Use this to run Tests using TestNG.xml ***/

	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browser){
		initializeBrowser(browser);
		driver.navigate().to(Config.Url.url_Base);
// 		driver.navigate().to("https://develop.sunbasket-staging.com");
	}

/*
	@BeforeMethod
	public void setUp(){
		initializeBrowser(Config.Browser.browser);
		driver.navigate().to(Config.Url.base_url);
	}
	*/
	/*
	@BeforeMethod
	public void setUp(Method method){
		logger = extent.createTest(method.getName());
		initializeBrowser("chrome");
		driver.navigate().to("https://master.sunbasket-staging.com");
	}
/*
	@AfterMethod
	public void tearDown(){
		logger.log(Status.INFO, "Quit Browser");
		quit();
	}
/*
	@AfterClass
	public void afterClass(){
	}
	*/
	@AfterTest
	public void afterTest(){
	}
	@AfterMethod
	public void tearDown(){
		quit();
	}

}

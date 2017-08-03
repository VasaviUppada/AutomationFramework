package com.SunBasket.Utility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.SunBasket.Config.Config;

@Listeners(com.SunBasket.Utility.SBListeners.class)
public class BaseTest extends DriverScript{
/*	
	@BeforeTest
	public void beforeTest(){
		System.out.println("Before Test!");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Before Class!");
	}
*/	
	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(String browser){
		initializeBrowser(browser);
		driver.navigate().to(Config.url.base_url);
	}
	
	@BeforeTest
	public void setUp(){
		initializeBrowser();
		driver.navigate().to(Config.url.base_url);
	}
	
	@AfterTest
	public void tearDown(){
		close();
	}
	
/*	
	@AfterClass
	public void afterClass(){
		System.out.println("After Class!");
	}
	
	@AfterTest
	public void afterTest(){
		System.out.println("After Test!");
	}
*/
}

package com.SunBasket.Utility;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.UUID;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SunBasket.Config.Config;

@Listeners(com.SunBasket.Utility.SBListeners.class)
public class BaseTest extends DriverScript{
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("Before Test!");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Before Class!");
	}

/*** Use this to run Tests using TestNG.xml ***/
/*
	@Parameters({ "browser" })
	@BeforeMethod(dependsOnGroups = {"testNGRun"})
	public void setUp(String browser){
		initializeBrowser(browser);
		driver.navigate().to(Config.Url.base_url);
	}
*/

	@BeforeMethod
	public void setUp(){
		initializeBrowser(Config.Browser.browser);
		driver.navigate().to(Config.Url.base_url);
	}
	
	
	@AfterMethod
	public void tearDown(){
		quit();
	}

	@AfterClass
	public void afterClass(){
		System.out.println("After Class!");
	}
	
	@AfterTest
	public void afterTest(){
		System.out.println("After Test!");
	}

}

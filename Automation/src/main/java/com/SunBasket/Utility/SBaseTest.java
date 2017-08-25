package com.SunBasket.Utility;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.UUID;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

//@Listeners(com.SunBasket.Utility.SBListeners.class)
@Listeners(com.SunBasket.Utility.ExtentReporterNG.class)
public class SBaseTest extends DriverScript{


	public WebDriver setBrowser(String browser, String version, String os, Method method){
        try {
			createDriver(browser, version, os, method.getName());
		} catch (UnexpectedException | MalformedURLException e) {
			e.printStackTrace();
		}
        WebDriver webDriver = getWebDriver();
        System.out.println("Browser : " + browser + " Version : " + version + " OS : " + os + " Method : " + method );
        System.out.println("SessionID : " + getSessionId());
        return webDriver;
	}
	

	@BeforeTest
	public void beforeTest(){
		System.out.println("Before Test!");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Before Class!");
	}

	@BeforeMethod
	public void setSauceLabs(){
		System.out.println("setSauceLabs....");
	}
   
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) dr.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        dr.get().quit();
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

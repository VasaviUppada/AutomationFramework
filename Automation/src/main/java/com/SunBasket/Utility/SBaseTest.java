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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

//@Listeners(com.SunBasket.Utility.SBListeners.class)
public class SBaseTest extends DriverScript{


	public WebDriver setBrowser(String browser, String version, String os, Method method){
        try {
			createDriver(browser, version, os, method.getName());
		} catch (UnexpectedException | MalformedURLException e) {
			e.printStackTrace();
		}
        WebDriver webDriver = getWebDriver();
        logger.log(Status.INFO,"Remote WebDriver : " +   browser + " / " + version + " / " + os);
        logger.log(Status.INFO, "Session ID : " + getSessionId());
        extent.setSystemInfo("BROWSER", browser);
        extent.setSystemInfo("VERSION", version);
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("SESSION ID", getSessionId());
        return webDriver;
	}
	

	@BeforeTest
	public void beforeTest(){
	}

	@BeforeMethod
	public void setSauceLabs(Method method){
		logger = extent.createTest(method.getName());
		extent.setSystemInfo("Test Name", method.getName());
	}
   
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) dr.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        logger.log(Status.INFO, "Quit Browser");
        dr.get().quit();
    }
	
	@AfterTest
	public void afterTest(){
	}

}

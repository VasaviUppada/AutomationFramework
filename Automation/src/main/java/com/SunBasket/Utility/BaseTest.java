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
import org.testng.ISuite;
import org.testng.ITestClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

@Listeners(com.SunBasket.Utility.SBListeners.class)
public class BaseTest extends DriverScript{


	public WebDriver setBrowser(String browser, String version, String os, Method method){
        try {
			createDriver(browser, version, os, method.getName());
		} catch (UnexpectedException | MalformedURLException e) {
			e.printStackTrace();
		}
        logger.log(Status.INFO,"Remote WebDriver : " +   browser + " / " + version + " / " + os);
        logger.log(Status.INFO, "Session ID : " + getSauceSessionId());
        extent.setSystemInfo("BROWSER", browser);
        extent.setSystemInfo("VERSION", version);
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("SESSION ID", getSauceSessionId());
        return driver;
	}

	@BeforeClass
	public static void beforeClass() {
		String name = System.getenv("JOB_NAME");
		batchInfo = new BatchInfo(name != null ? name : "LocalBatchJob");
		String batchId = System.getenv("APPLITOOLS_BATCH_ID");  // To use Jenkins Plugin
		if (batchId != null){ batchInfo.setId(System.getenv("APPLITOOLS_BATCH_ID")); }
	}
	
	/*** We need this to run tests through pom.xml & through Jenkins ***/
	@Parameters({"browser", "version", "os"})
	@BeforeMethod
	public void setSauceLab(@Optional("chrome")String browser, @Optional("60.0")String version, @Optional("macOS 10.12")String os, Method method){
//		logger = extent.createTest(method.getName());
		logger = parent_logger.createNode(method.getName());
		extent.setSystemInfo("Test Name", method.getName());
		setBrowser(browser, version, os, method);
        setupApplitools(method.getName(), 1250, 900);
        logger.log(Status.PASS, "Browser Set Up");
	}
		
/*
	@BeforeMethod
	public void setSauceLabs(Method method){
		logger = parent_logger.createNode(method.getName());
		extent.setSystemInfo("Test Name", method.getName());
		setDriver(setBrowser(Config.Browser.browser, Config.Browser.version, Config.Browser.os, method));
		logger.info("Config.Browser.browser : " + Config.Browser.browser);
		logger.info("Config.Browser.version : " + Config.Browser.version);
		logger.info("Config.Browser.os : " + Config.Browser.os);
		logger.log(Status.PASS, "Browser Set Up");
		setupApplitools();
	}
*/

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        logger.log(Status.INFO, "Quit Browser");
//        eyes.close(false);
        TestResults applitools_results = eyes.close(false);
        assert(applitools_results.getMismatches() > 0);
        driver.quit();
        eyes.abortIfNotClosed();
    }


}

package com.SunBasket.Utility;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.SunBasket.Config.Config;
import com.SunBasket.Config.Config.Browser;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.aventstack.extentreports.Status;

@Listeners(com.SunBasket.Utility.SBListeners.class)
public class BaseTest_Local extends DriverScript{

	@BeforeClass
	public static void beforeClass() {
		String name = System.getenv("JOB_NAME");
		batchInfo = new BatchInfo(name != null ? name : "BatchJob");
		String batchId = System.getenv("APPLITOOLS_BATCH_ID");  // To use Jenkins Plugin
		if (batchId != null){ batchInfo.setId(System.getenv("APPLITOOLS_BATCH_ID")); }
	}

/*** We need this to run tests through testng.xml ***/
	@Parameters({"browser"})
	@BeforeMethod
	public static void setUp(@Optional("chrome")String browser, Method method){
		logger = parent_logger.createNode(method.getName());
		extent.setSystemInfo("Test Name", method.getName());
		driver = initializeBrowser(browser);
		setupApplitools(method.getName(), 1400, 650);
		driver.navigate().to(Config.Url.url_Base);
		logger.log(Status.PASS, "Browser Set Up");
	}

	
/*** We need this to run tests through .class file ***/
//	@BeforeMethod
//	public void setUp(){
//		logger = parent_logger.createNode("Test");
//		initializeBrowser(Browser.browser);
//		getDriver().navigate().to(Config.Url.url_Base);
//		logger.log(Status.PASS, "Browser Set Up");
//	}


/*** We need this to run Jobs through Jenkins ***/
//	@BeforeMethod
//	public void setUp(String browser, Method method){
//		logger = parent_logger.createNode(method.getName());
//		extent.setSystemInfo("Test Name", method.getName());
//		initializeBrowser(browser);
//		getDriver().navigate().to(Config.Url.url_Base);
//		logger.log(Status.PASS, "Browser Set Up");
//	}
	

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        TestResults applitools_results = eyes.close(false);
        assert(applitools_results.getMismatches() > 0);
        quit();
        eyes.abortIfNotClosed();
    }


}
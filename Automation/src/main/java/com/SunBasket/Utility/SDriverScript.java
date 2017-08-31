package com.SunBasket.Utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.LinkedList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketPromoPage;

public class SDriverScript{

	public static final String USERNAME = System.getenv(Config.SauceLabs.sauceUser); 
	public static final String ACCESS_KEY = System.getenv(Config.SauceLabs.sauceKey); 
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	protected String browser;
	protected String os;
	protected String version;
	protected String sessionId;
	protected WebDriver driver;

	public SDriverScript(String os, String version, String browser) { 
		super(); 
		this.os = os; 
		this.version = version; 
		this.browser = browser; 
	}
	

	public static LinkedList<String[]> browsersStrings() { 
		LinkedList<String[]> browser = new LinkedList<String[]>();
		browser.add(new String[]{"OS X 10.10", "54.0", "chrome"}); 
		browser.add(new String[]{"OS X 10.10", "8.0", "safari"});  
		browser.add(new String[]{"OS X 10.10", "47.0", "firefox"}); 
		return browser; 
	}
	
	@Parameters({"browser", "version", "os"})
	@BeforeMethod
	public void setUp() throws Exception { 
		
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser); 
		capabilities.setCapability(CapabilityType.VERSION, version); 
		capabilities.setCapability(CapabilityType.PLATFORM, os);
		driver = new RemoteWebDriver(new URL(URL), capabilities); 
		this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString(); 
		String message = String.format("SauceOnDemandSessionID=%1$s", this.sessionId); 
		System.out.println(message); 
	}
	
    protected static void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);
/*
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }
        URL url = new URL("https://" + Config.SauceLabs.sauceUser + ":" + Config.SauceLabs.sauceKey + "@ondemand.saucelabs.com:443/wd/hub");
        // Launch remote browser and set it as the current thread
        dr.set(new RemoteWebDriver(url,capabilities));

        // set current sessionId
      String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
      sessionId.set(id);     
*/
       
    }
	
	@Test 
	public void testSampleTestCaseSauceLabs() { 
		driver.get(Config.Url.url_MultiWeekPromo);

        SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageToLoad();
	}
	
	@AfterMethod
	public void tearDown() throws Exception { 
		driver.quit(); 
	}
	
}



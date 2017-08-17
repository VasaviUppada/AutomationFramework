package com.SunBasket.Utility;

import java.net.URL;
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
		LinkedList<String[]> browsers = new LinkedList<String[]>();
		// windows 7, IE 9 
		browsers.add(new String[]{"Windows 7", "9", "internet explorer"}); 
		// windows 8, IE 10 
		browsers.add(new String[]{"Windows 8", "10", "internet explorer"}); 
		// windows 8.1, IE 11 
		browsers.add(new String[]{"Windows 8.1", "11", "internet explorer"}); 
		return browsers; 
	} 
	
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
	
	@Test 
	public void testSampleTestCaseSauceLabs() { 
		driver.get(Config.Url.MultiWeekPromo_url);

        SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		sunBasketGetStartedPage.waitForPageToLoad();
	}
	
	@AfterMethod
	public void tearDown() throws Exception { 
		driver.quit(); 
	}
	
}



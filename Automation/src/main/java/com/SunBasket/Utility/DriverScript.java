package com.SunBasket.Utility;

import java.awt.Toolkit;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.DataProvider;

import com.SunBasket.Config.Config;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class DriverScript {
	
	public static WebDriver driver;
	public static String buildTag = System.getenv("BUILD_TAG");
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> sessionId = new ThreadLocal<String>();
   
	public static ExtentReports report = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
//	public static ExtentReports report = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport.html");
	public static ExtentTest logger;
	
	public static void initializeBrowser(String browser){
		browserOptions(browser);
	}
	
	public static void browserOptions(String browser){
		if(driver == null){
			System.out.println("Initializing Driver : " + browser);
			System.out.println("System Properties : " + System.getProperty("profile.name"));
			String os = System.getProperty("os.name").toLowerCase();
			
			switch(browser){
				case "firefox" :
					if(os.contains("mac")){
						System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/geckodriver");
					}
					else{
						System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
					}				
					ProfilesIni profile = new ProfilesIni();
					FirefoxProfile myprofile = profile.getProfile("default");
					DesiredCapabilities dc = DesiredCapabilities.firefox();
					dc.setCapability(FirefoxDriver.PROFILE, myprofile);
					dc.setCapability("marionette", true);
					driver = new FirefoxDriver(dc);
					break;
				case "chrome" :
					driver = new ChromeDriver();
					if(os.contains("mac")){
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
					}
					else{
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
					}
					break;
				case "saucelabs" :
					System.out.println("Sauce Labs!");
				default :
						System.out.println("Choosing Chrome Browser by default ...");
			}
			maximizeScreen(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();	
		}

	}
		
	public static void maximizeScreen(WebDriver driver) {
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		driver.manage().window().setPosition(position);
		Dimension maximizedScreenSize =
		new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(maximizedScreenSize);
		}

	public static void close(){
		System.out.println("Closing Browser!");
		driver.close();
		driver = null;
	}
	
	public static void quit(){
		System.out.println("Quitting Browser!");
		driver.quit();
		driver = null;
	}

    public static WebDriver getWebDriver() {
    	System.out.println("WebDriver : " + dr.get());
        return dr.get();
    }

    public String getSessionId() {
        return sessionId.get();
    }
    
    protected static void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }
        URL url = new URL("https://" + Config.SauceLabs.sauceUser + ":" + Config.SauceLabs.sauceKey + "@ondemand.saucelabs.com:443/wd/hub");
        // Launch remote browser and set it as the current thread
        dr.set(new RemoteWebDriver(url,capabilities));
//      WebDriver driver = new RemoteWebDriver(url, capabilities);

        // set current sessionId
      String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
      sessionId.set(id);
      
    }

}
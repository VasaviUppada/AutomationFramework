package com.SunBasket.Utility;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.SunBasket.Config.Config;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DriverScript {

	public static WebDriver driver;
	public static Eyes eyes;
	protected static BatchInfo batchInfo;	
	public static String buildTag = System.getenv("BUILD_TAG");
	public static ThreadLocal<String> sessionId = new ThreadLocal<String>();
    public static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/target/ExtentReports/ExtentReport" + ".html");
    public static ExtentReports extent = new ExtentReports();
    public static ExtentTest parent_logger;
    public static ExtentTest logger;
    public static String applitoolsApiKey = Config.Applitools.myApiKey;
    
    protected static void createFolders() {
        try {
            File dir = new File("target/ExtentReports");
            if (!dir.exists()) {
                dir.mkdir();
            }

            dir = new File("target/Screenshots_Extent");
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (SecurityException e) {
            parent_logger.log(Status.INFO, "Could not create Extent Report folders");
        }
    }

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        return formater.format(calendar.getTime());
    }

	public static WebDriver initializeBrowser(String browser){
		return createBrowserInstance(browser);
	}
	
	public static WebDriver createBrowserInstance(String browser){
		WebDriver webDriver = null;
			String os = System.getProperty("os.name").toLowerCase();
			if (browser.toLowerCase().contains("firefox")){
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
				webDriver = new FirefoxDriver(dc);
//				webDriver.manage().window().maximize(); // commenting for Applitool's View port size 
			}
			else if(browser.toLowerCase().contains("chrome")){
				if(os.contains("mac")){
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
				}
				else{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
				}
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				webDriver = new ChromeDriver(cap);
//				maximizeScreen(webDriver); // commenting for Applitool's View port size
			}
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webDriver.manage().deleteAllCookies();
			return webDriver;
	}

	public static void maximizeScreen(WebDriver driver) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Point position = new Point(0, 0);
		driver.manage().window().setPosition(position);
		int Width = (int) toolkit.getScreenSize().getWidth();
		int Height = (int)toolkit.getScreenSize().getHeight();
		//For Dimension class, Import following library "org.openqa.selenium.Dimension"  
		driver.manage().window().setSize(new Dimension(Width,Height));
		}
	
	public static void close(){
		logger.log(Status.INFO, "Close Browser");
        if (driver != null) {
            driver.close();
        }
//		driver.close();
//		driver = null;
	}

	public static void quit(){
		logger.log(Status.INFO, "Quit Browser");
        if (driver != null) {
            driver.quit();
        }
//		driver.quit();
//		driver = null;
	}

    public static String getSauceSessionId() {
        return sessionId.get();
    }

    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("screenResolution", "1280x1024"); //1250, 900 Added for Applitools viewport size
        capabilities.setCapability("name", methodName);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }
        URL url = new URL("https://" + Config.SauceLabs.sauceUser + ":" + Config.SauceLabs.sauceKey + "@ondemand.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url,capabilities);
        String id = ((RemoteWebDriver) driver).getSessionId().toString();
        sessionId.set(id);
    }
    
    protected static void setupApplitools(String string, int X, int Y) {
		DriverScript.eyes = new Eyes();
		DriverScript.eyes.setApiKey(DriverScript.applitoolsApiKey);
		DriverScript.eyes.setForceFullPageScreenshot(true);  // Turn in to 'false' -- To take full page screenshot 
		DriverScript.eyes.setStitchMode(StitchMode.CSS);
		eyes.setBatch(batchInfo);
		eyes.setIgnoreCaret(true);
//		eyes.setIsDisabled(true);  // To disable Applitools
        eyes.open(driver, "SunBasket", string, new RectangleSize(X, Y));
    }

	public static void getScreentShotForExtentReport(String screenshotName){
		String getScreenshotPath = SBUtil.getScreenshotPath();
        try {
    		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath).build();
    		logger.info(screenshotName, mediaModel);
			logger.addScreenCaptureFromPath(getScreenshotPath, "Scr");
		} catch (IOException e) {
			logger.log(Status.FAIL, "Fail to capture the screenshot");
			e.printStackTrace();
		}
	}

}
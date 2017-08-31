package com.SunBasket.Utility;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import com.SunBasket.Config.Config;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DriverScript {

	public static WebDriver driver;
	public static String buildTag = System.getenv("BUILD_TAG");
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> sessionId = new ThreadLocal<String>();

//    public static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/target/ExtentReports/ExtentReport-" + getCurrentTime() + ".html");
    public static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/target/ExtentReports/ExtentReport" + ".html");
    public static ExtentReports extent = new ExtentReports();
    public static ExtentTest logger;

    private static void createFolders() {
        try {
            File dir = new File("target/ExtentReports");

            if (!dir.exists()) {
                dir.mkdir();
            }

            dir = new File("target/Screenshots_Extent");

            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (IOException e) {
            logger.log(Status.INFO, "Could not create Extent Report folders");
        }
    }

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        return formater.format(calendar.getTime());
    }

	public static void initializeBrowser(String browser){
		browserOptions(browser);
	}

	public static void browserOptions(String browser){
		if(driver == null){
			logger.log(Status.INFO, browser + " Browser Started");
			logger.log(Status.INFO, "System Properties : " + System.getProperty("profile.name"));
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
		logger.log(Status.INFO, "Close Browser");
		driver.close();
		driver = null;
	}

	public static void quit(){
		logger.log(Status.INFO, "Quit Browser");
		driver.quit();
		driver = null;
	}

    public static WebDriver getWebDriver() {
//		logger.log(Status.INFO, "Web Driver : " + dr.get());
        return dr.get();
    }

    public static String getSessionId() {
//    	logger.log(Status.INFO, "Session ID_ " + sessionId.get());
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

        // set current sessionId
      String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
      sessionId.set(id);
    }


	public void getScreentShotForExtentReport(String screenshotName){
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
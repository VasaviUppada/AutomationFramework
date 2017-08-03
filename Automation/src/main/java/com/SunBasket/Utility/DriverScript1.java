package com.SunBasket.Utility;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.SunBasket.Config.Config;

//import com.SunBasket.Config.Config;

public class DriverScript {
	public static WebDriver driver;
	
	public static void initializeBrowser(String browser){
		if(driver == null){
			System.out.println("Initializing Driver : " + browser);
			String os = System.getProperty("os.name").toLowerCase();
			if(browser.equalsIgnoreCase("firefox")){
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
			}
			else if(browser.equalsIgnoreCase("chrome")){
				driver = new ChromeDriver();
				if(os.contains("mac")){
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
				}
				else{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
				}
			}
			else if(browser.equalsIgnoreCase("safari")){
				driver = new SafariDriver();
			}
		}
		
//		driver.manage().window().maximize();   // not working
		
		maximizeScreen(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}
	
	public static void initializeBrowser(){
		if(driver == null){
			System.out.println("Initializing Driver : " + Config.Browser.browser);
			String os = System.getProperty("os.name").toLowerCase();
			if(Config.Browser.browser.equalsIgnoreCase("firefox")){
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
			}
			else if(Config.Browser.browser.equalsIgnoreCase("chrome")){
				driver = new ChromeDriver();
				if(os.contains("mac")){
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
				}
				else{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
				}
			}
			else if(Config.Browser.browser.equalsIgnoreCase("safari")){
				driver = new SafariDriver();
			}
		}
		
//		driver.manage().window().maximize();   // not working
		
		maximizeScreen(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
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
}
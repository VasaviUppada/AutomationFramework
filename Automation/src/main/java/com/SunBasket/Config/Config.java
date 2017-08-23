package com.SunBasket.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class Config {

	public static class Url{
		public static final String base_url	 = ReadPropertytFile().getProperty("url");
		
//		public final static String base_url				 = master_url;
		public static final String master_url			 = "https://master.sunbasket-staging.com";
		public static final String develop_url			 = "https://develop.sunbasket-staging.com";
		public final static String home_url				 = base_url + "/home";
		public final static String SingleWeekPromo_url 	 = base_url + "/?offer=QA-TEST35OFF";
		public final static String MultiWeekPromo_url	 = base_url + "?offer=QA-MW403010";	
		public final static String join_conFirmation_url = base_url + "/join/confirmation";

	}
	
	public static class Browser{
//		public static String browser = "firefox";
//		public static String browser = "chrome";
		public static String browser = ReadPropertytFile().getProperty("browser");
	}
	
	public static class PageTitle{
		public static final String pageTitle_HomePage  = "Sun Basket Meal Delivery Service | Fresh Organic and Sustainable Food & Healthy Recipes";
		public static final String pageTitle_PromoPage = "Sun Basket - Healthy organic meal delivery";
		public static final String pageTitle_SignInPage = "Sun Basket - Healthy organic meal delivery";
		public static final String pageTitle_GetStartedPage = "Sun Basket: Join";
		public static final String pageTitle_BuildYourOrderPage = "Sun Basket: Join";
		public static final String pageTitle_CompleteYourOrderPage = "Sun Basket: Join";
		public static final String pageTitle_JoinConfirmationPage = "Sun Basket: Join";
		public static final String pageTitle_ConfirmYourMealsPage = "Sun Basket: Confirm Your Meals";
		public static final String pageTitle_MyAccountPage = "Sun Basket: My Account";
	}
	
	public static class SauceLabs{
	    public static final String sauceUser      = ReadPropertytFile().getProperty("sauceUser");
	    public static final String sauceKey       = ReadPropertytFile().getProperty("sauceKey");
//	    public static final String sauceUser      = "sbdevops";
//	    public static final String sauceKey       = "92c7dc53-cca0-4efe-b674-0e30e4814005";
	    public static final boolean browserMob    = true;
	}
	
    @DataProvider(name = "saucelabsBrowsers", parallel = true)
    public static Object[][] sauceBrowsers(Method testMethod) {
        return new Object[][]{
//                new Object[]{"MicrosoftEdge", "15", "Windows 10"},
//                new Object[]{"firefox", "54.0", "Windows 10"},
//                new Object[]{"internet explorer", "10.0", "Windows 7"},
//                new Object[]{"firefox", "47.0", "OS X 10.10"}, 
        		  new Object[]{"chrome", "54.0", "OS X 10.10"},
//        		  new Object[]{"safari", "8.0", "OS X 10.10"},
        };
    }
	
    
	protected static ArrayList<String> getPropertyFileList(){
//		ArrayList<String> propertyFileList = super.getPropertyFileList();
		ArrayList<String> propertyFileList = new ArrayList<String>();
		if("develop.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-develop.properties");
		else if("master.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-master.properties");
		else if("prod.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-prod.properties");
		else if("qa.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-qa.properties");
		else if("develop-sauce.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-develop-sauce.properties");
		else if("master-sauce.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-master-sauce.properties");
		else if("prod-sauce.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-prod-sauce.properties");
		else if("qa-sauce.profiles".equalsIgnoreCase(System.getProperties().getProperty("profile.name")))
			propertyFileList.add("/resources/application-qa-sauce.properties");
		else
			System.out.println("We don't have properties with profile.name : " + System.getProperties().getProperty("profile.name"));	
		return propertyFileList;
		
	}    

	public static Properties ReadPropertytFile() {
//		ArrayList<String> propertyFileList = getPropertyFileList();
		Properties property;
		FileInputStream fs = null;
			property = new Properties();
			try {
//				fs  = new FileInputStream(System.getProperty("user.dir") + "/resources" + "/application-master.properties");
				fs  = new FileInputStream(System.getProperty("user.dir") + loadProperties().get(0));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			property = new Properties();
			try {
				property.load(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return property;

	}
	
	protected static ArrayList<String> loadProperties(){
		ArrayList<String> propertyFileList = getPropertyFileList();
		return propertyFileList;
	}
    
    
}

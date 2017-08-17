package com.SunBasket.Config;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class Config {

	public static class Url{
//		public static String base_url = "https://sunbasket.com";
		public static final String master_url			 = "https://master.sunbasket-staging.com";
		public static final String develop_url			 = "https://develop.sunbasket-staging.com";
		public static final String base_url				 = master_url;
		
		public static final String home_url				 = base_url + "/home";
		public static final String SingleWeekPromo_url 	 = base_url + "/?offer=QA-TEST35OFF";
		public static final String MultiWeekPromo_url	 = base_url + "?offer=QA-MW403010";	
		public static final String join_conFirmation_url = base_url + "/join/confirmation";
	}
	
	public static class Browser{
//		public static String browser = "firefox";
		public static String browser = "chrome";
	}
	
	public static class PageTitle{
		public static final String pageTitle_HomePage  = "Sun Basket - Healthy organic meal delivery";
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
	    public static final String sauceUser      = "sbdevops";
	    public static final String sauceKey       = "92c7dc53-cca0-4efe-b674-0e30e4814005";
	    public static final boolean browserMob    = true;
	}
	
    @DataProvider(name = "saucelabsBrowsers", parallel = true)
    public static Object[][] sauceBrowsers(Method testMethod) {
        return new Object[][]{
//                new Object[]{"MicrosoftEdge", "15", "Windows 10"},
//                new Object[]{"firefox", "54.0", "Windows 10"},
//                new Object[]{"internet explorer", "10.0", "Windows 7"},
//                  new Object[]{"firefox", "47.0", "OS X 10.10"}, 
        		  new Object[]{"chrome", "54.0", "OS X 10.10"},
        		  new Object[]{"safari", "8.0", "OS X 10.10"},
        };
    }
	
}

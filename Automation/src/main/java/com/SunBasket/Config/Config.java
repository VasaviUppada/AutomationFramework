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
		public static final String url_Base	 = ReadPropertytFile().getProperty("url");

		public static final String url_Master			 	= "https://master.sunbasket-staging.com";
		public static final String url_Develop				= "https://develop.sunbasket-staging.com";	
		public static final String url_Home				 	= url_Base + "/home";
		public static final String url_SingleWeekPromo 	 	= url_Base + "/?offer=QA-TEST35OFF";
		public static final String url_MultiWeekPromo	 	= url_Base + "/?offer=QA-MW403010";	
		public static final String url_Menu				 	= url_Base + "/menu";
		public static final String url_MenuClassic		 	= url_Base + "/menu#classic";
		public static final String url_MenuFamily		 	= url_Base + "/menu#family_plan";
		public static final String url_Values			 	= url_Base + "/values";
		public static final String url_Farms			 	= url_Base + "/farms";
		public static final String url_Pricing 			 	= url_Base + "/pricing";
		public static final String url_Login			 	= url_Base + "/login";
		public static final String url_Join				 	= url_Base + "/join";
		public static final String url_JoinSetup		 	= url_Base + "/join/setup";
		public static final String url_MenuConfirmation	 	= url_Base + "/join/menuconfirmation";
		public static final String url_JoinPayment		 	= url_Base + "/join/payment";
		public static final String url_JoinConfirmation	 	= url_Base + "/join/confirmation";
		public static final String url_MyAccount		 	= url_Base + "/my-account";
		public static final String url_BillingHistory	 	= url_Base + "/my-account#billing-history-section";
		public static final String url_Credits			 	= url_Base + "/my-account#credits-section";
		public static final String url_Alerts			 	= url_Base + "/my-account#alerts-section";
		public static final String url_MyMenu			 	= url_Base + "/mymenu";
		public static final String url_Schedule_Upcoming 	= url_Base + "/account/schedule";
		public static final String url_Schedule_History	 	= url_Base + "/history";
		public static final String url_Explore_TheSunTimes	= url_Base + "/the-sun-times";
		public static final String url_Explore_Recipes		= url_Base + "/recipes";
		public static final String url_Explore_Videos		= url_Base + "/videos";
		public static final String url_Explore_Tips			= url_Base + "/tips";
		public static final String url_Explore_Stories		= url_Base + "/stories";
		public static final String url_ReferAFriend			= url_Base + "/refer-a-friend";
		public static final String url_GiftSelect			= url_Base + "/gift/select";
		public static final String url_GiftDetails			= url_Base + "/gift/details";
		public static final String url_GiftPayment			= url_Base + "/gift/payment";
		public static final String url_GiftConfirmation		= url_Base + "/gift/confirmation";
		public static final String url_Recycle				= url_Base + "/recycle";
		public static final String url_Help					= "https://sunbasket.force.com/Help/s/";
		public static final String url_Paypal				= "https://checkout.paypal.com/";
		
	}
	
	public static class Browser{
		public static String browser = ReadPropertytFile().getProperty("browser");
		public static String version = ReadPropertytFile().getProperty("version");
		public static String os = ReadPropertytFile().getProperty("os");
	}
	
	public static class PageTitle{
		public static final String pageTitle_HomePage  = "Sun Basket Meal Delivery Service | Fresh Organic and Sustainable Food & Healthy Recipes";
		public static final String pageTitle_PromoPage = "Sun Basket Meal Delivery Service | Fresh Organic and Sustainable Food & Healthy Recipes";
		public static final String pageTitle_SignInPage = "Sun Basket: Login and Choose Your Weekly Recipes | Healthy Meals Delivered | Sun Basket";
		public static final String pageTitle_GetStartedPage = "Sun Basket: Sign Up for Weekly Healthy Meal Kit Delivery | Sun Basket";
		public static final String pageTitle_BuildYourOrderPage = "Sun Basket: Build Your Order | Sun Basket";
		public static final String pageTitle_CompleteYourOrderPage = "Sun Basket: Complete Your Order | Sun Basket";
		public static final String pageTitle_JoinConfirmationPage = "Sun Basket: Confirm Your Meals | Sun Basket";
		public static final String pageTitle_ConfirmYourMealsPage = "Sun Basket: Confirm Your Meals";
		public static final String pageTitle_MyAccountPage = "Sun Basket: My Account";
	}
	
	public static class SauceLabs{
	    public static final String sauceUser      = ReadPropertytFile().getProperty("sauceUser");
	    public static final String sauceKey       = ReadPropertytFile().getProperty("sauceKey");
	    public static final boolean browserMob    = true;
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
//			propertyFileList.add("/resources/application-master.properties");
		return propertyFileList;
		
	}    

	public static Properties ReadPropertytFile() {
//		ArrayList<String> propertyFileList = getPropertyFileList();
		Properties property;
		FileInputStream fs = null;
			property = new Properties();
			try {
//				fs  = new FileInputStream(System.getProperty("user.dir") + "/resources" + "/master.properties");
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

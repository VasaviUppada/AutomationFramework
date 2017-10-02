package com.SunBasket.AutomationTests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.SunBasket.Pages.SunBasketBuildYourOrderPage;
import com.SunBasket.Pages.SunBasketCompleteYourOrderPage;
import com.SunBasket.Pages.SunBasketConfirmYourMealsPage;
import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Pages.SunBasketMyMenuPage;
import com.SunBasket.Pages.SunBasketPromoPage;
import com.SunBasket.Pages.SunBasketSchedulePage;
import com.SunBasket.Pages.SunBasketSignInPage;
import com.SunBasket.Pages.SunBasketTheSunTimesPage;
import com.SunBasket.Utility.BaseTest;

public class Test_AllLinks extends BaseTest{
	
	@Test(description="Verifies all Links on SunBasketHomePage!")
	public void AllLinks_SunBasketHomePage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();		
		sunBasketHomePage.action_VerifyBrokenLinks(sunBasketHomePage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketGetStartedPage!")
	public void AllLinks_SunBasketGetStartedPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();		
		sunBasketHomePage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_VerifyBrokenLinks(sunBasketGetStartedPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketSignInPage!")
	public void AllLinks_SunBasketSignInPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_VerifyBrokenLinks(sunBasketSignInPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketGetMyMenuPage!")
	public void AllLinks_SunBasketMyMenuPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn("vasavitest@test.com");
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage(driver);
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.action_VerifyBrokenLinks(sunBasketMyMenuPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketSchedulePage!")
	public void AllLinks_SunBasketSchedulePage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn("vasavitest@test.com");
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage(driver);
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.tab_Schedule.click();
		SunBasketSchedulePage sunBasketSchedulePage = new SunBasketSchedulePage(driver);
		sunBasketSchedulePage.waitForPageToLoad();
		sunBasketSchedulePage.action_VerifyBrokenLinks(sunBasketSchedulePage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketGetTheSunTimesPage!")
	public void AllLinks_SunBasketTheSunTimesPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn("auto002@auto.com");
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage(driver);
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.tab_TheSunTimes.click();
		SunBasketTheSunTimesPage sunBasketTheSunTimesPage = new SunBasketTheSunTimesPage(driver);
		sunBasketTheSunTimesPage.waitForPageToLoad();
		sunBasketTheSunTimesPage.action_VerifyBrokenLinks(sunBasketTheSunTimesPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketPromoPage!")
	public void AllLinks_SunBasketPromoPage(){
		driver.navigate().to(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		sunBasketPromoPage.waitForPageToLoad();
		sunBasketPromoPage.action_VerifyBrokenLinks(sunBasketPromoPage.link_allLinks);
	}

	@Test(description="Verifies all Links on SunBasketBuildYourOrderPage!")
	public void AllLinks_SunBasketBuildYourOrderPage(){
		driver.navigate().to(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		sunBasketPromoPage.waitForPageToLoad();
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		sunBasketBuildYourOrderPage.waitForPageToLoad();
		sunBasketBuildYourOrderPage.action_VerifyBrokenLinks(sunBasketBuildYourOrderPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketConfirmYourMealsPage!")
	public void AllLinks_SunBasketConfirmYourMealsPage(){
		driver.navigate().to(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		sunBasketPromoPage.waitForPageToLoad();
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		sunBasketBuildYourOrderPage.waitForPageToLoad();
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		sunBasketConfirmYourMealsPage.waitForPageToLoad();
		sunBasketConfirmYourMealsPage.action_VerifyBrokenLinks(sunBasketConfirmYourMealsPage.link_allLinks);
	}
	
	@Test(description="Verifies all Links on SunBasketCompleteYourOrderPage!")
	public void AllLinks_SunBasketCompleteYourOrderPage(){
		driver.navigate().to(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		sunBasketPromoPage.waitForPageToLoad();
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		sunBasketBuildYourOrderPage.waitForPageToLoad();
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrder = new SunBasketCompleteYourOrderPage(driver);
		sunBasketCompleteYourOrder.waitForPageToLoad();
		sunBasketCompleteYourOrder.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrder.action_VerifyBrokenLinks(sunBasketCompleteYourOrder.link_allLinks);
	}
	
	@Test(description = "")
	public void AllLinks_SunBasketMyAccountPage(){
		// Need to create elements
	}
	
	
}

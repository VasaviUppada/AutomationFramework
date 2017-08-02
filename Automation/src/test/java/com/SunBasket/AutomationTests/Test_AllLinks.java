package com.SunBasket.AutomationTests;

import org.testng.annotations.Test;

import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Pages.SunBasketMyMenuPage;
import com.SunBasket.Pages.SunBasketSchedulePage;
import com.SunBasket.Pages.SunBasketSignInPage;
import com.SunBasket.Pages.SunBasketTheSunTimesPage;
import com.SunBasket.Utility.BaseTest;

public class Test_AllLinks extends BaseTest{
	
	@Test
	public void AllLinks_HomePage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();		
		sunBasketHomePage.action_VerifyBrokenLinks(sunBasketHomePage.link_allLinks);
	}
	
	@Test
	public void AllLinks_GetStartedPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();		
		sunBasketHomePage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_VerifyBrokenLinks(sunBasketGetStartedPage.link_allLinks);
	}
	
	@Test
	public void AllLinks_SignInPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_VerifyBrokenLinks(sunBasketSignInPage.link_allLinks);
	}
	
	@Test
	public void AllLinks_MyMenuPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn();
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage();
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.action_VerifyBrokenLinks(sunBasketMyMenuPage.link_allLinks);
	}
	
	@Test
	public void AllLinks_SchedulePage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn();
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage();
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.tab_Schedule.click();
		SunBasketSchedulePage sunBasketSchedulePage = new SunBasketSchedulePage();
		sunBasketSchedulePage.waitForPageToLoad();		
	}
	
	@Test
	public void AllLinks_TheSunTimesPage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn();
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage();
		sunBasketMyMenuPage.waitForPageToLoad();
		sunBasketMyMenuPage.tab_TheSunTimes.click();
		SunBasketTheSunTimesPage sunBasketTheSunTimesPage = new SunBasketTheSunTimesPage();
		sunBasketTheSunTimesPage.waitForPageToLoad();
		sunBasketTheSunTimesPage.action_VerifyBrokenLinks(sunBasketTheSunTimesPage.link_allLinks);
	}

}

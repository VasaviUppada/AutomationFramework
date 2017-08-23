package com.SunBasket.AutomationTests;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.SunBasket.Pages.SunBasketBuildYourOrderPage;
import com.SunBasket.Pages.SunBasketCompleteYourOrderPage;
import com.SunBasket.Pages.SunBasketConfirmYourMealsPage;
import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Pages.SunBasketJoinConfirmationPage;
import com.SunBasket.Pages.SunBasketMyAccountPage;
import com.SunBasket.Pages.SunBasketPromoPage;
import com.SunBasket.Pages.SunBasketSignInPage;
import com.SunBasket.Utility.SBUtil;
import com.SunBasket.Utility.SBaseTest;

public class STest_WebSmokeChecklist extends SBaseTest{

	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C1_SunBasket_JoinFlow_SingleWeek_001(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
        driver.get(Config.Url.SingleWeekPromo_url);
        SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");

		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "Order today and get $35 off your first delivery");

		// Step 3
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		sunBasketBuildYourOrderPage.waitForPageToLoad(30);
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.waitForPageToLoad();
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		
		// Step 5
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		
		// Step 6
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");

	}
	
	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C6_SunBasket_JoinFlow_SingleWeek_002(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
        driver.get(Config.Url.SingleWeekPromo_url);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		
		// Step 3
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 5
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "Order today and get $35 off your first delivery");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		System.out.println("Email : " + email);
		
		// Step 6
		sunBasketHomePage.action_SignOut();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_SignInPage);
		sunBasketSignInPage.action_signIn(email);
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 7
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 8
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();

		// Step 9
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		sunBasketJoinConfirmationPage.waitForPageToLoad();
		
		// Step 10
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		
		// Step 11
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");
		
	}

	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C7_SunBasket_JoinFlow_MultiWeek_003(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
        driver.get(Config.Url.MultiWeekPromo_url);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 3
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 5
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		
		// Step 6
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");		
	}
	
	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C8_SunBasket_JoinFlow_MultiWeek_004(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
        driver.get(Config.Url.MultiWeekPromo_url);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		
		// Step 3
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 5
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 6
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		System.out.println("Email : " + email);
		sunBasketHomePage.action_SignOut();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_SignInPage);
		sunBasketSignInPage.action_signIn(email);
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 7
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 8
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");	
		
		// Step 9
		String parentWindowHandler = driver.getWindowHandle();
		sunBasketCompleteYourOrderPage.action_ClickOnSubmitButton(sunBasketCompleteYourOrderPage.button_CheckoutWithPaypal);
		sunBasketCompleteYourOrderPage.action_checkOutWithPayPal(parentWindowHandler);
		sunBasketCompleteYourOrderPage.action_ClickOnSubmitButton(sunBasketCompleteYourOrderPage.button_SubmitOrder);
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		sunBasketJoinConfirmationPage.waitForPageToLoad();
		
		// Step 10
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");	
	}
	
	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C9_SunBasket_JoinFlow_SingleWeek_005(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
        driver.get(Config.Url.SingleWeekPromo_url);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);	
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);	
		sunBasketBuildYourOrderPage.action_BuildYourOrder();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.clear();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.sendKeys("QA-MW403010");
		SBUtil.waitForElementToBeClickable(sunBasketCompleteYourOrderPage.button_ApplyPromoCode);
		sunBasketCompleteYourOrderPage.button_ApplyPromoCode.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		SBUtil.waitForTextToBePresentInElement(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");	
		
		// Step 2
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		sunBasketJoinConfirmationPage.waitForPageToLoad();
		
		// Step 3
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");		
	}
	
	@Test(dataProvider = "saucelabsBrowsers", dataProviderClass = Config.class)
	public void C10_SunBasket_JoinFlow_PromoCode_006(String browser, String version, String os, Method method){
		driver = setBrowser(browser, version, os, method);
		
        // Step 1
		driver.get(Config.Url.home_url);
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);	
		sunBasketBuildYourOrderPage.action_BuildYourOrder();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		
		// Step 2
		sunBasketCompleteYourOrderPage.textfield_PromoCode.clear();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.sendKeys("QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_ClickOnSubmitButton(sunBasketCompleteYourOrderPage.button_ApplyPromoCode);
		sunBasketCompleteYourOrderPage.button_ApplyPromoCode.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		SBUtil.waitForTextToBePresentInElement(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");	
		
		// Step 3
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		sunBasketJoinConfirmationPage.waitForPageToLoad();
		
		// Step 4
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");		
	}
	

}

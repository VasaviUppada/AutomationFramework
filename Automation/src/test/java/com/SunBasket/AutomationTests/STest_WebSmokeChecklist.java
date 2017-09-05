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
import com.aventstack.extentreports.Status;

public class STest_WebSmokeChecklist extends SBaseTest{

	@Test
	public void C1_SunBasket_JoinFlow_SingleWeek_001(){
		
        // Step 1
		logger.log(Status.INFO, "Navigate to SingleWeekPromoURL");
        driver.get(Config.Url.url_SingleWeekPromo);
        SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForUrlToBe(Config.Url.url_SingleWeekPromo);
		getScreentShotForExtentReport("SingleWeekJoinPage");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");
		
		
		// Step 2
		logger.log(Status.INFO, "Click on Get Started");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "Order today and get $35 off your first delivery");

		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "Order today and get $35 off your first delivery");
				
		// Step 5
		logger.log(Status.INFO, "Click on Continue");
		sunBasketConfirmYourMealsPage.button_Continue.click();
		logger.log(Status.PASS, "Menu Confirmed");
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlMatches(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		
		// Step 6
		logger.log(Status.PASS, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");
		getScreentShotForExtentReport("JoinPaymentPage");
	}
	
	@Test
	public void C6_SunBasket_JoinFlow_SingleWeek_002(){
		
        // Step 1
		logger.log(Status.INFO, "Navigate to SingleWeekPromoURL");
        driver.get(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForUrlToBe(Config.Url.url_SingleWeekPromo);
		getScreentShotForExtentReport("SingleWeekJoinPage");
		
		// Step 2
		logger.log(Status.INFO, "Click on get Started");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		
		// Step 3
		logger.log(Status.INFO, "Click on Get Started");
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		
		// Step 5
		logger.log(Status.INFO, "Click on SunBasket Image");
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForUrlToBe(Config.Url.url_Home);
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "Order today and get $35 off your first delivery");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		getScreentShotForExtentReport("HomePage");
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		
		// Step 6
		sunBasketHomePage.action_SignOut();
		logger.log(Status.PASS, "Signout success");
		logger.log(Status.INFO, "Click on SignIn");
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Login);
		sunBasketSignInPage.action_signIn(email);
		logger.log(Status.PASS, "SignIn success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		getScreentShotForExtentReport("JoinSetUpPage");
		
		// Step 7
		logger.log(Status.INFO, "Click on Continue");
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		
		// Step 8
		logger.log(Status.INFO, "Click on Continue");
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		logger.log(Status.INFO, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();

		// Step 9
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		logger.log(Status.PASS, "Place Order success");
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
		SBUtil.waitForUrlMatches(Config.Url.url_JoinConfirmation);
		getScreentShotForExtentReport("JoinConfirmationPage");
		
		// Step 10
		logger.log(Status.INFO, "Click on Menu");
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		logger.log(Status.INFO, "Click on My Account");
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForUrlMatches(Config.Url.url_MyAccount);

		// Step 11
		logger.log(Status.INFO, "Click on Credits & Coupons");
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		getScreentShotForExtentReport("MyAccountPage");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");			
	}

	@Test
	public void C7_SunBasket_JoinFlow_MultiWeek_003(){

        // Step 1
		logger.log(Status.INFO, "Navigate to MultiWeekPromoURL");
        driver.get(Config.Url.url_MultiWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MultiWeekPromo);
		getScreentShotForExtentReport("MultiWeekJoinPage");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 2
		logger.log(Status.INFO, "Click on get Started");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 5
		logger.log(Status.INFO, "Click on get Continue");
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		
		// Step 6
		logger.log(Status.INFO, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");		
	}
	
	@Test
	public void C8_SunBasket_JoinFlow_MultiWeek_004(){

        // Step 1
		logger.log(Status.INFO, "Navigate to MultiWeekPromoURL");
        driver.get(Config.Url.url_MultiWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MultiWeekPromo);
		getScreentShotForExtentReport("MultiWeekJoinPage");
		
		// Step 2
		logger.log(Status.INFO, "Click on get Started");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		
		// Step 5
		logger.log(Status.INFO, "Click on SunBasket Image");
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForUrlToBe(Config.Url.url_Home);
		getScreentShotForExtentReport("HomePage");
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 6
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		sunBasketHomePage.action_SignOut();
		logger.log(Status.PASS, "Signout success");
		logger.log(Status.INFO, "Click on SignIn");
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Login);
		getScreentShotForExtentReport("SignIn");
		sunBasketSignInPage.action_signIn(email);
		logger.log(Status.PASS, "SignIn success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		getScreentShotForExtentReport("JoinSetUpPage");
		
		// Step 7
		logger.log(Status.INFO, "Click on Continue");
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		
		// Step 8
		logger.log(Status.INFO, "Click on Continue");
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		logger.log(Status.INFO, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");	
		
		// Step 9
		String parentWindowHandler = driver.getWindowHandle();
		sunBasketCompleteYourOrderPage.action_ClickOnButton(sunBasketCompleteYourOrderPage.button_CheckoutWithPaypal);
		sunBasketCompleteYourOrderPage.action_checkOutWithPayPal(parentWindowHandler);
		logger.log(Status.PASS, "Paypal Payment Success");
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinConfirmation);
		getScreentShotForExtentReport("JoinConfirmationPage");
		
		// Step 10
		logger.log(Status.INFO, "Click on Menu");
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		logger.log(Status.INFO, "Click on My Account");
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForUrlMatches(Config.Url.url_MyAccount);
		logger.log(Status.INFO, "Click on Credits & Coupons");
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		getScreentShotForExtentReport("MyAccountPage");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");	
	}
	
	@Test
	public void C9_SunBasket_JoinFlow_SingleWeek_005(){
		
        // Step 1
		logger.log(Status.INFO, "Navigate to SingleWeekPromoURL");
        driver.get(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		SBUtil.waitForUrlToBe(Config.Url.url_SingleWeekPromo);
		getScreentShotForExtentReport("SingleWeekPromoPage");
		logger.log(Status.INFO, "Click on Get Started");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		logger.log(Status.INFO, "Click on Continue");
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		logger.log(Status.INFO, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		logger.log(Status.INFO, "Clear Single Promo code & Pass Multiple Promo code : QA-MW403010");
		sunBasketCompleteYourOrderPage.textfield_PromoCode.clear();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.sendKeys("QA-MW403010");
		logger.log(Status.INFO, "Click on Apply Promo code");
		SBUtil.waitForElementToBeClickable(sunBasketCompleteYourOrderPage.button_ApplyPromoCode);
		sunBasketCompleteYourOrderPage.button_ApplyPromoCode.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		SBUtil.waitForTextToBePresentInElement(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");	
		getScreentShotForExtentReport("Change PromoCode from Single to Multiple");
		
		// Step 2
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		logger.log(Status.PASS, "Place Order success");
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinConfirmation);
		getScreentShotForExtentReport("JoinConfirmationPage");
		// Step 3
		logger.log(Status.INFO, "Click on Menu");
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		logger.log(Status.INFO, "Click on My Account");
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForUrlMatches(Config.Url.url_MyAccount);
		logger.log(Status.INFO, "Click on Credits & Coupons");
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		getScreentShotForExtentReport("MyAccountPage");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");
		
	}
	
	@Test
	public void C10_SunBasket_JoinFlow_PromoCode_006(){

        // Step 1
		logger.log(Status.INFO, "Navigate to HomeURL");
		driver.get(Config.Url.url_Home);
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		SBUtil.waitForUrlToBe(Config.Url.url_Home);
		getScreentShotForExtentReport("HomePage");
		logger.log(Status.INFO, "Click on Get Started");
		sunBasketHomePage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		SBUtil.waitForUrlToBe(Config.Url.url_Join);
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "Join Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinSetup);
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "Join Setup Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		SBUtil.waitForUrlToBe(Config.Url.url_MenuConfirmation);
		getScreentShotForExtentReport("MenuConfirmationPage");
		logger.log(Status.INFO, "Click on Continue");
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		getScreentShotForExtentReport("ApplyFreeShippingPopup");
		logger.log(Status.INFO, "Click on Apply Free Shipping");
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		
		// Step 2

		logger.log(Status.INFO, "Clear Multiple Promo code & Pass Single Promo code : QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.textfield_PromoCode.clear();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.sendKeys("QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_ClickOnButton(sunBasketCompleteYourOrderPage.button_ApplyPromoCode);
		logger.log(Status.INFO, "Click on Apply Promo code");
		sunBasketCompleteYourOrderPage.button_ApplyPromoCode.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		SBUtil.waitForTextToBePresentInElement(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");	
		getScreentShotForExtentReport("Change PromoCode from Multiple to Single");
		
		// Step 3
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		logger.log(Status.PASS, "Place Order success");
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage();
		SBUtil.waitForUrlToBe(Config.Url.url_JoinConfirmation);
		getScreentShotForExtentReport("JoinConfirmationPage");
		
		// Step 4
		logger.log(Status.INFO, "Click on Menu");
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		logger.log(Status.INFO, "Click on My Account");
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage();
		SBUtil.waitForUrlMatches(Config.Url.url_MyAccount);
		logger.log(Status.INFO, "Click on Credits & Coupons");
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		getScreentShotForExtentReport("MyAccountPage");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");		
	}

}

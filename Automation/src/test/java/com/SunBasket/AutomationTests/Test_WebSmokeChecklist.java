package com.SunBasket.AutomationTests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.UUID;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.SunBasket.Pages.SunBasketBuildYourOrderPage;
import com.SunBasket.Pages.SunBasketConfirmYourMealsPage;
import com.SunBasket.Pages.SunBasketJoinConfirmationPage;
import com.SunBasket.Pages.SunBasketMyAccountPage;
import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Pages.SunBasketCompleteYourOrderPage;
import com.SunBasket.Pages.SunBasketPromoPage;
import com.SunBasket.Pages.SunBasketSignInPage;
import com.SunBasket.Utility.BaseTest;
import com.SunBasket.Utility.DriverScript;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Test_WebSmokeChecklist extends BaseTest{
	
	@Test(description = "Verifies Join Flow_1!")
	public void C1_SunBasket_JoinFlow_PromoCode_001(){

		// Step 1
//		driver.navigate().to(Config.Url.SingleWeekPromo_url);
		logger.log(Status.INFO, "Navigate to SingleWeekPromoURL");
		driver.navigate().to("https://develop.sunbasket-staging.com/?offer=QA-TEST35OFF");
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		SBUtil.waitForUrlToBe(Config.Url.url_SingleWeekPromo);
		logger.log(Status.INFO, "Verify label_PromoOffer");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		logger.log(Status.INFO, "Verify button_RedeemPromoOffer");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");

		// Step 2
		logger.log(Status.INFO, "Click on GetStarted");
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
//		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		logger.log(Status.PASS, "Verified label_PromoOffer");
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		logger.log(Status.PASS, "SignUp Success");
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
//		sunBasketBuildYourOrderPage.waitForPageToLoad();
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		logger.log(Status.PASS, "Verified label_PromoOffer");
		
		// Step 4
		sunBasketBuildYourOrderPage.action_BuildYourOrder();
		logger.log(Status.PASS, "BuildYourOrder Success");
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
//		sunBasketConfirmYourMealsPage.waitForPageToLoad();
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "Order today and get $35 off your first delivery");
		
		// Step 5
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
//		sunBasketCompleteYourOrderPage.waitForPageToLoad();
		
		// Step 6
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		logger.log(Status.PASS, "Clicked on ApplyFreeShipping Popup");
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");	
	}
	
	@Test(description = "Verifies Join Flow_2!")
	public void C6_SunBasket_JoinFlow_PromoCode_002(){
		
		// Step 1
//		driver.navigate().to(Config.Url.SingleWeekPromo_url);
		driver.navigate().to("https://develop.sunbasket-staging.com/?offer=QA-TEST35OFF");
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 4
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 5
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "Order today and get $35 off your first delivery");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		System.out.println("Email : " + email);
		
		// Step 6
		sunBasketHomePage.action_SignOut();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_SignInPage);
		sunBasketSignInPage.action_signIn(email);
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 7
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 8
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();

		// Step 9
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		
		// Step 10
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		
		// Step 11
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");	
	}
	
	@Test(description = "Verifies Join Flow_3!")
	public void C7_SunBasket_JoinFlow_PromoCode_003(){
		
		// Step 1
		driver.navigate().to(Config.Url.url_MultiWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		sunBasketBuildYourOrderPage.action_VerifyText(sunBasketBuildYourOrderPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 4
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");
		
		// Step 5
		sunBasketConfirmYourMealsPage.button_Continue.click();		
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		
		// Step 6
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");		
	}
	
	@Test(description = "Verifies Join Flow_4!")
	public void C8_SunBasket_JoinFlow_PromoCode_004(){
		// Step 1
		driver.navigate().to(Config.Url.url_MultiWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);
		
		// Step 2
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		
		// Step 3
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 4
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 5
		sunBasketConfirmYourMealsPage.img_SunBasket.click();			
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.label_PromoOffer, "GET $40 OFF - $30 off your first order, $10 off your second order");		
		sunBasketHomePage.action_VerifyText(sunBasketHomePage.button_PromoRedeemOffer, "Redeem Offer");
		
		// Step 6
		String username = sunBasketHomePage.dropdown_Menu.getText();
		String email = username + "@test.com";
		System.out.println("Email : " + email);
		sunBasketHomePage.action_SignOut();
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_SignInPage);
		sunBasketSignInPage.action_signIn(email);
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage2 = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);
		
		// Step 7
		sunBasketBuildYourOrderPage2.button_Continue.click();
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage2 = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		
		// Step 8
		sunBasketConfirmYourMealsPage2.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-MW403010");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$30.00");	
		
		// Step 9
		String parentWindowHandler = driver.getWindowHandle();
		sunBasketCompleteYourOrderPage.action_ClickOnButton(sunBasketCompleteYourOrderPage.button_CheckoutWithPaypal);
		sunBasketCompleteYourOrderPage.action_checkOutWithPayPal(parentWindowHandler);
		sunBasketCompleteYourOrderPage.button_SubmitOrder.click();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		
		// Step 10
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");	
	}
	
	@Test(description = "Verifies Join Flow_5!")
	public void C9_SunBasket_JoinFlow_PromoCode_005(){
		
		 // Step 1		
		driver.navigate().to(Config.Url.url_SingleWeekPromo);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_PromoPage);	
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);	
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
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
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		
		// Step 3
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$10.00 off");
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(2, 0, "$30.00 off");		
	}
	
	@Test(description = "Verifies Join Flow_6!")
	public void C10_SunBasket_JoinFlow_PromoCode_006(){
		
		 // Step 1		
		driver.navigate().to(Config.Url.url_Home);
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_HomePage);
		sunBasketHomePage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_GetStartedPage);
		sunBasketGetStartedPage.action_JoinSetup();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_BuildYourOrderPage);	
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_ConfirmYourMealsPage);
		sunBasketConfirmYourMealsPage.button_Continue.click();
		SunBasketCompleteYourOrderPage sunBasketCompleteYourOrderPage = new SunBasketCompleteYourOrderPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		sunBasketCompleteYourOrderPage.button_ApplyFreeShipping.click();
		
		// Step 2
		sunBasketCompleteYourOrderPage.textfield_PromoCode.clear();
		sunBasketCompleteYourOrderPage.textfield_PromoCode.sendKeys("QA-TEST35OFF");
		sunBasketCompleteYourOrderPage.action_ClickOnButton(sunBasketCompleteYourOrderPage.button_ApplyPromoCode);
		sunBasketCompleteYourOrderPage.button_ApplyPromoCode.click();
		sunBasketCompleteYourOrderPage.action_verifyAttribute(sunBasketCompleteYourOrderPage.textfield_PromoCode, "value", "QA-TEST35OFF");
		SBUtil.waitForTextToBePresentInElement(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");
		sunBasketCompleteYourOrderPage.action_VerifyText(sunBasketCompleteYourOrderPage.value_PromoDiscount, "–$35.00");	
		
		// Step 3
		sunBasketCompleteYourOrderPage.action_FillCreditCardAndplaceOrder();
		SunBasketJoinConfirmationPage sunBasketJoinConfirmationPage = new SunBasketJoinConfirmationPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_JoinConfirmationPage);
		
		// Step 4
		sunBasketJoinConfirmationPage.dropdown_Menu.click();
		sunBasketJoinConfirmationPage.link_MyAccount.click();
		SunBasketMyAccountPage sunBasketMyAccountPage = new SunBasketMyAccountPage(driver);
		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_MyAccountPage);
		sunBasketMyAccountPage.link_CreditsAndCoupons.click();
		sunBasketMyAccountPage.action_WebTable_CouponsAvailable(1, 0, "$35.00 off");		
	}
	
}

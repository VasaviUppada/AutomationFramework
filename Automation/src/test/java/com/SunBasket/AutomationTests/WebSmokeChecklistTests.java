package com.SunBasket.AutomationTests;

import org.testng.annotations.Test;

import com.SunBasket.Config.Config;
import com.SunBasket.Pages.SunBasketBuildYourOrderPage;
import com.SunBasket.Pages.SunBasketConfirmYourMealsPage;
import com.SunBasket.Pages.SunBasketGetStartedPage;
import com.SunBasket.Pages.SunBasketCompleteYourOrder;
import com.SunBasket.Pages.SunBasketPromoPage;
import com.SunBasket.Utility.BaseTest;

public class WebSmokeChecklistTests extends BaseTest{
	
	@Test
	public void C1_SunBasket_JoinFlow_PromoCode_001(){

		driver.navigate().to(Config.Url.promo35_url);
		SunBasketPromoPage sunBasketPromoPage = new SunBasketPromoPage();
		sunBasketPromoPage.waitForPageToLoad();
		sunBasketPromoPage.action_VerifyText(sunBasketPromoPage.label_OrderTodayAndGet35, "Order today and get $35 off your first delivery");
		
		sunBasketPromoPage.button_GetStarted.click();
		SunBasketGetStartedPage sunBasketGetStartedPage = new SunBasketGetStartedPage();
		sunBasketGetStartedPage.waitForPageToLoad();
		sunBasketGetStartedPage.action_VerifyText(sunBasketGetStartedPage.label_OrderTodayAndGet35, "Order today and get $35 off your first delivery");
		sunBasketGetStartedPage.action_GetStarted();
		SunBasketBuildYourOrderPage sunBasketBuildYourOrderPage = new SunBasketBuildYourOrderPage();
		sunBasketBuildYourOrderPage.waitForPageToLoad();
		
		sunBasketBuildYourOrderPage.textfield_Addressline1.sendKeys("Ownes Dr");
		sunBasketBuildYourOrderPage.textfield_AddressLine2.sendKeys("345");
		sunBasketBuildYourOrderPage.textfield_PhoneNumber.sendKeys("9259259259");
		sunBasketBuildYourOrderPage.textfield_DeliveryInstructions.sendKeys("Door Delivery");
		sunBasketBuildYourOrderPage.button_Continue.click();
		
		SunBasketConfirmYourMealsPage sunBasketConfirmYourMealsPage = new SunBasketConfirmYourMealsPage();
		sunBasketConfirmYourMealsPage.waitForPageToLoad();
		sunBasketConfirmYourMealsPage.action_VerifyText(sunBasketConfirmYourMealsPage.label_OrderTodayAndGet35, "Order today and get $35 off your first delivery");
		sunBasketConfirmYourMealsPage.button_Continue.click();
		
		SunBasketCompleteYourOrder sunBasketCompleteYourOrder = new SunBasketCompleteYourOrder();
		sunBasketCompleteYourOrder.waitForPageToLoad();
		sunBasketCompleteYourOrder.button_ApplyFreeShipping.click();

		sunBasketCompleteYourOrder.action_verifyAttribute(sunBasketCompleteYourOrder.textfield_PromoCode, "value", "QA-TEST35OFF");
		sunBasketCompleteYourOrder.action_VerifyText(sunBasketCompleteYourOrder.value_PromoDiscount, "–$35.00");
		
	}
}

package com.SunBasket.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketCompleteYourOrder extends BasePage{

	public SunBasketCompleteYourOrder() {
		super(driver);
	}
	
	/*** Web Elements ***/
	
	@FindBy(xpath = "//h2[text()='Free Shipping Unlocked']")
	public WebElement title_FreeShippingUnlocked;
	
	@FindBy(xpath = "//*[@id='apply_promo']")
	public WebElement button_ApplyFreeShipping;
	
	@FindBy(xpath = "//button[@class='close']")
	public WebElement crossmark_Close;
	
	@FindBy(xpath = "//h2[text()='Complete Your Order']")
	public WebElement title_CompleteYourOrder;
	
	@FindBy(xpath = "//*[@id='nameOnCardInput']")
	public WebElement textField_CreditCardDetails_Name;
	
	@FindBy(xpath = "//*[@id='cardNumberInput']")
	public WebElement textField_CreditCardNumber;
	
	@FindBy(xpath = "//select[@id='expirationMonthSelect']")
	public WebElement dropdown_Month;
	
	@FindBy(xpath = "//select[@id='expirationYearSelect']")
	public WebElement dropdown_Year;
	
	@FindBy(xpath = "//*[@id='securityCode']")
	public WebElement textField_CVC;
	
	@FindBy(xpath = "//*[@id='sameAddress']")
	public WebElement checkbox_SameAsDeliveryAddress;	
	
	@FindBy(xpath = "//*[@id='promoCode']")
	public WebElement textfield_PromoCode;
	
	@FindBy(xpath = "//*[@id='promo-eval']")
	public WebElement button_ApplyPromoCode;
	
	@FindBy(xpath = "//*[@id='submitOrderButton']")
	public WebElement button_SubmitOrder;
	
	@FindBy(xpath = "//h3[text()='Order Summary']")
	public WebElement title_OrderSummary;
	
	@FindBy(xpath = "//*[@id='delivery']")
	public WebElement label_FreeShipping;
	
	@FindBy(xpath = "//*[@class='breakdown-entry brand-yellow']")
	public WebElement value_PromoDiscount;
	
	/*** Action Methods ***/
	
	public void action_VerifyText(WebElement element, String expected){
		SBUtil.verifyText(element, expected);
	}

	public void action_verifyAttribute(WebElement element, String attribute, String expected){
			SBUtil.verifyAttributeValue(element, attribute, expected);
	}
	
}

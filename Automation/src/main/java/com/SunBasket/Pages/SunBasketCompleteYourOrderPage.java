package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SunBasket.Config.Config;
import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;

public class SunBasketCompleteYourOrderPage extends BasePage{

	public SunBasketCompleteYourOrderPage() {
		super(driver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//*[@id='home-hero']/div/img")
	public WebElement img_SunBasket;
	
	@FindBy(xpath = "//h2[text()='Free Shipping Unlocked']")
	public WebElement title_FreeShippingUnlocked;
	
	@FindBy(xpath = "//*[@id='apply_promo']")
	public WebElement button_ApplyFreeShipping;
	
	@FindBy(xpath = "//button[@class='close']")
	public WebElement crossmark_Close;
	
	@FindBy(xpath = "//h2[text()='Complete Your Order']")
	public WebElement title_CompleteYourOrder;
	
	@FindBy(xpath = "//form//a[@id='paypal-button']")
	public WebElement button_CheckoutWithPaypal;
	
	@FindBy(xpath = "//p[text()='PayPal Account']")
	public WebElement text_PayPalAccount;
	
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
	
	@FindBy(xpath = "//button[@id='promo-eval']")
	public WebElement button_ApplyPromoCode;
	
	@FindBy(xpath = "//form//button[@id='submitOrderButton'][text()='Submit order']")
	public WebElement button_SubmitOrder;
	
	@FindBy(xpath = "//h3[text()='Order Summary']")
	public WebElement title_OrderSummary;
	
	@FindBy(xpath = "//*[@id='delivery']")
	public WebElement label_FreeShipping;
	
	@FindBy(xpath = "//*[@class='breakdown-entry brand-yellow']")
	public WebElement value_PromoDiscount;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;

	
	/*** Action Methods ***/
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}

	public void action_VerifyText(WebElement element, String expected){
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.verifyText(element, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}

	public void action_verifyAttribute(WebElement element, String attribute, String expected){
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.verifyAttributeValue(element, attribute, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}
		
	public void action_FillCreditCardAndplaceOrder(){
		logger.log(Status.INFO, "Enter CreditCardNumber");
		textField_CreditCardNumber.sendKeys("4242424242424242");
		logger.log(Status.INFO, "Enter Month");
		SBUtil.selectOptionByVisibleText(dropdown_Month, "06 - Jun");
		logger.log(Status.INFO, "Enter Year");
		SBUtil.selectOptionByIndex(dropdown_Year, 4);
		logger.log(Status.INFO, "Enter CVV");
		textField_CVC.sendKeys("925");
		action_ClickOnSubmitButton(button_SubmitOrder);
	}
	
	public void action_checkOutWithPayPal(String parentWindowHandler){
		SBUtil.getWindowToHandle();
		SunBasketPayPalPurchasePage sunBasketPayPalPurchasePage = new SunBasketPayPalPurchasePage();
		SBUtil.waitForUrlContains(Config.Url.url_Paypal);
//		sunBasketPayPalPurchasePage.waitForPageToLoad();
		logger.log(Status.INFO, "Click on Proceed with Sandbox button");
		sunBasketPayPalPurchasePage.button_ProceedWithSandboxPurchase.click();
		driver.switchTo().window(parentWindowHandler);
//		SBUtil.waitForPageTitle(Config.PageTitle.pageTitle_CompleteYourOrderPage);
		SBUtil.waitForUrlToBe(Config.Url.url_JoinPayment);
		logger.log(Status.INFO, "Enter Promocode : QA-MW403010");
		textfield_PromoCode.clear();
		textfield_PromoCode.sendKeys("QA-MW403010");
//		SBUtil.waitForElementToBeClickable(button_SubmitOrder);
		action_ClickOnSubmitButton(button_SubmitOrder);
	}

	public void action_ClickOnSubmitButton(WebElement element){
			try {
				SBUtil.clickwithJavaScriptExecutor(element);
			} catch (Exception e) {
				logger.log(Status.FAIL, e.getMessage());
				e.getMessage();
			}
	}

	public static void action_ClickOnSubmitButton2(WebElement element) {
	     Actions builder = new Actions(driver);
	     builder.moveToElement(element).click(element);
//	     builder.click(element);
//	     builder.build();
	     builder.perform();
	}


}

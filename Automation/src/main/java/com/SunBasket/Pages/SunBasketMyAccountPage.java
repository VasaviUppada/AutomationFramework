package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;

public class SunBasketMyAccountPage extends BasePage{

	public SunBasketMyAccountPage(WebDriver driver) {
		super(driver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//*[@id='myaccount-user-info']//h4")
	public WebElement text_UserName;
	
	@FindBy(xpath = "//p[@class='account-value margin-bottom-0']")
	public WebElement text_Email;
	
	@FindBy(xpath = "//a[@id='settings-section-hash']")
	public WebElement link_Settings;

	@FindBy(xpath = "//a[@id='billing-history-section-hash']")
	public WebElement link_BillingHistory;
	
	@FindBy(xpath = "//a[@id='credits-section-hash']")
	public WebElement link_CreditsAndCoupons;
	
	@FindBy(xpath = "//a[@id='alerts-section-hash']")
	public WebElement link_Notifications;
	
	@FindBy(xpath = "//*[@id='credits-section']//h3")
	public WebElement title_CreditsAndCoupons;
		
	@FindBy(xpath = "//*[@id='credits-section']//h4[text()='Coupons available']")
	public WebElement title_CouponsAvailable;
	

	/*** Action Methods ***/
	
	public void action_WebTable_CouponsAvailable(int rowNo, int colNo, String expected){
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.VerifyText_webTable(rowNo, colNo, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}


}

package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;

public class SunBasketHomePage extends BasePage{
	
	public SunBasketHomePage() {
		super(driver);	
	}

	/*** Web Elements ***/
	@FindBy(xpath = "//div[@class='navbar-header ']/div[3]/a")
	public WebElement title_SunBasket;
	
	@FindBy(xpath = "//div[@class ='navbar-header ']/div[1]//li[1]/a")
	public WebElement tab_Menu;
	
	@FindBy(xpath = "//div[@class ='navbar-header ']/div[1]//li[2]/a")
	public WebElement tab_Values;
	
	@FindBy(xpath = "//div[@class ='navbar-header ']/div[1]//li[3]/a")
	public WebElement tab_Farms;
	
	@FindBy(xpath = "//div[@class ='navbar-header ']/div[1]//li[4]/a")
	public WebElement tab_Pricing;
	
	@FindBy(xpath = "//*[@id='nav-logged-out']//li[@class='hidden-xs']/a[@class='logout-link nav-btn']")
	public WebElement link_SignIn;
	
	@FindBy(xpath = "//*[@class='nav navbar-nav navbar-right hidden-xs']//a[@class='btn-join btn']")
	public WebElement button_GetStarted;
	
	@FindBy(xpath = "//div[@id='page-content']/div/a[1]")
	public WebElement label_PromoOffer;
	
	@FindBy(xpath = "//*[@id='page-content']/div/a[2]")
	public WebElement button_PromoRedeemOffer;
	
	@FindBy(xpath = "//*[@id='settings-dropdown']")
	public WebElement dropdown_Menu;
	
	@FindBy(xpath = "//li[@class='dropdown open']//*[@class='logout-link']")
	public WebElement link_SignOut;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	// To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	// To Verify Text
	public void action_VerifyText(WebElement element, String expected){
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.verifyText(element, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}
	
	public void action_SignOut(){
//		Select dropdown = new Select(dropdownelement);
		logger.log(Status.INFO, "Click on Menu");
		dropdown_Menu.click();
		logger.log(Status.INFO, "Click on Signout");
		getScreentShotForExtentReport("Signout");
		link_SignOut.click();
	}
	
}

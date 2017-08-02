package com.SunBasket.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

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
	
	@FindBy(xpath = "gged-out']//li[@class='hidden-xs']/a[@class='btn-join btn btn-join-scrolled']")
	public WebElement button_GetStarted;
	
	@FindBy(tagName = "a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
}

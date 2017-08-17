package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketPromoPage extends BasePage{

	public SunBasketPromoPage() {
		super(driver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//div[@id='page-content']/div/a[1]")
	public WebElement label_PromoOffer;
	
	@FindBy(xpath = "//*[@id='page-content']/div/a[2]")
	public WebElement button_PromoRedeemOffer;
	
	@FindBy(xpath = "//*[@class='nav navbar-nav navbar-right hidden-xs']//a[@class='btn-join btn']")
	public WebElement button_GetStarted;
	
	@FindBy(xpath = "//*[@id='nav-logged-out']//li[@class='hidden-xs']/a[@class='logout-link nav-btn']")
	public WebElement link_SignIn;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	
	/*** Action Methods 
	 * @throws IOException ***/
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	public void action_VerifyText(WebElement element, String expected){
		assertTrue(SBUtil.verifyText(element, expected));
	}
	
	

}

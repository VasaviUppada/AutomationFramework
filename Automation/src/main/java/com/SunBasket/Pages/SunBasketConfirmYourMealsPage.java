package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketConfirmYourMealsPage extends BasePage{

	public SunBasketConfirmYourMealsPage() {
		super(driver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//*[@id='home-hero']/div/img")
	public WebElement img_SunBasket;
	
	@FindBy(xpath = "//div[@id='page-content']/div/a[1]")
	public WebElement label_PromoOffer;
	
	@FindBy(xpath = "//h2[text()='Confirm Your Meals']")
	public WebElement title_ConfirmYourMeals;
	
	@FindBy(xpath = "//*[@id='saveMenuButton']")
	public WebElement button_Continue;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;


	/*** Action Methods ***/
	
	// To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	public void action_VerifyText(WebElement element, String expected){
		assertTrue(SBUtil.verifyText(element, expected));
	}


}

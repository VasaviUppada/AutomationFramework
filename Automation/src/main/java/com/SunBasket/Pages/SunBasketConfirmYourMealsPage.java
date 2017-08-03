package com.SunBasket.Pages;

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
	
	@FindBy(xpath = "//*[@id='page-content']/div[1]/a[1]")
	public WebElement label_OrderTodayAndGet35;
	
	@FindBy(xpath = "//h2[text()='Confirm Your Meals']")
	public WebElement title_ConfirmYourMeals;
	
	@FindBy(xpath = "//*[@id='saveMenuButton']")
	public WebElement button_Continue;
	
	/*** Action Methods ***/
	
	public void action_VerifyText(WebElement element, String expected){
		SBUtil.verifyText(element, expected);
	}

}

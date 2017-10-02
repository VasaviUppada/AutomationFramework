package com.SunBasket.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;

public class SunBasketPayPalPurchasePage extends BasePage{

	public SunBasketPayPalPurchasePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='return_url']")
	public WebElement button_ProceedWithSandboxPurchase;

	@FindBy(xpath = "//*[@id='cancel_url']")
	public WebElement button_CancelSandboxPurchase;
}

package com.SunBasket.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Config.Config;
import com.SunBasket.Utility.BasePage;

public class SunBasketJoinConfirmationPage extends BasePage{

	public SunBasketJoinConfirmationPage() {
		super(threadDriver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//*[@id='settings-dropdown']")
	public WebElement dropdown_Menu;
	
	@FindBy(xpath = "//*[@class='dropdown-menu']//a[@href='/my-account']")
	public WebElement link_MyAccount;
	
}

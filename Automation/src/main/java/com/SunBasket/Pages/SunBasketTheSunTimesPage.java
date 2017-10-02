package com.SunBasket.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketTheSunTimesPage extends BasePage{

	public SunBasketTheSunTimesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	//*** Action Methods ***//
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);

	}


}

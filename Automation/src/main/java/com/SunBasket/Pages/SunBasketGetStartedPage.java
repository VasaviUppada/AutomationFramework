package com.SunBasket.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketGetStartedPage extends BasePage{

	public SunBasketGetStartedPage(){
		super(driver);
	}
	
	/*** Web Elements ***/

	@FindBy(xpath = "//div[@id='sign-up-form']//h2[text()='Get Started']")
	public WebElement header_GetStarted;
	
	@FindBy(xpath = "//*[@id='sign-up-form']//p[@class='subhead']")
	public WebElement text_SeasonalIngredientsAndDeliciousRecipesDeliveredWeekly;
	
	@FindBy(id = "#firstName")
	public WebElement textfield_FirstName;
	
	@FindBy(id = "#lastName")
	public WebElement textfield_LastName;
	
	@FindBy(id = "#email")
	public WebElement textfield_EmailAddress;
	
	@FindBy(id = "#password")
	public WebElement textfield_ChooseAPassword;
	
	@FindBy(id = "#zip")
	public WebElement textfield_ZipCode;
	
	@FindBy(xpath = "//form[@id='userJoinForm']/fieldset/div[5]/div/div/button")
	public WebElement button_Continue;
	
	@FindBy(xpath = "//img[contains(@src,'/resources/img/icons/icon-lemons.svg')]")
	public WebElement image_ChooseFromDeliciousRecipesEachWeek;
	
	@FindBy(xpath = "//*[@id='join-about-info']//h4[text()='Choose from delicious recipes each week']")
	public WebElement text_ChooseFromDeliciousRecipesEachWeek;
	
	@FindBy(xpath = "//img[contains(@src,'/resources/img/icons/icon-beans.svg')]")
	public WebElement image_EnjoyTheCountrysBestSeasonalFood;
	
	@FindBy(xpath = "//*[@id='join-about-info']/div[2]/div/h4")
	public WebElement text_EnjoyTheCountrysBestSeasonalFood;
	
	@FindBy(xpath = "//img[contains(@src,'/resources/img/icons/icon-delivery-truck.svg)]")
	public WebElement image_gh;
	
	@FindBy(xpath = "//*[@id='join-about-info']//h4[text()='Convenient delivery to your door']")
	public WebElement text_ConvenientDeliveryToYourDoor;
	
	@FindBy(tagName = "a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	
}

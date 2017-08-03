package com.SunBasket.Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@FindBy(xpath = "//div[@id='page-content']//a[1]")
	public WebElement label_OrderTodayAndGet35;
	
	@FindBy(xpath = "//div[@id='sign-up-form']//h2[text()='Get Started']")
	public WebElement header_GetStarted;
	
	@FindBy(xpath = "//*[@id='sign-up-form']//p[@class='subhead']")
	public WebElement text_SeasonalIngredientsAndDeliciousRecipesDeliveredWeekly;
	
	@FindBy(xpath = "//*[@id='firstName']")
	public WebElement textfield_FirstName;
	
	@FindBy(xpath = "//*[@id='lastName']")
	public WebElement textfield_LastName;
	
	@FindBy(xpath = "//*[@id='email']")
	public WebElement textfield_EmailAddress;
	
	@FindBy(xpath = "//*[@id='password']")
	public WebElement textfield_ChooseAPassword;
	
	@FindBy(xpath = "//*[@id='zip']")
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
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	public void action_VerifyText(WebElement element, String expected){
		SBUtil.verifyText(element, expected);
	}
	
	public void action_GetStarted(){
		DateFormat dateFormat = new SimpleDateFormat("ddmmyyyyhms");
        Date date = new Date();
		textfield_FirstName.sendKeys("TestFirst");
		textfield_LastName.sendKeys("TestLast");
		textfield_EmailAddress.sendKeys("test" + dateFormat.format(date) + "@test.com");
		textfield_ChooseAPassword.sendKeys("ReplacePassword123");
		textfield_ZipCode.sendKeys("94588");
		button_Continue.click();
	}
	
	
	
	
}

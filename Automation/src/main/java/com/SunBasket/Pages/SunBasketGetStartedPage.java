package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;

public class SunBasketGetStartedPage extends BasePage{

	public SunBasketGetStartedPage(){
		super(threadDriver);
	}
	
	
	/*** Web Elements ***/

	@FindBy(xpath = "//div[@id='page-content']/div/a[1]")
	public WebElement label_PromoOffer;
	
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
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.verifyText(element, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}
	
	public void action_JoinSetup(){
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy_hms");
        Date date = new Date();
        String firstName = "test" + dateFormat.format(date);
		logger.log(Status.INFO, "Enter FirstName");
		textfield_FirstName.sendKeys(firstName);
		logger.log(Status.INFO, "Enter LastName");
		textfield_LastName.sendKeys("Test");
		String email = firstName + "@test.com";
		String pswd = "ReplacePassword123";
		logger.log(Status.INFO, "Enter Email - " + email);
		textfield_EmailAddress.sendKeys(email);
		logger.log(Status.INFO, "Enter Password");
		textfield_ChooseAPassword.sendKeys(pswd);
		logger.log(Status.INFO, "Enter ZIPcode");
		textfield_ZipCode.sendKeys("94588");
		getScreentShotForExtentReport("JoinPage");
		action_ClickOnContinueButton(button_Continue);
	}
	
	public void action_ClickOnContinueButton(WebElement element){
		try {
			SBUtil.clickwithJavaScriptExecutor(element);
		} catch (Exception e) {
			logger.log(Status.FAIL, (Throwable)element);
			e.printStackTrace();
		}
}
	
	
	
}

package com.SunBasket.Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

// this page loads when customer clicks on 
public class SunBasketBuildYourOrderPage extends BasePage {

	public SunBasketBuildYourOrderPage() {
		super(threadDriver);
	}

	/*** Web Elements ***/
	
	@FindBy(xpath = "//*[@id='home-hero']/div/img")
	public WebElement img_SunBasket;
	
	@FindBy(xpath = "//div[@id='page-content']/div/a[1]")
	public WebElement label_PromoOffer;
	
	@FindBy(xpath = "//h2[text()='Build Your Order']")
	public WebElement title_BuildYourOrder;
	
	@FindBy(xpath = "//select[@id='numberOfPeopleSelect']")
	public WebElement dropdown_HowmanyAreYouCookingFor;
	
	@FindBy(xpath = "//select[@id='numberOfMealsSelect']")
	public WebElement dropdown_DefaultRecipes;
	
	@FindBy(xpath = "//select[@id='mealPlanSelect'][@title='Meal plan']") 
	public WebElement dropdown_SelectAMealPlan;
	
	@FindBy(xpath = "//input[@id='autocomplete']")
	public WebElement textfield_Addressline1;
	
	@FindBy(xpath = "//*[@name='address2']")
	public WebElement textfield_AddressLine2;
	
	@FindBy(xpath = "//*[@id='locality']")
	public WebElement textfield_City;
	
	@FindBy(xpath = "//select[@id='administrative_area_level_1']")
	public WebElement dropdown_State;
	
	@FindBy(xpath = "//*[@id='postal_code']")
	public WebElement textfield_ZipCode;
	
	@FindBy(xpath = "//*[@id='phone']")
	public WebElement textfield_PhoneNumber;
	
	@FindBy(xpath = "//*[@id='setup-form']/fieldset/div[4]/div[10]/input")
	public WebElement textfield_DeliveryInstructions;
	
	@FindBy(xpath = "//form/fieldset//button[@id='submit-button']")
	public WebElement button_Continue;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	

	/*** Action Methods ***/

	// To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	public void action_VerifyText(WebElement element, String expected){
		logger.log(Status.INFO, "Verify Text - " + expected);
		assertTrue(SBUtil.verifyText(element, expected));
		logger.log(Status.PASS, expected + " - Matches");
	}

	public void action_SendKeys(WebElement textBox, String textToPass){
		try {
			SBUtil.sendKeyswithJavaScriptExecutor(textBox, textToPass);
		} catch (Exception e) {
//			System.out.println("Unable to click on : " + textBox);
			logger.log(Status.FAIL, (Throwable)textBox);
			e.printStackTrace();
		}
	}
	
	public void action_ClickOnContinueButton(WebElement element){
		try {
			SBUtil.clickwithJavaScriptExecutor(element);
		} catch (Exception e) {
			logger.log(Status.FAIL, (Throwable)element);
			e.printStackTrace();
		}
	}
	
	public void action_BuildYourOrder(){
		logger.log(Status.INFO, "Enter Addressline1");
		textfield_Addressline1.sendKeys("Ownes Dr");
		logger.log(Status.INFO, "Enter Addressline2");
		textfield_AddressLine2.sendKeys("925");		
		action_SendKeys(textfield_PhoneNumber, "9259259259");
		logger.log(Status.INFO, "Enter DeliveryInstructions");
		textfield_DeliveryInstructions.sendKeys("Door Delivery");
		getScreentShotForExtentReport("JoinSetUpPage");
//		action_ClickOnContinueButton(button_Continue);
		logger.log(Status.INFO, "Click on Continue");
		button_Continue.sendKeys(Keys.ENTER);
	}

	
}

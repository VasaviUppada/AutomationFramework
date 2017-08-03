package com.SunBasket.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

// this page loads when customer clicks on 
public class SunBasketBuildYourOrderPage extends BasePage {

	public SunBasketBuildYourOrderPage() {
		super(driver);
	}
	
	/*** Web Elements ***/
	
	@FindBy(xpath = "//div[@class='offer-message semibold center']/a[1]")
	public WebElement label_OrderTodayAndGet35;
	
	@FindBy(xpath = "//h2[text()='Build Your Order']")
	public WebElement title_BuildYourOrder;
	
	@FindBy(xpath = "//select[@id='numberOfPeopleSelect']")
	public WebElement dropdown_HowmanyAreYouCookingFor;
	
	@FindBy(xpath = "//select[@id='numberOfMealsSelect']")
	public WebElement dropdown_DefaultRecipes;
	
	@FindBy(xpath = "//select[@id='mealPlanSelect']")
	public WebElement dropdown_SelectAMealPlan;
	
	@FindBy(xpath = "//*[@id='autocomplete']")
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
	
	@FindBy(xpath = "//*[@class='submit-container float-right']")
	public WebElement button_Continue;
	
	/*** Action Methods ***/
	
	public void action_VerifyText(WebElement element, String expected){
		SBUtil.verifyText(element, expected);
	}


}

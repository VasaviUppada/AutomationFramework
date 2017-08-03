package com.SunBasket.Pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketMyMenuPage extends BasePage{

	public SunBasketMyMenuPage(){
		super(driver);
	}
	
	@FindBy(className = "logo-container")
	public WebElement logo_SunBasket;
	
	@FindBy(id = "mymenu")
	public WebElement tab_Menu;
	
	@FindBy(id = "schedule")	
	public WebElement tab_Schedule;
	
	@FindBy(id = "zine")
	public WebElement tab_TheSunTimes;
	
	@FindBy(id = "refer-a-friend")
	public WebElement tab_Earn25;
	
	@FindBy(xpath = "//*[@id='my-menu-header']//h4[@class='margin-bottom-0']")
	public WebElement header_MyMenuHeader;
	
	@FindBy(className = "menu-instructions")
	public WebElement text_ChangeYourMenuBeforeThursday;
	
	@FindBy(xpath = "//img[contains(@src,'/resources/img/landing/gart/gart-roadsign.svg')]")
	public WebElement image_RoadTrip;
	
	@FindBy(xpath = "//p[contains(text(), 'taking you on a Great American Road Trip—Starting June 29th')]")
	public WebElement text_WeTakingYouOnAGreatAmericanRoadTrip;
	
	@FindBy(xpath = "//a[@href='/great-american-road-trip']")
	public WebElement link_LearnMore;
	
	@FindBy(id = "gart-banner-close")
	public WebElement icon_CrossMark_WeTakingYouOnAGreatAmericanRoadTrip;
	
	@FindBy(className = "unskip-this-week btn btn-pink btn-sm unskip-btn")
	public WebElement button_UnskipDeliver;
	
	@FindBy(xpath = "//*[@id='change-mymenu-btn'][@href='javascript:void(0)']")
	public WebElement button_ChangeMyRecipes;
	
	@FindBy(xpath = "//span[text()='Select ']")
	public WebElement text_Select;

	@FindBy(xpath = "//*[@class='fill-checks-container']")
	public WebElement checkContainer;	
	
	@FindBy(xpath = "//div[@class='icon icon-check-mark']")
	public List<WebElement> checkbox_AllReciepes;
	
	@FindBy(xpath = "//div[@class='check-box checked']/div[@class='icon icon-check-mark']")
	public List<WebElement> checkbox_AllSelected;
	
	@FindBy(xpath = "//div[@class='check-box unchecked']/div[@class='icon icon-check-mark']")
	public List<WebElement> checkbox_AllUnselected;
	
	@FindBy(id = "cancelEditMode")
	public WebElement link_Cancel;
	
	@FindBy(id = "saveMenuButton")
	public WebElement button_Save;
	
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	@FindBy(xpath = "//*[@id='survey']")
	public WebElement popup_Survey;
	
	@FindBy(xpath = "//a[@id='down-survey']/span[@class='icon icon-angle-down']")
	public WebElement popup_SurveyDownArrow;
	
	@FindBy(xpath = "//*[@id='close-survey']/span[@class='icon icon-close-round']")
	public WebElement popup_SurveyCrossmark;
	
	@FindBy(xpath = "//*[@class='stylize']")
	public WebElement dropdown_SelectRecipes;
	
	@FindBy(xpath = "//div[@class='stylize']//li/a")
	public List<WebElement> dropdownList_SelectRecipes2;
	
	@FindBy(xpath = "//div[@id='edit-mode-controls-container']//li/a/p[1]")
	public List<WebElement> dropdownList_SelectRecipes;
	
	@FindBy(xpath = "//*[@class='margin-bottom-0 adelle text-xl']/a[1]")
	public WebElement button_SwitchToFamilyOrClassicMenu;
	
	@FindBy(xpath = "//*[@id='myModalLabel']")
	public WebElement Title_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='editMealPlanModal']//button[@class='close']")
	public WebElement button_Crossmark_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='number-of-people-container']/p[@class='semibold text-sm form-label']")
	public WebElement label_SelectMenuAndNoOfPeople_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='number-of-meals-container']/p[@class='semibold text-sm form-label']")
	public WebElement label_DefaultRecipesPerWeek_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='meal-plan-container']/p[@class='semibold text-sm form-label']")
	public WebElement label_SelectAMealPlan_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='meal-plan-form']//p[@class='text-sm']")
	public WebElement text_ShippingPerOrder_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='meal-plan-form']//p[@class='text-sm family-plan-copy']")
	public WebElement text_For2RecipeOrders_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='meal-plan-form']//a[@class='cancel-link']")
	public WebElement button_Cancel_EditMealPlan;
	
	@FindBy(xpath = "//*[@id='meal-plan-submit']")
	public WebElement button_Save_EditMealPlan;
	
	
	//*** Action Methods ***//
	
	public void action_verifyHeader(){
		SBUtil.verifyText(header_MyMenuHeader, "Vasavi");
	}
	
	public void action_isTextPresent(String expected){
		Assert.assertTrue(SBUtil.isTextPresent(expected));
	}
	
	public void action_verifyText(WebElement webElement, String expected){
		Assert.assertTrue(SBUtil.verifyText(webElement, expected));
	}

	// To minimize/close Popup
	public void action_MinimizePopup(){
		popup_SurveyDownArrow.click();
		popup_SurveyCrossmark.click();
	}

	// To Choose Recipes from Page
	public void action_chooseRecipes(List<WebElement> checkboxReciepe, int noOfReciepes){
		int chkboxCount = 0;
		for(WebElement chk : checkboxReciepe){
			if(chkboxCount < noOfReciepes){
				SBUtil.checkboxSelect(chk);
				chkboxCount++;
				SBUtil.waitForPageToLoad();
			}
		}
		System.out.println("No.of Recipes Selected : " + checkbox_AllSelected.size());
		button_Save.click();	
	}
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}


/*	
	// Unable to get all options from BootStrap Dropdown.
	public void action_DropDown(List<WebElement> dropdown){
		dropdown_SelectRecipes.click();
		System.out.println("Dropdown Text : " + dropdown_SelectRecipes.getText());
		System.out.println("Dropdown Size : " + dropdown.size());
		for(WebElement dd : dropdown){
			String dd_value = dd.getText();
			System.out.println("Dropdown List _ Text : " + dd_value);

//			if(dd_value.contentEquals("2 recipes")){
//				dd.click();
//				break;
//			}
			dd.click();
//			System.out.println("Dropdown Clicked : " + dropdown_SelectRecipes.getText());
		}


	}
	
	*/
	
	@FindBy(xpath = "//*[@id='changeNumRecipes']")
	public WebElement dd_SelectRecipes;
	
	@FindBy(xpath = "//*[@id='changeNumFamilyRecipes']/option")
	public List<WebElement> ddList_SelectRecipes;
	
	public void action_GetDropdownLis(WebElement element){
//		Select dropdown = new Select(element);
/*		for(WebElement list : ddList_SelectRecipes){
			String value = list.getText();
			System.out.println("Value : " + value);
		}*/

//		dropdown.selectByVisibleText("4 recipes");
		String[] selectReciepeList = {"2 recipes", "4 recipes"};
//		SBUtil.verifyDropdownList(dd_SelectRecipes, selectReciepeList);
		SBUtil.getDropdownList(ddList_SelectRecipes, dd_SelectRecipes);
		
	}
	
	public void action_dd(WebElement dd_SelectRecipes){
		Select dropdown = new Select(dd_SelectRecipes);
//		dropdown.selectByValue("value");
		dropdown.selectByIndex(1);
//		dropdown.selectByVisibleText("2 recipes");
		
	}
	
	public Select action_select(WebElement element) { 
		return new Select(element); 
	}


}

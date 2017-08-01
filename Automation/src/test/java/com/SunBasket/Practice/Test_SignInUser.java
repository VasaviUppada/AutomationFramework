package com.SunBasket.Practice;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.testng.annotations.Test;

import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Pages.SunBasketMyMenuPage;
import com.SunBasket.Pages.SunBasketSignInPage;
import com.SunBasket.Utility.BaseTest;
import com.SunBasket.Utility.SBUtil;

public class Test_SignInUser extends BaseTest{

	@Test
	public void Test_001() throws Exception{
		//Step 1
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		//Step 2
		sunBasketHomePage.link_SignIn.click();
		SunBasketSignInPage sunBasketSignInPage = new SunBasketSignInPage();
		sunBasketSignInPage.waitForPageToLoad();
		sunBasketSignInPage.action_signIn();
		
		SunBasketMyMenuPage sunBasketMyMenuPage = new SunBasketMyMenuPage();
//		SBUtil.verifyText(sunBasketMyMenuPage.header_MyMenuHeader, "Vasavi");
//		sunBasketMyMenuPage.action_verifyHeader();
//		sunBasketMyMenuPage.action_isTextPresent("We're taking you on a Great");  //-- Working fine
//		sunBasketMyMenuPage.action_verifyText(sunBasketMyMenuPage.header_MyMenuHeader, "Arriving Mon, Jul 31");  //--Working fine
//		sunBasketMyMenuPage.action_verifyText(sunBasketMyMenuPage.header_MyMenuHeader, "Vasavi");  //--Working fine
//		sunBasketMyMenuPage.action_TakeScreenshot();  // -- Working fine, need to update dynamic name for screenshots.
		
//		sunBasketMyMenuPage.action_verifyText(sunBasketMyMenuPage.button_ChangeMyRecipes, "Change My 2 Recipes");
		sunBasketMyMenuPage.button_ChangeMyRecipes.click();
		sunBasketMyMenuPage.action_MinimizePopup();
		
//		sunBasketMyMenuPage.action_verifyText(sunBasketMyMenuPage.dropdown_SelectRecipes, "4 recipes");
		
//		sunBasketMyMenuPage.action_getDropdownList(sunBasketMyMenuPage.dropdownList_SelectRecipes);  // -- Not printing values from Dropdown list
//		String[] expDropdownList = {"2 recipes" , "4 recipes"};
//		sunBasketMyMenuPage.action_verifyDropdownList(sunBasketMyMenuPage.dropdown_SelectRecipes, expDropdownList);
		
//		sunBasketMyMenuPage.action_selectDropdownOption(sunBasketMyMenuPage.dropdownList_SelectRecipes, "2 recipes");
//		SBUtil.waitForPageToLoad(5000);
		
//		sunBasketMyMenuPage.link_Cancel.click();
//		SunBasketMyMenuPage sunBasketMyMenuPage2 = new SunBasketMyMenuPage();
//		sunBasketMyMenuPage2.waitForPageToLoad();
//		sunBasketMyMenuPage2.action_verifyText(sunBasketMyMenuPage2.button_ChangeMyRecipes, "Change My 4 Recipes"); 
		
//		sunBasketMyMenuPage.action_chooseRecipes(sunBasketMyMenuPage.checkbox_AllReciepes, 4);   //-- Working fine.
		
//		sunBasketMyMenuPage.action_VerifyBrokenLinks(sunBasketMyMenuPage.link_allLinks);    //-- Working fine.
		
//		sunBasketMyMenuPage.action_DropDown(sunBasketMyMenuPage.dropdownList_SelectRecipes);   //-- Not getting all options from dropdown
		
/*		sunBasketMyMenuPage.button_SwitchToFamilyOrClassicMenu.click();
		System.out.println("Edit_ Title : " + sunBasketMyMenuPage.Title_EditMealPlan.getText());
		sunBasketMyMenuPage.button_Crossmark_EditMealPlan.click();
		*/   //-- Working fine

//		SunBasketMyMenuPage sunBasketMyMenuPage2 = new SunBasketMyMenuPage();
//		sunBasketMyMenuPage2.waitForPageToLoad();
//		sunBasketMyMenuPage2.button_SwitchToFamilyOrClassicMenu.click();

//		sunBasketMyMenuPage.action_GetDropdownLis(sunBasketMyMenuPage.dd_SelectRecipes);
		sunBasketMyMenuPage.action_dd(sunBasketMyMenuPage.dd_SelectRecipes);
		System.out.println("dd_SelectRecipes : " + sunBasketMyMenuPage.dd_SelectRecipes.getAttribute("name"));
		
		
		
	}

}

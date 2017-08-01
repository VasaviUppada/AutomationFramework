package com.SunBasket.Practice;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.SunBasket.Pages.SunBasketHomePage;
import com.SunBasket.Utility.BaseTest;
import com.SunBasket.Utility.DriverScript;

public class Test_validateHomePage extends BaseTest{

	@Test
	public void Test_HomePage(){
		SunBasketHomePage sunBasketHomePage = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		System.out.println("Page Title : " + sunBasketHomePage.getPageTitle());
		sunBasketHomePage.tab_Menu.click();
		SunBasketHomePage sunBasketHomePage2 = new SunBasketHomePage();
		sunBasketHomePage.waitForPageToLoad();
		sunBasketHomePage2.link_SignIn.click();
		System.out.println("Test Success !!!");
		
	}
}

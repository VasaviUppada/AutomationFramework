package com.SunBasket.Utility;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.VerificationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends DriverScript {

	public BasePage(WebDriver driver){
		DriverScript.driver = driver; 
        PageFactory.initElements(driver, this);
	}

	//*** Default wait time of 30sec. ***//* 
	public void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//*** Custom wait time ***//* 
	public void waitForPageToLoad(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	
	//*** Verify Text  ***//
	public  void verifyText(WebElement actual, String expected){
		try{
			assertTrue(actual.getText().matches(expected));
		}
		catch(Error e){
			e.getStackTrace();
		}
	}
	
	//*** To get Title of the page ***//
	public String getPageTitle(){
		return driver.getTitle();
	}


	

}

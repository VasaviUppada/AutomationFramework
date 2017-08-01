package com.SunBasket.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;

public class SunBasketSignInPage extends BasePage{

	public SunBasketSignInPage(){
		super(driver);		
	}
	
	/*** Web Elements ***/
	@FindBy(xpath = "//h2[text()='Sign In']")
	public WebElement header_SignIn;
	
	@FindBy(xpath = "//p[@class='subhead text-lg center']")
	public WebElement text_WelcomebackWeveMissedYou;
	
	@FindBy(id = "email")
	public WebElement textField_EnterYourEmail;
	
	@FindBy(id = "password")
	public WebElement textfield_EnterYourPassword;
	
	@FindBy(id = "rememberMe")
	public WebElement checkbox_RememberMe;
	
	@FindBy(xpath = "//a[@class='text-sm a-brown' and @href='#']")
	public WebElement link_ForgotPassword;
	
	@FindBy(xpath = "//input[@class='btn btn-primary float-right btn-continue'][@value='Sign In']")
	public WebElement button_SignIn;
		
	@FindBy(tagName = "a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}
	
	public void action_signIn(){
		textField_EnterYourEmail.sendKeys("vasavitest@test.com");
		textfield_EnterYourPassword.sendKeys("ReplacePassword123");
		button_SignIn.click();
	}

}

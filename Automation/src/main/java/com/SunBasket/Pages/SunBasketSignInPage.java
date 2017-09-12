package com.SunBasket.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import com.SunBasket.Utility.BasePage;
import com.SunBasket.Utility.SBUtil;
import com.aventstack.extentreports.Status;

public class SunBasketSignInPage extends BasePage{

	public SunBasketSignInPage(){
		super(threadDriver);		
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
		
	@FindBy(xpath = "//*[@href]|//a")
	public List<WebElement> link_allLinks;
	
	
	// *** Action Methods *** //
	
	//To verify all Links on a Page
	public void action_VerifyBrokenLinks(List<WebElement> allLinks){
		SBUtil.verifyBrokenLinks(allLinks);
	}

	public void action_ClickOnContinueButton(WebElement element){
		try {
			SBUtil.clickwithJavaScriptExecutor(element);
		} catch (Exception e) {
//			System.out.println("Unable to click on : " + element);
			logger.log(Status.FAIL, (Throwable)element);
			e.printStackTrace();
		}
	}
	
	public void action_signIn(String email){
		logger.log(Status.INFO, "Enter Email - " + email);
		textField_EnterYourEmail.sendKeys(email);
		logger.log(Status.INFO, "Enter Password");
		textfield_EnterYourPassword.sendKeys("ReplacePassword123");
		logger.log(Status.INFO, "Click On Continue");
		getScreentShotForExtentReport("SignInPage");
		action_ClickOnContinueButton(button_SignIn);
	}

}

package com.SunBasket.Utility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SBUtil extends DriverScript{

	/*** Custom wait time ***/ 
	public static void waitForPageToLoad(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	/*** Default wait time of 30sec. ***/ 
	public static void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	/*** select Checkbox ***/
	public static void checkboxSelect(WebElement chkbox){
		if(chkbox.isSelected()){
			System.out.println(chkbox + " already selected.");
		}
		else{
			chkbox.click();
			waitForPageToLoad(500);
		}
	}
	
	/*** Verify Attribute ***/
	public static boolean verifyAttributeValue(WebElement webElement, String attribute, String expected){
		if(expected.equalsIgnoreCase(webElement.getAttribute(attribute))){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*** Verify Text present or not ***/
	public static boolean verifyText(WebElement webElement, String expected){
		if(expected.equalsIgnoreCase(webElement.getText())){
			return true;
		}
		else{
			return false;
		}
	}	

	/*** Verify whether element contains the Text ***/
	public static void containsText(WebElement actual, String expected){
		try{
			assertTrue(actual.getText().contains(expected));
		}
		catch(Error e){
			e.getStackTrace();
		}
	}

	/*** To get Page Title ***/
	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	/*** Verify whether Element present or not ***/
	@SuppressWarnings("finally")
	public static boolean isTextPresent(String expected)	
	{
        boolean b = false;
        try{
        	b = driver.getPageSource().contains(expected);
        	return b;
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
        }     
        finally{
        	return b;
        }    
	}
		
//		String actual = webElement.getText();
//		if(webElement.isDisplayed()){
//			if(expected.equals(actual)){
//				 return "Pass : ActualMsg '" + actual + "' matches ExpectedMsg! " + expected;
//			}
//			else{
//				return "Fail : ActualMsg '" + actual + "' didn't match ExpectedMsg! '" + expected + "' ";
//			}
//		}
//		else{
//			System.out.println(webElement + " : didn't displayed");
//		}
//		return null ;
//		}

	/*** Get all Links on a Page & verify Broken or not ***/
	public static void verifyBrokenLinks(List<WebElement> allLinks){
		System.out.println("Total Links : " + allLinks.size());
		for(int i=0; i<allLinks.size(); i++){
			WebElement link = allLinks.get(i);
			String urlLink = link.getAttribute("href");
//			System.out.println("All URL _ getAttribute : " + url);
			verifyLinks(urlLink);
		}
	}
	
	/*** Verify Links on Page ***/
	public static void verifyLinks(String linkUrl)	 
	{	
		try 
        {
           URL url = new URL(linkUrl);           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();          
           httpURLConnect.setConnectTimeout(3000);           
           httpURLConnect.connect();          
           if(httpURLConnect.getResponseCode()==200)
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
           }
           if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
           }
        } catch (Exception e) {
           
        }
	}
	
	/*** Take Screenshot ***/ 
	public static void getScreenshot(){		
		String filePath = new File("Screenshots_Failed").getAbsolutePath();
    	String fileSeperator = System.getProperty("file.separator");
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy _ h.m.s");
        Date date = new Date();
        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File trg = new File(filePath + fileSeperator + "SS_" + dateFormat.format(date) + ".png");
            FileUtils.copyFile(src, trg);
        }catch(Exception e){
            //if it fails to take screenshot then this block will execute
            System.out.println("Failure to take screenshot "+e);

        }
	}
	
	/*** Get Screenshot ***/  //-- working fine
    public static void takeScreenshot(String methodName) throws Exception 
    {
		String filePath = new File("Screenshots_Failed").getAbsolutePath();
    	String fileSeperator = System.getProperty("file.separator");
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy _ h.m.s");
        Date date = new Date();
        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File trg = new File(filePath +fileSeperator + "SS_" + methodName + "_" + dateFormat.format(date) + ".png");           
            FileUtils.copyFile(src, trg);
        }catch(Exception e){
            System.out.println("Failure to take screenshot "+e);
        }
     }

	
	
	// unable to get values using option.getText()
	/*** Get all options from Dropdown list on a Page ***/  
	
	public static void getDropdownList(List<WebElement> dropdownList, WebElement dropdown){
    	System.out.println(" Size : " + dropdownList.size());
//    	for (WebElement option : dropdownList){
//    		System.out.println("Options : " + option.getText());
//    	}
    	getAllOptions(dropdown);
	}
	
	public static List<String> getAllOptions(WebElement element) {
	    List<String> options = new ArrayList<String>();
	    for (WebElement option : new Select(element).getOptions()) {
	        String txt = option.getText();
	        if (option.getAttribute("value") != "") options.add(txt);
	    }
	    return options;
	}
		
		
	/*** Get all options from dropdown list & verify with expected list ***/ //  -- need to check
	public static void verifyDropdownList(WebElement webElement, String[] expectedList){
		Select dropdown = new Select(webElement);
        List<WebElement> values = dropdown.getOptions();          
        for (int i = 0; i < values.size(); i++){
        	System.out.println("Value_" + i + " : "+ values.get(i).getText());
            Assert.assertEquals(values.get(i).getText(), expectedList[i], "Values in the drop down for number of occupants are wrong"  );
            System.out.println("List captured ");
        }
//        for(WebElement list : values){
//        	System.out.println("List : " + dropdown.getOptions());
//        }
	}
	
	//*** Select option from dropdown list ***/
	public static void selectDropdownOption(List<WebElement> dropdownList, String selectOption){
    	String optionText = null;
		for (WebElement option : dropdownList){
    		optionText = option.getText();
    		if(optionText.equalsIgnoreCase(selectOption)){
    			option.click();
    			break;
    		}
    	}
	}
	
	// need to verify 
	public void selectOptionByVisibleText(WebElement dropdownelement, String option){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByVisibleText(option);		
	}
	
	public void selectOptionByValue(WebElement dropdownelement, String value){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByValue(value);
	}
	
	public void selectOptionByIndex(WebElement dropdownelement, int index){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByIndex(index);
	}
	
	
	// --- Not working
	/*** Select Value from Dropdown List using 'SelectByVisibleText' ***/
	public static void selectDropdownOptionByVisibleText(List<WebElement> dropDownList, WebElement dropdown, String visibleText, String xPath){		
		String valuetext = null;
		Select select = new Select(dropdown);
		List<WebElement> opt = dropdown.findElements(By.xpath(xPath));
		 for (WebElement value: opt) 
		    {
		        valuetext = value.getText();
		        if (valuetext.equalsIgnoreCase(visibleText))
		        {
		            try
		            {
		                select.selectByVisibleText(valuetext);                        
		                break;
		            }
		            catch (Exception e)
		            {
		                System.out.println(valuetext + "Value not found in Dropdown to Select");
		            }       
		        }
		    }		
//		Select option = new SelectElement(dropdown).SelectByText("");
//
//		dropdown.selectByVisibleText(visibleText);
	}
	
	
	// -- Not working
	/*** Select Value from Dropdown List using 'SelectByIndex' ***/
	public static void selectDropdownOptionByIndex(WebElement dropdown, int index){		
		Select option= new Select(dropdown);
		System.out.println(option.getOptions());
		option.selectByIndex(index);
	}
	
	// -- Not working
	/*** Select Value from Dropdown List using 'SelectByValue' ***/
	public static void selectDropdownOptionByValue(WebElement webElement, String value){		
		Select dropdown= new Select(webElement);
		dropdown.selectByValue(value);
	}
	
	



}

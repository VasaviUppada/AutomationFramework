package com.SunBasket.Utility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SunBasket.Config.Config;
import com.aventstack.extentreports.Status;

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
	
	/*** Explicit Wait - titleIs ***/ 
	public static void waitForPageTitle(String pageTitle){
		WebDriverWait wait=new WebDriverWait(driver, 60);
		logger.log(Status.INFO, "Verify Page Title - " + pageTitle);
		wait.until(ExpectedConditions.titleIs(pageTitle));
		logger.log(Status.PASS, "Page Title Matches");
	}
	
	/*** Explicit Wait - urlContains ***/
	public static void waitForUrlContains(String urlText){
		WebDriverWait wait=new WebDriverWait(driver, 60);
		logger.log(Status.INFO, "Verify Url Contains - " + urlText);
		wait.until(ExpectedConditions.urlContains(urlText));
		logger.log(Status.PASS, "URL Matches");
	}
	
	/*** Explicit Wait - urlIs ***/
	public static void waitForUrlMatches(String url){
		WebDriverWait wait=new WebDriverWait(driver, 60);
		logger.log(Status.INFO, "Verify Url Matches - " + url);
		wait.until(ExpectedConditions.urlMatches(url));
		logger.log(Status.PASS, "URL Matches");
	}
	
	/*** Explicit Wait - urlIs ***/
	public static void waitForUrlToBe(String url){
		WebDriverWait wait=new WebDriverWait(driver, 60);
		logger.log(Status.INFO, "Verify Url To be - " + url);
		wait.until(ExpectedConditions.urlToBe(url));
		logger.log(Status.PASS, "URL Matches");
	}
	
	/*** Explicit Wait - titleContains ***/ 
	public static void waitForPageTitleContains(String titleContains){
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.titleContains(titleContains));
	}
	
	/*** Explicit Wait - textToBePresentInElement ***/ 
	public static void waitForTextToBePresentInElement(WebElement textToBePresentInElement, String expectedText){
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElement(textToBePresentInElement, expectedText));
	}

	/*** Explicit Wait - elementToBeClickable ***/ 
	public static void waitForElementToBeClickable(WebElement elementToBeClickable){
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(elementToBeClickable));
	}
	
	/*** Explicit Wait - explicitWait_Wait for Windows to be appeared ***/ 
	public static void wait_WindowsToAppear(int noofWindows){
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.numberOfWindowsToBe(noofWindows));
	}
	
	/*** Explicit Wait - presenceOfElementLocated - To check whether element is present in DOM or not ***/ 
    public static WebElement waitForElement(By locator) {
    	WebDriverWait wait = new WebDriverWait(driver, 40);
    	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
	
	/*** Click on an element using Java Script Executor ***/
	public static void clickwithJavaScriptExecutor(WebElement button) throws Exception {
		try {
			if (button.isDisplayed()) {
				waitForElementToBeClickable(button);
				logger.log(Status.INFO, "Click on " + button.getText());
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);			
			} else {
				System.out.println("No element Present to click - " + button.getText());
				logger.log(Status.FAIL, button.getText());
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		}/* catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}*/
	}
	
	/*** Click on an element using Java Script Executor ***/
	public static void sendKeyswithJavaScriptExecutor(WebElement textBox, String textToPass) throws Exception {
		try {
			waitForElementToBeClickable(textBox);
			if (textBox.isDisplayed()) {
				String script = "arguments[0].value=" + textToPass + ";";
				logger.log(Status.INFO, "Enter " + textBox);
				((JavascriptExecutor) driver).executeScript(script, textBox);
			} else {
				logger.log(Status.ERROR, "No Textbox present to enter text - " + textBox);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to send value to element "+ e.getStackTrace());
		}
	}
	
	/*** Scroll to view an element using Java Script Executor ***/
	public static void scrollToViewWithJavaScriptExecutor(WebElement element) throws Exception {
		try {
			waitForElementToBeClickable(element);
			if (element.isDisplayed()) {
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();");
				System.out.println("Scrolling page to view element using java script...");
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to scroll to view element "+ e.getStackTrace());
		}
	}
	
	/*** select Checkbox ***/
	public static void checkboxSelect(WebElement chkbox){
		if(chkbox.isSelected()){
//			System.out.println(chkbox + " already selected.");
			logger.log(Status.INFO, chkbox.getText() + "  already selected");
		}
		else{
			logger.log(Status.INFO, "Select " + chkbox.getText());
			chkbox.click();
			waitForPageToLoad(200);
			logger.log(Status.PASS, chkbox.getText() + "  already selected");
		}
	}
	
	/*** Verify Attribute ***/
	public static boolean verifyAttributeValue(WebElement webElement, String attribute, String expected){
		try {
        	return expected.equalsIgnoreCase(webElement.getAttribute(attribute));
		} catch (Exception e) {
			e.getStackTrace();
        	return false;
		}		
	}
	
	/*** Verify Text present or not ***/	//-- working fine
	public static boolean verifyText(WebElement actual, String expected){ 
		try {
        	return expected.equalsIgnoreCase(actual.getText());
		} catch (Exception e) {
			e.getStackTrace();
        	return false;
		}
	}	

	/*** Verify whether element contains the Text ***/	//-- working fine
	public static boolean containsText(WebElement actual, String expected){ 
		try {
        	return actual.getText().contains(expected);
		} catch (Exception e) {
			e.getStackTrace();
        	return false;
		}
	}

	/*** To get Page Title ***/
	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	/*** Verify whether Element present or not ***/
	public static boolean isTextPresent(String expected)	
	{
        try{
        	logger.log(Status.INFO, "Verify Text Present - " + expected);
        	boolean b = driver.getPageSource().contains(expected);
        	return b;
        }
        catch (Exception e){
        	System.out.println(e.getMessage()); 
        	return false;
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

	/*** Verify Broken Links ***/		//-- Working fine
	public static void verifyBrokenLinks(List<WebElement> allLinks){
		System.out.println("Total Links : " + allLinks.size());
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        Iterator<WebElement> it = allLinks.iterator();
        while(it.hasNext()){
        	url = it.next().getAttribute("href");
        	if(url == null || url.isEmpty()){
        	System.err.println("Null URL : " + url +" : URL is either not configured for anchor tag or it is empty");
   	        continue;
        	}
//            if(!url.startsWith(Config.Url.base_url)){
//                System.err.println("Skipped _ URL belongs to another domain : " + url);
//                continue;
//            }
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());               
                huc.setRequestMethod("HEAD");               
                huc.connect();             
                respCode = huc.getResponseCode();               
                if(respCode >= 400){
                    System.err.println("Broken URL : " + url + " - " + respCode + " : "+ huc.getResponseMessage());
                }
                else{
                    System.out.println("Success URL : " + url + " - " + respCode + " : "+ huc.getResponseMessage());
                }
                    
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	}

	/*** Get all Links on a Page & verify Broken or not ***/		// Working fine... Currently not using
	public static void verifyBroken(List<WebElement> allLinks){
		System.out.println("Total Links : " + allLinks.size());
		for(int i=0; i<allLinks.size(); i++){
			WebElement link = allLinks.get(i);
			String urlLink = link.getAttribute("href");
//			System.out.println("All URL _ getAttribute : " + url);
			verifyLinkResponse(urlLink);
		}
	}
	
	/*** Verify Links response on Page ***/
	public static void verifyLinkResponse(String linkUrl)	 
	{	
		try 
        {
           URL url = new URL(linkUrl);           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();          
           httpURLConnect.setConnectTimeout(3000);           
           httpURLConnect.connect();          
           if(httpURLConnect.getResponseCode()==200)
           {
               System.out.println("Success Link : " + linkUrl+" - " + httpURLConnect.getResponseMessage());
           }
           else if(httpURLConnect.getResponseCode()>=400){
        	   System.out.println("Broken Link : " + linkUrl+" - " + httpURLConnect.getResponseMessage());
           }
           else if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
           {
               System.out.println(linkUrl+" - " + httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
           }
        } catch (MalformedURLException e) {
        	System.out.println("Exception Link_getBytes : " + linkUrl);
            e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.getMessage();
        }
	}
	
	/*** Take Screenshot ***/ 	//-- working fine
	public static void getScreenshot(){		
		String filePath = new File("Screenshots_Extent").getAbsolutePath();
    	String fileSeperator = System.getProperty("file.separator");
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        Date date = new Date();
        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File trg = new File(filePath + fileSeperator + "SS_" + dateFormat.format(date) + ".jpg");
            FileUtils.copyFile(src, trg);
        }catch(Exception e){
            //if it fails to take screenshot then this block will execute
            System.out.println("Failure to take screenshot "+e);

        }
	}
	
	/*** Get Screenshot ***/  //-- working fine
    public static void takeScreenshot(String methodName) throws Exception 
    {
		String filePath = new File("Screenshots_Extent").getAbsolutePath();
    	String fileSeperator = System.getProperty("file.separator");
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        Date date = new Date();
        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String trg_path = filePath +fileSeperator + "SS_" + methodName + "_" + dateFormat.format(date) + ".jpg";
            File trg = new File(trg_path);           
            FileUtils.copyFile(src, trg);
        }catch(Exception e){
            System.out.println("Failure to take screenshot "+e);
        }
     }
    
    /*** Get Screenshot Path ***/
    public static String getScreenshotPath()
    {
		String filePath = new File("Screenshots_Extent").getAbsolutePath();
    	String fileSeperator = System.getProperty("file.separator");
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        Date date = new Date();
        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String trg_path = filePath +fileSeperator + "ExtentScreenshot_" + dateFormat.format(date) + ".jpg";
            File trg = new File(trg_path);           
            FileUtils.copyFile(src, trg);
    return trg_path;
    }
    catch (Exception ex)
    {
    ex.printStackTrace();
    return ex.getMessage();
    }
    }

	
	/*** Web Table ***/		//-- working fine
	public static void webTable(){
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int rows_count = rows.size();
		for (int row = 0; row < rows_count; row++){
			 List<WebElement> columns = rows.get(row).findElements(By.tagName("td"));
			 int columns_count = columns.size();
			 System.out.println("No.of Cells in Row_" + row + " : " + columns_count);
			 for (int column = 0; column < columns_count; column++) {
				 //To retrieve text from the cells.
				 String celltext = columns.get(column).getText();
				 System.out.println("Cell[" + row + "][" + column + "] : " + celltext);
			}
		}
		
	}
	
	/*** Verify Text in Web Table ***/		//-- working fine
	public static boolean VerifyText_webTable(int rowNo, int colNo, String expected){
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int rows_count = rows.size();
		for (int row = 0; row < rows_count; row++){
			 List<WebElement> columns = rows.get(row).findElements(By.tagName("td"));
			 int columns_count = columns.size();
			 for (int column = 0; column < columns_count; column++) {
				if(row==rowNo && column==colNo){
					try {
						String cellText = columns.get(column).getText();
						System.out.println("Cell[" + row + "][" + column + "] : " + cellText);
			        	return expected.equalsIgnoreCase(cellText);
					} catch (Exception e) {
						e.getStackTrace();
			        	return false;
					}
				}
			}
		}
		return false;
	}
	
	public static List<ArrayList<String>> VerifyText_web(){
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr//td//.."));
		List<ArrayList<String>> rowsData = new ArrayList<ArrayList<String>>();

		for(WebElement row:rows){
		    List<WebElement> rowElements = row.findElements(By.xpath(".//td"));

		    ArrayList<String> rowData = new ArrayList<String>();

		    for(WebElement column:rowElements){
		        rowData.add(column.getText().toString());
		    }

		    rowsData.add(rowData);
		}

		return rowsData;
	}
	

    
    /************ Need to Verify **************/
    
	/*** Select Option from Dropdown List using 'SelectByVisibleText' ***/
	public static void selectOptionByVisibleText(WebElement dropdownelement, String option){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByVisibleText(option);		
	}
	
	/*** Select Option from Dropdown List using 'SelectByValue' ***/
	public static void selectOptionByValue(WebElement dropdownelement, String value){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByValue(value);
	}
	
	/*** Select Option from Dropdown List using 'SelectByIndex' ***/
	public static void selectOptionByIndex(WebElement dropdownelement, int index){
		Select dropdown = new Select(dropdownelement);
		dropdown.selectByIndex(index);
	}
	
	/*** Select Random Value from Dropdown List ***/
	public static void selectRandomOptionFromDropdown(WebElement dropdownelement){
		Select dropdown = new Select(dropdownelement);
		List<WebElement> allOptions = dropdown.getOptions();
		int listCount = allOptions.size();
		Random num = new Random();
		int iSelect = num.nextInt(listCount);
		dropdown.selectByIndex(iSelect);
		System.out.println("Random Selected Value from Dropdown List : " + dropdownelement.getAttribute("value"));
	}
	
	/*** Get all options from dropdown list ***/
	public static void getAllDropdownOptions(WebElement dropdownelement){		
	    Select dropdown = new Select(dropdownelement);
	    List<WebElement> options = dropdown.getOptions();
	    for(WebElement opt : options){
	    	System.out.println("Option Value : " + opt.getAttribute("value"));
	        System.out.println("Display Text : " + opt.getText());
	    }
	}

	/*** Get all options from dropdown list & verify with expected list ***/
	public static void verifyAllDropdownOptions(WebElement dropdownelement, String[] expectedDropdownList){		
	    Select dropdown = new Select(dropdownelement);
	    List<WebElement> options = dropdown.getOptions();
	    for (int i = 0; i < options.size(); i++){
        	System.out.println("Option_" + i + " : "+ options.get(i).getText());
            Assert.assertEquals(options.get(i).getText(), expectedDropdownList[i], "Options from Dropdown are not as expected!"  );
            System.out.println("Option_" +  i + " captured!");
        }
	}
	
	/*** Windows Handles ***/
	public static WebDriver getWindowToHandle(){
        WebDriver popup = null;
        Set<String> windowIterator = driver.getWindowHandles();
//        System.err.println("No of windows :  " + windowIterator.size());
        for (String window : windowIterator) {
          String windowHandle = window; 
          popup = driver.switchTo().window(windowHandle);
//          System.out.println("Child Window Title : " + popup.getTitle());
//          System.out.println("Child Window Url : " + popup.getCurrentUrl());
        }
            return popup;
	}
	




}

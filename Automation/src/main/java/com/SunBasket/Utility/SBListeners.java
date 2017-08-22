package com.SunBasket.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
 
import org.testng.IInvokedMethodListener;
 
import org.testng.ISuite;
 
import org.testng.ISuiteListener;
 
import org.testng.ITestContext;
 
import org.testng.ITestListener;
 
import org.testng.ITestNGMethod;
 
import org.testng.ITestResult;
 
import org.testng.Reporter;
 
public class SBListeners extends DriverScript implements ITestListener, ISuiteListener, IInvokedMethodListener {
 
	// This belongs to ISuiteListener and will execute before the Suite start
	@Override
	public void onStart(ISuite result) {
		Reporter.log("Begins Suite Execution : " + result.getName(), true);
	}
 
	// This belongs to ISuiteListener and will execute, once the Suite is finished
	@Override
	public void onFinish(ISuite result) { 
		Reporter.log("Completed Suite Execution : " + result.getName(), true);
	}
 
	// This belongs to ITestListener and will execute before starting of Test set/batch 
	@Override
	public void onStart(ITestContext result) {
		Reporter.log("Begins Test Execution : " + result.getName(), true);
	}
 
	// This belongs to ITestListener and will execute, once the Test set/batch is finished
	@Override
	public void onFinish(ITestContext result) {
		Reporter.log("Completed Test Execution : " + result.getName(), true);
	}
 
	// This belongs to ITestListener and will execute only when the test is pass 
	@Override
	public void onTestSuccess(ITestResult result) {
		// This is calling the printTestResults method
		System.out.println("Success Test : " + result.getName());
		printTestResults(result);
	}
 
	// This belongs to ITestListener and will execute only on the event of fail test
	@Override
	public void onTestFailure(ITestResult result) {
		System.err.println("*** Failed Tests : "+ result.getName());
		String methodName = result.getName().toString().trim();
		try {
			SBUtil.takeScreenshot(methodName);
			System.out.println("Screenshot saved...");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		System.out.println("Failed Test!");
		printTestResults(result);
	}
	
	// This belongs to ITestListener and will execute before the main test start (@Test)
	
	@Override
	public void onTestStart(ITestResult result) { 
		System.out.println("Begin Execution : " + result.getName()); 
	}
 
	// This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped Tests : " + result.getName());
		printTestResults(result);
	}
 
	// This is just a piece of shit, ignore this 
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) { 
	}
 
	// This is the method which will be executed in case of test pass or fail 
	// This will provide the information on the test 
	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
 
		String status = null;
		switch (result.getStatus()) {
		
		case ITestResult.SUCCESS: 
			status = "Pass"; 
			break;
 
		case ITestResult.FAILURE: 
			status = "Failed"; 
			break;
 
		case ITestResult.SKIP:
			status = "Skipped";
		} 
		Reporter.log("Test Status: " + status, true); 
	}
 
	// This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test 
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) { 
		String textMsg = "Begins Method Execution : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}
 
	// This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Completed Method Execution : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true); 
	}
 
	// This will return method names to the calling function 
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName(); 
	}
 
}
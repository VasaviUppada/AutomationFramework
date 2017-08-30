package com.SunBasket.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.configuration.Config;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SBListeners extends DriverScript implements ITestListener, ISuiteListener, IInvokedMethodListener {

	// This will return method names to the calling function 
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName(); 
	}

	@Override
	public void onStart(ISuite suite) {
		startReport(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		logger.log(Status.INFO, " Test Finished");
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
//		logger.log(Status.INFO, " Test Method Execution Starts : " +  result.getName());
		logger.log(Status.INFO, MarkupHelper.createLabel("Test Method Execution Starts : " + result.getName(), ExtentColor.CYAN));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.log(Status.PASS, MarkupHelper.createLabel("SUCCESS", ExtentColor.GREEN));
		getResult(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {	
		String getScreenshotPath = SBUtil.getScreenshotPath();
		logger.fail(MarkupHelper.createLabel("Above Step FAILED", ExtentColor.RED));
        try {
    		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath).build();
    		logger.fail("Screenshot", mediaModel);
			logger.addScreenCaptureFromPath(getScreenshotPath, "Failed Scr");
			
			logger.error(result.getThrowable());
		} catch (IOException e) {
			logger.log(Status.FAIL, "Fail to capture the screenshot");
			e.printStackTrace();
		}

		getResult(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.log(Status.SKIP, MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
		getResult(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		logger.log(Status.INFO, context.getName() + " Test Started ");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.log(Status.INFO, context.getName() + " Test Finished ");
		extent.flush();
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//		String textMsg = "Begins Method Execution : " + returnMethodName(method.getTestMethod());
//		logger.log(Status.INFO, textMsg);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//		String textMsg = "Completed  Execution : " + returnMethodName(method.getTestMethod());
//		logger.log(Status.INFO, textMsg);
	}

	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.SUCCESS)
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASS ", ExtentColor.GREEN));
		else if(result.getStatus() == ITestResult.SKIP)
			logger.log(Status.SKIP, result.getName() + " - SKIP : " + "\n" + result.getThrowable());
//			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - SKIP ", ExtentColor.ORANGE) + "\n" + result.getThrowable());
		else if(result.getStatus() == ITestResult.FAILURE){
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - FAIL ", ExtentColor.RED));
		}
		else if(result.getStatus() == ITestResult.STARTED)
			logger.log(Status.INFO, result.getName() + " : STARTED");
		extent.flush();
	}
	
	public static void startReport(ISuite suite){

		extent.attachReporter(htmlReporter);
//		extent.log(Status.INFO, "HTML", "Usage: BOLD TEXT");
		extent.setSystemInfo("Suite Name", suite.getName());
		extent.setSystemInfo("User Name", "Vasavi Uppada");
		extent.setTestRunnerOutput("TestNG");
		
//		htmlReporter.config().setDocumentTitle(suite.getParentModule());
		htmlReporter.config().setDocumentTitle("ExtentReports_Join Flow");
		htmlReporter.config().setReportName(suite.getName());
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	    htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");		
		logger = extent.createTest(suite.getName());
	}
/*
public static ExtentReports createInstance(String fileName) {
//	Config.initConstants();
//    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTheme(Theme.DARK);
    htmlReporter.config().setDocumentTitle("Report Title"); //Config.ReportTitle
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName("Report Name"); //Config.ReportTitle
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    
    return extent;
}
*/
}


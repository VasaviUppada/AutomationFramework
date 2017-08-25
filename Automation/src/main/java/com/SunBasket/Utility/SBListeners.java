package com.SunBasket.Utility;

import java.io.File;
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

import com.relevantcodes.extentreports.LogStatus;

public class SBListeners extends DriverScript implements ITestListener, ISuiteListener, IInvokedMethodListener {
  
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//    	report = new ExtentReports(outputDirectory + File.separator + "ExtentReportTestNG.html", true);
  
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
  
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
  
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
  
        report.flush();
        report.close();
    }
  
    private void buildTestNodes(IResultMap tests, LogStatus status) {
  
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
            	logger = report.startTest(result.getMethod().getMethodName());
  
//                test.getTest().startedTime = getTime(result.getStartMillis());
//                test.getTest().endedTime = getTime(result.getEndMillis());
  
                for (String group : result.getMethod().getGroups())
                	logger.assignCategory(group);
  
                String message = "Test " + status.toString().toLowerCase() + "ed";
  
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
  
                logger.log(status, message);
  
                report.endTest(logger);
            }
        }
    }
  
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }

	// This will return method names to the calling function 
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName(); 
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String textMsg = "Begins Method Execution_Extent : " + returnMethodName(method.getTestMethod());
		logger.log(LogStatus.INFO, textMsg);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		String textMsg = "Completed Method Execution_Extent : " + returnMethodName(method.getTestMethod());
		logger.log(LogStatus.INFO, textMsg);
	}

	@Override
	public void onStart(ISuite suite) {
		logger = report.startTest(suite.getName(), "Initializing!!!");
		logger.log(LogStatus.INFO, "Suite Test Started");
	}

	@Override
	public void onFinish(ISuite suite) {
		
		logger.log(LogStatus.INFO, "Test Finished");
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.log(LogStatus.INFO, "OnTestStart Started!!!");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, "SUCCESS");
		getResult(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.log(LogStatus.FAIL, "FAILED");
		logger.addScreenCapture(("user.dir") +"/Screenshots_Failed");
		getResult(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.log(LogStatus.SKIP, "SKIPPED");
		getResult(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		logger.log(LogStatus.INFO, "ITestCOntent, OnStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.log(LogStatus.INFO, "ITestContent On Finish!!!");
	}
	
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.SUCCESS)
			logger.log(LogStatus.PASS, result.getName() + "Test PASS");
		else if(result.getStatus() == ITestResult.SKIP)
			logger.log(LogStatus.SKIP, result.getName() + "Test SKIP : " + result.getThrowable());
		else if(result.getStatus() == ITestResult.FAILURE)
			logger.log(LogStatus.FAIL, result.getName() + "Test FAIL : " + result.getThrowable());
		else if(result.getStatus() == ITestResult.STARTED)
			logger.log(LogStatus.INFO, result.getName() + "Test STARTED");
	}

}


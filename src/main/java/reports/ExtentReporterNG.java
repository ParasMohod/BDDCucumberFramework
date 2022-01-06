package reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseClass.BaseClassDef;
 
public class ExtentReporterNG implements IReporter 
{
	public ExtentReports extent;
	public ExtentTest test;
	
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports("D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\reports\\"+"ExtentReportsTestNG.html", true);
    	for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) 
            {
            	
                ITestContext context = r.getTestContext();
                try 
                {
                	buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                    buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                    buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                }
                catch(IOException e)
                {
                	System.out.println(e.getMessage());
                }
            }
        }
 
        extent.flush();
        extent.close();
    }
    
    private String getTestName(ITestResult result)
    {
    	ITestNGMethod testNGMethod = result.getMethod();
    	Method method = testNGMethod.getConstructorOrMethod().getMethod();
    	Test testAnnotation = (Test) method.getAnnotation(Test.class);
    	if (testAnnotation != null)
    		return testAnnotation.testName();
    	else
    		return "";
    }
    private void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
                if(result.getStatus() == ITestResult.FAILURE)
                {
                	String testname = getTestName(result);
                	test.log(LogStatus.FAIL, "Test Case Failed - "+testname+".");
                	test.log(LogStatus.FAIL, "Test method Failed - "+result.getName());
                	test.log(LogStatus.FAIL, "The failure reason -  "+result.getThrowable());
                	
                	BaseClassDef.getScreenshot(result.getName());
                	String scrnshotPath = BaseClassDef.getScreenshot(result.getName());
                	test.log(LogStatus.FAIL, test.addScreencast(scrnshotPath));
                }
                else if(result.getStatus() == ITestResult.SKIP)
                {
                	test.log(LogStatus.SKIP, "Test Case Skipped - "+result.getName());
                }
                else if(result.getStatus() == ITestResult.SUCCESS)
                {
                	String testname = getTestName(result);
                	test.log(LogStatus.PASS, "Test Case Passed - "+testname+".");
                }
                /*test.getTest(). = getTime(result.getStartMillis());
                test.getTest().endedTime = getTime(result.getEndMillis());*/
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
                	test.log(status, message);
 
                extent.endTest(test);
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}
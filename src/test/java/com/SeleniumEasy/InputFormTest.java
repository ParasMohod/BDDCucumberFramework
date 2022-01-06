package com.SeleniumEasy;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseClass.BaseClassDef;
import pageObjects.homePage.HomePage;
import pageObjects.inputForms.SimpleFormPage;
import suiteSetup.inputForms.GetSimpleFormTestData;
import utilities.CommonFunctionalities;

public class InputFormTest extends BaseClassDef
{
	GetSimpleFormTestData testData;
	HomePage homepg;
	SimpleFormPage simpleFormpg;
	CommonFunctionalities functions;
	ExtentTest test;
	
	@BeforeSuite
	public void suiteSetup()
	{
		setExtentReport();
	}
	
	@BeforeMethod
	public void methodSetup(Method method) throws Exception
	{
		String testname = getTestname(method);
		test = setExtentTest(method.getName(), testname);
		driver = initializeDriver();
		launchBrowser(driver, URL);
		homepg = new HomePage(driver, test);
		try 
		{
			homepg.clickInputFormsLink();
			homepg.simpleFormLinkLocator();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		simpleFormpg = new SimpleFormPage(driver, test);
		functions = new CommonFunctionalities();
		testData = new GetSimpleFormTestData();
	}
	
	@Test(priority=0, testName="Verify 1st Activity Box information bullet point texts are correct")
	public void verifyBulletPoints_1stBox() throws Exception
	{	
		testData.SetTestData("Verify 1st Activity Box information bullet point texts are correct");
		String expected_page_title = testData.page_title;
		String expected_bullet_point_content = testData.bullet_point_text;
		
		String bulletPointXpath = simpleFormpg.get1stBlockBulletListLocator();
		String ActualpgTitle = simpleFormpg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		functions.verifyBulletText(driver, bulletPointXpath, expected_bullet_point_content);
		test.log(LogStatus.INFO, "Bullet point text is correct.", "Bullet point text verification");
	}
	
	@Test(priority=1, testName="Verify that the entered message in 1st Block is displayed when \"Show Message\" button is clicked")
	public void verify1stBlockEnterMessageDisplay() throws Exception
	{
		testData.SetTestData("Verify that the entered message in 1st Block is displayed when \"Show Message\" button is clicked");
		String expected_page_title = testData.page_title;
		String input_to_message_box_content = testData.message_box_input_text;
		
		String ActualpgTitle = simpleFormpg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		simpleFormpg.enter1stBlockEnterMessageBox(input_to_message_box_content);
		simpleFormpg.click1stBlockShowMessageBtn();
		String msgDisplayed = simpleFormpg.get1stBlockMessageDisplayLabelText();
		
		functions.verifyText(input_to_message_box_content, msgDisplayed, "Message displayed is incorrect or message is not displayed");
		test.log(LogStatus.INFO, "Entered message box is correctly displayed.", "Message verification");
	}
	
	@Test(priority=2, testName="Verify that the sum of \"a\" and \"b\" in 2nd Block is displayed correct when \"Get Total\" button is clicked")
	public void verify2ndBlockSumTotalDisplay() throws Exception
	{
		testData.SetTestData("Verify that the sum of \"a\" and \"b\" in 2nd Block is displayed correct when \"Get Total\" button is clicked");
		String expected_page_title = testData.page_title;
		String a_value = testData.enter_a;
		String b_value = testData.enter_b;
		
		String ActualpgTitle = simpleFormpg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		simpleFormpg.enter2ndBlockEnterValueBox(a_value, b_value);
		simpleFormpg.click2ndBlockGetTotalBtn();
		String totalDisplayed = simpleFormpg.get2ndBlockMessageDisplayLabelText().trim();
		
		String totalCalculated = functions.sumOfNumbers(a_value, b_value);
		
		functions.verifyText(totalDisplayed, totalCalculated, "Total displayed is incorrect or message is not displayed");
		test.log(LogStatus.INFO, "The calculated sum is correct.", "Calculation verification");
	}
	
}
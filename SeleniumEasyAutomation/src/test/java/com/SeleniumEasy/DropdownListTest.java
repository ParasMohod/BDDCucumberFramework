package com.SeleniumEasy;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseClass.BaseClassDef;
import pageObjects.homePage.HomePage;
import pageObjects.inputForms.SelectDropDownPage;
import suiteSetup.inputForms.GetSelectDropDownTestData;
import utilities.CommonFunctionalities;

public class DropdownListTest extends BaseClassDef
{
	GetSelectDropDownTestData testData;
	HomePage homepg;
	SelectDropDownPage selectDropDownPg;
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
			homepg.clickSelectDropDownListLink();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		selectDropDownPg = new SelectDropDownPage(driver, test);
		functions = new CommonFunctionalities();
		testData = new GetSelectDropDownTestData();
	}
	
	@Test(priority=0, testName="Select day from the list by visible text")
	public void select_dropDown_ByVisibleText() throws Exception
	{
		testData.SetTestData("Select day from the list by visible text");
		String item_text = testData.select_list_demo;
		String expected_page_title = testData.page_title_text;
		
		String ActualpgTitle = selectDropDownPg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		selectDropDownPg.selectDemoList(item_text);
		test.log(LogStatus.INFO, "Dropdown is selected by visible text", "Dropdown selection verification");
	}
	
	@Test(priority=1, testName="Select day from the list by value")
	public void select_dropDown_ByValue() throws Exception
	{
		testData.SetTestData("Select day from the list by value");
		String item_value = testData.select_list_demo;
		String expected_page_title = testData.page_title_text;
		
		String ActualpgTitle = selectDropDownPg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		selectDropDownPg.selectDemoList(item_value);
		test.log(LogStatus.INFO, "Dropdown is selected by value attribute.", "Dropdown selection verification");
	}
	
	@Test(priority=2, testName="Select all dropdown values of multi select list demo by visible text")
	public void select_multiDropdown_ByVisibleText() throws Exception
	{
		testData.SetTestData("Select all dropdown values of multi select list demo by visible text");
		List<String> dropdownValues = testData.getMultiDropDownValue(testData.data);
		String expected_page_title = testData.page_title_text;
		
		String ActualpgTitle = selectDropDownPg.getPageTitle();
		Assert.assertEquals(ActualpgTitle, expected_page_title, "Title text is different");
		test.log(LogStatus.INFO, "Page title is correct.", "Page title verification");
		
		selectDropDownPg.selectDemoMultiList(dropdownValues);
		
		selectDropDownPg.clickGetAllSelectedButton();
		
		String displayed_multiList = selectDropDownPg.getLabelTextDisplayed_MultiList();
		
		selectDropDownPg.verify_DisplayedLabelTextContains_SelectedValues_MultiList(displayed_multiList, dropdownValues);
	}
}

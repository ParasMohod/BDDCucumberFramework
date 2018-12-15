package pageObjects.inputForms;

import java.awt.AWTException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.CommonFunctionalities;
import utilities.wrapperedActions.SelectList;
import utilities.wrapperedActions.ClickActions;

import utilities.LoadObjectRepository;

public class SelectDropDownPage 
{
	private InputStream objectFile;
	private Properties prop;
	
	private String repository_path = "D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\src\\main\\java\\Object Repository\\InputForm_SelectDropDown_Constants.properties";
	private String select_list_dropdown_id;
	private String select_multi_list_dropdown_id;
	private String get_all_selected_button_id;
	private String get_all_selected_label_class;
	private String page_title_text_xpath;
	
	private String page_title_text;
	
	private WebDriver driver;
	private ExtentTest test;
	
	public SelectDropDownPage(WebDriver driver, ExtentTest test) throws Exception
	{
		this.driver = driver;
		this.test = test;
		
		LoadObjectRepository obj = new LoadObjectRepository(repository_path);
		objectFile = obj.file;
		prop = new Properties();
		prop.load(objectFile);
		
		select_list_dropdown_id = prop.getProperty("select_demo_list_id");
		select_multi_list_dropdown_id = prop.getProperty("select_multi_list_id");
		get_all_selected_button_id = prop.getProperty("get_all_selected_button_id");
		get_all_selected_label_class = prop.getProperty("get_all_selected_label_class");
		page_title_text_xpath = prop.getProperty("page_title_xpath");
		page_title_text = prop.getProperty("page_title_text");
	}
	
	public String getPageTitle()
	{
		return CommonFunctionalities.getText(driver, "xpath", page_title_text_xpath);
	}
	
	public void selectDemoList(String listItemText)
	{
		SelectList.Select(driver, "id", select_list_dropdown_id, listItemText);
		test.log(LogStatus.INFO, "Dropdown item is selected.");
	}
	
	public void selectDemoMultiList(List<String> dropdownValues)
	{
		try 
		{
			SelectList.MultiSelect(driver, "id", select_multi_list_dropdown_id, dropdownValues);
		} 
		catch (AWTException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, "Dropdown items are selected.");
	}
	
	public void clickGetAllSelectedButton()
	{
		ClickActions.click(driver, "id", get_all_selected_button_id);
		test.log(LogStatus.INFO, "Get All Button is clicked.");
	}
	
	public String getLabelTextDisplayed_MultiList()
	{
		return CommonFunctionalities.getText(driver, "class", get_all_selected_label_class);
	}
	
	public void verify_DisplayedLabelTextContains_SelectedValues_MultiList(String displayedText, List<String> selectedValues)
	{
		boolean check = false;
		for(String value : selectedValues)
		{
			if(displayedText.contains(value))
			{
				check = true;
			}
			else
			{
				check = false;
			}
		}
		
		if(check = true)
		{
			test.log(LogStatus.INFO, "The selected dropdown values are present in the displayed label.");
		}
		else
		{
			test.log(LogStatus.FAIL, "The selected dropdown values are not present in the displayed label.");
		}
	}
}

package pageObjects.homePage;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.*;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseClass.BaseClassDef;
import suiteSetup.homepage.Setup_ObjectRepository;
import utilities.FindElementMethods;
import utilities.wrapperedActions.ClickActions;

public class HomePage extends FindElementMethods
{
	private String repository_path = "D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\src\\main\\java\\Object Repository\\HomePage_Constants.properties";
	private String inputForm_link_xpath;
	private String simpleForm_link_xpath;
	private String select_dropdown_list_xpath;
	private WebDriver driver;
	private ExtentTest test;
	private static Logger logger = LogManager.getLogger(HomePage.class.getName());
	
	public HomePage(WebDriver driver, ExtentTest test) throws Exception
	{
		this.driver = driver;
		this.test = test;
		//this.logger = logger;
		Setup_ObjectRepository obj = new Setup_ObjectRepository();
		obj.setVariables(repository_path);
		inputForm_link_xpath = obj.inputForm_link_xpath;
		simpleForm_link_xpath = obj.simpleForm_link_xpath;
		select_dropdown_list_xpath = obj.select_dropdown_list_xpath;
	}
	public void clickInputFormsLink() 
	{
		try
		{
			ClickActions.click(driver, "xpath", inputForm_link_xpath);
			test.log(LogStatus.INFO, "Input form link is clicked.", "Step information");
		}
		catch(Exception e)
		{
			String error = e.toString();
			test.log(LogStatus.FAIL, "Input form link is not clicked due to error: "+error, "Step information");
			//logger.error("Error occured in Input form click", error);
		}
	}
	
	public void simpleFormLinkLocator()
	{
		ClickActions.click(driver, "xpath", simpleForm_link_xpath);
		test.log(LogStatus.INFO, "Simple form link is clicked.", "Step information");
	}
	
	public void clickSelectDropDownListLink()
	{
		ClickActions.click(driver, "xpath", select_dropdown_list_xpath);
		test.log(LogStatus.INFO, "Select dropdown list link is clicked.", "Step information");
	}
}

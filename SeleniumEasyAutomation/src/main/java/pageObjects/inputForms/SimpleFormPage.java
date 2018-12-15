package pageObjects.inputForms;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import suiteSetup.inputForms.Setup_ObjectRepository;
import utilities.CommonFunctionalities;
import utilities.FindElementMethods;
import utilities.LoadObjectRepository;
import utilities.wrapperedActions.ClickActions;
import utilities.wrapperedActions.InputActions;

public class SimpleFormPage extends FindElementMethods
{
	
	private InputStream objectFile;
	private Properties prop;
	
	private String repository_path = "D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\src\\main\\java\\Object Repository\\InputForm_SimpleForm_Constants.properties";
	private String page_title_css;
	private String block_1st_title_css;
	private String block_1st_Show_button_xpath;
	private String block_1st_bullet_list_xpath;
	private String block_1st_show_message_btn_xpath;
	private String block_1st_enter_msg_box_xpath;
	private String block_1st_msg_display_label_xpath;
	private String block_2nd_enter_a_input_box_id;
	private String block_2nd_enter_b_input_box_id;
	private String block_2nd_get_total_btn_xpath;
	private String block_2nd_sum_display_label_id;
	
	private String page_title_text;
	private String block_1st_title_text;
	
	private WebDriver driver;
	private ExtentTest test;
	
	public SimpleFormPage(WebDriver driver, ExtentTest test) throws Exception
	{
		this.driver = driver;
		this.test = test;
		
		LoadObjectRepository obj = new LoadObjectRepository(repository_path);
		objectFile = obj.file;
		prop = new Properties();
		prop.load(objectFile);
		
		//Setup_ObjectRepository obj = new Setup_ObjectRepository(repository_path);
		//obj.setVariables(repository_path);
		page_title_css = prop.getProperty("page_title_css");
		block_1st_title_css = prop.getProperty("1st_block_title_css");
		block_1st_Show_button_xpath = prop.getProperty("1st_block_Show_button_xpath");
		block_1st_bullet_list_xpath = prop.getProperty("1st_block_bullet_list_xpath");
		block_1st_show_message_btn_xpath = prop.getProperty("1st_block_show_message_btn_xpath");
		block_1st_enter_msg_box_xpath = prop.getProperty("1st_block_enter_msg_box_xpath");
		block_1st_msg_display_label_xpath = prop.getProperty("1st_block_msg_display_label_xpath");
		block_2nd_enter_a_input_box_id = prop.getProperty("2nd_block_enter_a_input_box_id");
		block_2nd_enter_b_input_box_id = prop.getProperty("2nd_block_enter_b_input_box_id");
		block_2nd_get_total_btn_xpath = prop.getProperty("2nd_block_get_total_btn_xpath");
		block_2nd_sum_display_label_id = prop.getProperty("2nd_block_sum_display_label_id");
	}
	
	
	public String getpageTitleLocator()
	{
		return page_title_css;
	}
	
	public String get1stBlockTitleLocator()
	{
		return block_1st_title_css;
	}
	
	public String get1stBlockButtonLocator()
	{
		return block_1st_Show_button_xpath;
	}
	
	public String get1stBlockBulletListLocator()
	{
		return block_1st_bullet_list_xpath;
	}
	
	public String getPageTitle()
	{
		return CommonFunctionalities.getText(driver, "css", page_title_css);
	}
	
	public void enter1stBlockEnterMessageBox(String input)
	{
		InputActions.enterText(driver, "xpath", block_1st_enter_msg_box_xpath, input);
		test.log(LogStatus.INFO, "Text is entered in the Message box.", "Step Information");
	}
	
	public void click1stBlockShowMessageBtn()
	{
		ClickActions.click(driver, "xpath", block_1st_show_message_btn_xpath);
		test.log(LogStatus.INFO, "\"Show Message\" button is clicked.", "Step Information");
	}
	
	public String get1stBlockMessageDisplayLabelText()
	{
		String enteredMsgDisplay = CommonFunctionalities.getText(driver, "xpath", block_1st_msg_display_label_xpath);
		test.log(LogStatus.INFO, "Entered message in the Message box is displayed.", "Step Information");
		return enteredMsgDisplay;
	}
	
	public void enter2ndBlockEnterValueBox(String a, String b)
	{
		InputActions.enterText(driver, "id", block_2nd_enter_a_input_box_id, a);
		test.log(LogStatus.INFO, "Value to \"a\" box is entered.", "Step Information");
		InputActions.enterText(driver, "id", block_2nd_enter_b_input_box_id, b);
		test.log(LogStatus.INFO, "Value to \"b\" box is entered.", "Step Information");
	}
	
	public void click2ndBlockGetTotalBtn()
	{
		ClickActions.click(driver, "xpath", block_2nd_get_total_btn_xpath);
		test.log(LogStatus.INFO, "Get Total button is clicked.", "Step Information");
	}
	
	public String get2ndBlockMessageDisplayLabelText()
	{
		String displayedSum = CommonFunctionalities.getText(driver, "id", block_2nd_sum_display_label_id);
		test.log(LogStatus.INFO, "Sum of a and b is displayed.", "Step Information");
		return displayedSum;
	}
	
}

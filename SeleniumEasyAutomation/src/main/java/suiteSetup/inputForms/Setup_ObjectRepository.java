package suiteSetup.inputForms;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import utilities.LoadObjectRepository;

public class Setup_ObjectRepository 
{
	InputStream objectFile;
	Properties prop;
	public String page_title_css;
	public String block_1st_title_css;
	public String block_1st_Show_button_xpath;
	public String block_1st_bullet_list_xpath;
	public String block_1st_show_message_btn_xpath;
	public String block_1st_enter_msg_box_xpath;
	public String block_1st_msg_display_label_xpath;
	public String block_2nd_enter_a_input_box_id;
	public String block_2nd_enter_b_input_box_id;
	public String block_2nd_get_total_btn_xpath;
	public String block_2nd_sum_display_label_id;
	
	public String page_title_text;
	public String block_1st_title_text;
	public String ExcelPath;
	public String workbookSheetName;
	public String TestCaseColumnName;
	
	public Setup_ObjectRepository(String path) throws Exception
	{
		LoadObjectRepository obj = new LoadObjectRepository(path);
		objectFile = obj.file;
		prop = new Properties();
		prop.load(objectFile);
		//Get all page elements
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
		
		//Get text
		page_title_text = prop.getProperty("page_title_text");
		block_1st_title_text = prop.getProperty("1st_block_title_text");
		
		//Get testdata file path
		ExcelPath = prop.getProperty("path");
		workbookSheetName = prop.getProperty("Sheet_name");
		
		//Get Column Header
		TestCaseColumnName = prop.getProperty("TestCase_col_name");
	}
}

package suiteSetup.inputForms;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import utilities.ExcelData;
import utilities.LoadObjectRepository;

public class GetSimpleFormTestData 
{
	InputStream objectFile;
	public String path = "D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\src\\main\\java\\Object Repository\\InputForm_SimpleForm_Constants.properties";
	Properties prop;
	HashMap<String, String> data;
	public String testcase_col_name;
	public String activity_box_title;
	public String enter_a;
	public String enter_b;
	public String page_title;
	public String bullet_point_text;
	public String message_box_input_text;
	
	private String excelPath;
	private String excelSheetName;
	
	public void SetTestData(String testname) throws Exception
	{
		LoadObjectRepository Repobj = new LoadObjectRepository(path);
		objectFile = Repobj.file;
		prop = new Properties();
		prop.load(objectFile);
		//Testdata Column names variables
		testcase_col_name = prop.getProperty("TestCase_col_name");
		activity_box_title = prop.getProperty("activity_box_title");
		enter_a = prop.getProperty("enter_a");
		enter_b = prop.getProperty("enter_b");
		page_title = prop.getProperty("page_title");
		bullet_point_text = prop.getProperty("bullet_point_text");
		message_box_input_text = prop.getProperty("message_box_input_text");
		
		//Set Excel sheet path and sheet name
		excelPath = prop.getProperty("path");
		excelSheetName = prop.getProperty("Sheet_name");
		
		ExcelData Exceobj = new ExcelData(excelPath, excelSheetName, testcase_col_name);
		data = Exceobj.getData(testname);
		
		HashMap<String, String> testData = data;
		Set<Entry<String, String>> pairs = testData.entrySet();
		
		for(Entry<String, String> entry: pairs)
		{
			if(activity_box_title.equals(entry.getKey()))
			{
				activity_box_title = entry.getValue();
			}
			else if(enter_a.equals(entry.getKey()))
			{
				enter_a = entry.getValue();
			}
			else if(enter_b.equals(entry.getKey()))
			{
				enter_b = entry.getValue();
			}
			else if(page_title.equals(entry.getKey()))
			{
				page_title = entry.getValue();
			}
			else if(bullet_point_text.equals(entry.getKey()))
			{
				bullet_point_text = entry.getValue();
			}
			else if(message_box_input_text.equals(entry.getKey()))
			{
				message_box_input_text = entry.getValue();
			}
		}
	}
}
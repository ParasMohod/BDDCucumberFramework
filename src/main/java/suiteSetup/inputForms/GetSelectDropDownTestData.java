package suiteSetup.inputForms;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import utilities.ExcelData;
import utilities.LoadObjectRepository;

public class GetSelectDropDownTestData 
{
	InputStream objectFile;
	private String path = "D:\\NK\\Selenium Projects\\SeleniumEasyAutomation\\src\\main\\java\\Object Repository\\InputForm_SelectDropDown_Constants.properties";
	Properties prop;
	public HashMap<String, String> data;
	public String testcase_col_name;
	public String select_list_demo;
	public String multi_select_list1;
	public String multi_select_list2;
	public String multi_select_list3;
	public String multi_select_list4;
	public String multi_select_list5;
	public String multi_select_list6;
	public String multi_select_list7;
	public String multi_select_list8;
	public String page_title_text;
	
	private String excelPath;
	private String excelSheetName;
	
	public void SetTestData(String testname) throws Exception
	{
		LoadObjectRepository Repobj = new LoadObjectRepository(path);
		objectFile = Repobj.file;
		prop = new Properties();
		prop.load(objectFile);
		//Testdata Column names variables
		testcase_col_name = testname;
		select_list_demo = prop.getProperty("select_list");
		multi_select_list1 = prop.getProperty("multi_select_list1");
		multi_select_list2 = prop.getProperty("multi_select_list2");
		multi_select_list3 = prop.getProperty("multi_select_list3");
		multi_select_list4 = prop.getProperty("multi_select_list4");
		multi_select_list5 = prop.getProperty("multi_select_list5");
		multi_select_list6 = prop.getProperty("multi_select_list6");
		multi_select_list7 = prop.getProperty("multi_select_list7");
		multi_select_list8 = prop.getProperty("multi_select_list8");
		
		//Get text on the page
		page_title_text = prop.getProperty("page_title_text");
		
		//Set Excel sheet path and sheet name
		excelPath = prop.getProperty("path");
		excelSheetName = prop.getProperty("Sheet_name");
				
		ExcelData Exceobj = new ExcelData(excelPath, excelSheetName, testcase_col_name);
		data = Exceobj.getData(testname);
		
		HashMap<String, String> testData = data;
		Set<Entry<String, String>> pairs = testData.entrySet();
		
		for(Map.Entry<String, String> entry: pairs)
		{
			if(select_list_demo.equals(entry.getKey()))
			{
				select_list_demo = entry.getValue();
			}
			else if(multi_select_list1.equals(entry.getKey()))
			{
				multi_select_list1 = entry.getValue();
			}
			else if(multi_select_list2.equals(entry.getKey()))
			{
				multi_select_list2 = entry.getValue();
			}
			else if(multi_select_list3.equals(entry.getKey()))
			{
				multi_select_list3 = entry.getValue();
			}
			else if(multi_select_list4.equals(entry.getKey()))
			{
				multi_select_list4 = entry.getValue();
			}
			else if(multi_select_list5.equals(entry.getKey()))
			{
				multi_select_list5 = entry.getValue();
			}
			else if(multi_select_list6.equals(entry.getKey()))
			{
				multi_select_list6 = entry.getValue();
			}
			else if(multi_select_list7.equals(entry.getKey()))
			{
				multi_select_list7 = entry.getValue();
			}
			else if(multi_select_list8.equals(entry.getKey()))
			{
				multi_select_list8 = entry.getValue();
			}
		}
	}
	
	public List<String> getMultiDropDownValue(HashMap<String, String> testdata)
	{
		Set<Entry<String, String>> entrySet = testdata.entrySet();
		List<String> dropdownValues = new ArrayList<String>();
		for(Map.Entry<String, String> entry : entrySet)
		{
			if(!entry.getKey().equals("Select List Demo") && !entry.getKey().equals("TestCase"))
			{
				dropdownValues.add(entry.getValue());
			}
		}
		return dropdownValues;
	}
}

package suiteSetup.homepage;

import java.io.InputStream;
import java.util.Properties;

import utilities.LoadObjectRepository;

public class Setup_ObjectRepository 
{
	InputStream objectFile;
	Properties prop;
	
	public String inputForm_link_xpath;
	public String simpleForm_link_xpath;
	public String select_dropdown_list_xpath;
	
	public void setVariables(String path) throws Exception
	{
		LoadObjectRepository obj = new LoadObjectRepository(path);
		objectFile = obj.file;
		prop = new Properties();
		prop.load(objectFile);
		
		//Get All page elements
		inputForm_link_xpath = prop.getProperty("inputForm_link_xpath");
		simpleForm_link_xpath = prop.getProperty("simpleForm_link_xpath");
		select_dropdown_list_xpath = prop.getProperty("selectDropdownList_link_xpath");
	}
	
}

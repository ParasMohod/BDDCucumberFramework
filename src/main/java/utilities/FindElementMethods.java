package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseClassDef;

public class FindElementMethods
{	
	public static By getelementbytype(String type,String locator)
	{
		switch(type)
		{
			case "id" : return By.id(locator);
			case "name" : return By.name(locator);
			case "class" : return By.className(locator);
			case "xpath" : return By.xpath(locator);
			case "css" : return By.cssSelector(locator);
			case "linkText" : return By.linkText(locator);
			case "partialLinkText" : return By.partialLinkText(locator);
			case "tagName" : return By.tagName(locator);
		default : return null;				
		}
	}
}

package utilities.wrapperedActions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utilities.FindElementMethods;
import utilities.JavascriptFunctions;
import utilities.WebDriverWaitMethods;

public class SelectList extends FindElementMethods
{	
	private static WebElement element=null;
	private static Actions action;
	private static Select select;
	
	public static void 	Select(WebDriver driver, String by, String locator, int index)
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		WebDriverWaitMethods.elementToBeClickable(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	public static void 	Select(WebDriver driver, String by, String locator, String text)
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		WebDriverWaitMethods.elementToBeClickable(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		
		select = new Select(element);
		try
		{
			select.selectByVisibleText(text);
		}
		catch(Exception e)
		{
			//Exception when it is not selected by visible text
		}
		finally
		{
			select.selectByValue(text);
		}
	}
	
	public static void MultiSelect(WebDriver driver, String by, String locator, List<String> dropdownValues) throws AWTException
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		WebDriverWaitMethods.elementToBeClickable(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		
		Robot robot = new Robot();
		
		
		select = new Select(element);
		
		for(String value : dropdownValues)
		{
			robot.keyPress(KeyEvent.VK_CONTROL); 
			select.selectByVisibleText(value);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		
		
		
	}
}

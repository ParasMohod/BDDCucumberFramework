package utilities.wrapperedActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utilities.FindElementMethods;
import utilities.JavascriptFunctions;
import utilities.WebDriverWaitMethods;

public class ClickActions extends FindElementMethods
{
	private static WebElement element=null;
	private static Actions action;
	
	public static void click(WebDriver driver, String by, String locator)
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		WebDriverWaitMethods.elementToBeClickable(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		element.click();
	}
	
	public static void doubleClick(WebDriver driver, String by, String locator)
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		WebDriverWaitMethods.elementToBeClickable(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		action = new Actions(driver);
		action.doubleClick();
	}
}

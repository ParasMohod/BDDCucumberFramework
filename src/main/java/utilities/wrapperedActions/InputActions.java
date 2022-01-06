package utilities.wrapperedActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.FindElementMethods;
import utilities.WebDriverWaitMethods;

public class InputActions extends FindElementMethods
{
	private static WebElement element=null;
	
	public static void enterText(WebDriver driver, String by, String locator, String input)
	{
		WebDriverWaitMethods.presenceOfElement(driver, by, locator);
		element = driver.findElement(getelementbytype(by, locator));
		element.sendKeys(input);
	}
}

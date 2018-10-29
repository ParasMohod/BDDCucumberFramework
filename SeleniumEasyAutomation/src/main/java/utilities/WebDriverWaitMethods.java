package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitMethods extends FindElementMethods
{
	public static WebDriverWait wait;
	
	public static void visibilityOfElementLocatedBy(WebDriver driver, String by , String element)
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getelementbytype(by, element)));
	}
	
	public static void elementToBeClickable(WebDriver driver, String by , String element)
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(getelementbytype(by, element)));
	}
	
	public static void presenceOfElement(WebDriver driver, String by, String element)
	{
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(by, element)));
	}
}

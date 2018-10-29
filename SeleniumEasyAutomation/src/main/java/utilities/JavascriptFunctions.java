package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptFunctions
{
	static JavascriptExecutor js;
	
	public static void jsClick(WebDriver driver, WebElement element)
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void jsScrollIntoView(WebDriver driver, WebElement element)
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
}

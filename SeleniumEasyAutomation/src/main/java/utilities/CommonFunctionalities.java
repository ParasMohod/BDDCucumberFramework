package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utilities.FindElementMethods;

public class CommonFunctionalities extends FindElementMethods
{
	private static WebElement element = null; 
	public List<String> getMultipleBulletText(WebDriver driver, String locator)
	{
		WebDriverWaitMethods.presenceOfElement(driver, "xpath", locator);
		List<WebElement> BulletList = driver.findElements(getelementbytype("xpath", locator));
		List<String> BulletTextContent = new ArrayList<String>();
		int countBullets = BulletList.size();
		for(int i=1; i<=countBullets; i++)
		{
			String actualLocator = locator + "["+i+"]";
			element = driver.findElement(getelementbytype("xpath", actualLocator));
			JavascriptFunctions.jsScrollIntoView(driver, element);
			String bulletText = element.getText().trim();
			BulletTextContent.add(bulletText);
		}
		return BulletTextContent;
	}
	
	public void verifyBulletText(WebDriver driver, String locator, String expected) 
	{
		CommonFunctionalities function = new CommonFunctionalities();
		List<String> actualBulletText = function.getMultipleBulletText(driver, locator);
		
		for(int i=0; i<actualBulletText.size(); i++)
		{
			int BulletNo = i+1;
			if(expected.contains(actualBulletText.get(i)))
			{
				System.out.println("Number "+ BulletNo +" Bullet Text is correct: "+actualBulletText.get(i));
			}
			else
			{
				System.out.println("Number "+ BulletNo +" Bullet Text is incorrect: "+actualBulletText.get(i));
				function.verifyText(actualBulletText.get(i), expected, BulletNo+": Bullet text is incorrect");
			}
		}
	}
	
	public void verifyText(String actual, String expected, String Message)
	{
		Assert.assertEquals(actual, expected, Message);
	}
	
	public static String getText(WebDriver driver, String By, String locator) 
	{
		WebDriverWaitMethods.presenceOfElement(driver, By, locator);
		element = driver.findElement(getelementbytype(By, locator));
		JavascriptFunctions.jsScrollIntoView(driver, element);
		return element.getText();
	}
	
	public String sumOfNumbers(String... values)
	{
		int sum = 0;
		String total=null;
		for(String str : values)
		{
			int num = (int) Double.parseDouble(str);
			sum = sum + num;
		}
		total = Integer.toString(sum);
		return total.trim();
	}
		
}

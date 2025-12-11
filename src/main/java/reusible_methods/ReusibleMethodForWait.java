package reusible_methods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReusibleMethodForWait{
	
	public static boolean elementPresent(WebDriver driver,By element)
	{
		//implicitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		try
		{
			driver.findElement(element);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}

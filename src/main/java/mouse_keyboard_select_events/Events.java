package mouse_keyboard_select_events;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Events{
	
	//actions class
	public static Actions actions(WebDriver driver)
	{
		 return new Actions(driver);
	}
	
	//select class
	public static Select select(WebElement element)
	{
		return new Select(element);
	}
	
}

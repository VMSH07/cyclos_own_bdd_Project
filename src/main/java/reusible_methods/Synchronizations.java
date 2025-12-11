package reusible_methods;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronizations {
	
	//explicitly wait
	public static<T> T explictly(WebDriver driver,ExpectedCondition<T> condition)
	{
		WebDriverWait ww=new WebDriverWait(driver,Duration.ofSeconds(10));
		return ww.until(condition);
	}
	
	//fluient wait
	public static<T> T  fluentSingle(WebDriver driver,ExpectedCondition<T> condition)
	{   
		Wait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(15))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class)
	            .ignoring(StaleElementReferenceException.class)
	            .ignoring(ElementClickInterceptedException.class);

	    return wait.until(condition);
	}

}

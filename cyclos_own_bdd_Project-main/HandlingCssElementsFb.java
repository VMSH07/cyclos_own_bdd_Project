package package2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class HandlingCssElementsFb {
	WebDriver dobj;
	@BeforeMethod
	public void sbilogin() 
	{
		// launch Browser
		 dobj=new FirefoxDriver();
	}
	@Test
	public void elementsCss()
	{
		// Load Web page
		dobj.get("https://retail.onlinesbi.sbi/retail/login.htm");
		//Click on login Button
		dobj.findElement(By.cssSelector(".continue_btn a[class=\"login_button\"]")).click();
		//locate user name by css
		dobj.findElement(By.cssSelector("input[name=\"userName\"]")).sendKeys("Test the thing");
		//locate password by css
		dobj.findElement(By.cssSelector(".form-group input[title=\"password\"]")).sendKeys("Test Password");
		//locate text box of image
		dobj.findElement(By.cssSelector("#loginCaptchaValue")).sendKeys("p66rh");
		//locate login Button
		dobj.findElement(By.cssSelector("input[onclick*=\"('ntc6RmC6');\"]")).click();
		//locate reset Button
		dobj.findElement(By.cssSelector("input[onclick*=\"(false)\"")).click();
		//
	}

}

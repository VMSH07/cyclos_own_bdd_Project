package cyclos_config_files;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;

import excel_utils.ReadAndSetData;

public class CyclosDriverClass {
	
	//Initialize webdriver instantly
	public static WebDriver d;
	 
	//instance object creation to read data from sheet
	public ReadAndSetData rsd;
	
	//Read data from login sheet
	String excel1="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\CyclonLogin.xlsx";
	
	//partial initializing browser it test method is created
	static String browser="chrome";
	
	//@Parameters("browser")
	//@BeforeTest
	//method to set up browser and application
	public static void openBrowser() throws Exception
	{
		if(browser.equals("chrome"))
		{	
		  //open chrome browser
		  d=new ChromeDriver();
			
		  //Remote execution chrome
		  //d=new RemoteWebDriver(new URL ("http://20.244.45.161:4444/wd/hub"), new ChromeOptions());
		}
		else if(browser.equals("ff"))
		{
			//open chrome browser
			 d=new FirefoxDriver();
			
			//Remote execution fire fox
			//d=new RemoteWebDriver(new URL ("http://20.244.45.161:4444/wd/hub"), new FirefoxOptions());
		}
		else if(browser.equals("edge"))
		{
			//open chrome browser
			d=new EdgeDriver();
			
			//Remote execution edge
			//d=new RemoteWebDriver(new URL ("http://20.244.45.161:4444/wd/hub"), new EdgeOptions());
		}
		
		//maximize window
		d.manage().window().maximize();
		
		//set page load time out
		//d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		//set implicitly wait
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//delete all cookies
		d.manage().deleteAllCookies();
		
		//open application
		d.get("https://demo.cyclos.org/ui/home");
		
		//verify page title
		//assertTrue(d.getTitle().contains("Home"));
	}
	
	//@AfterTest
	//method to close app after run
	public static void close()
	{
		//quit method to close 
		d.quit();
	}

}

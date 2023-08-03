package package2;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


public class sbiweb {
         WebDriver Bank;
         @BeforeMethod
         public void start()
         {
           //opening browser
    	   Bank=new FirefoxDriver();
    	   //page time out sunc
    	   Bank.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    	   //implicit Wait
    	   Bank.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
         }
         @Test
         public void steps()
         {
        	//loading application
     		Bank.get("https://retail.onlinesbi.sbi/retail/login.htm");
     		// Click on Continue to login
     		Bank.findElement(By.linkText("CONTINUE TO LOGIN")).click();
     		//enter User name
     		Bank.findElement(By.id("username")).sendKeys("Harsha");
     		//enter  password
     		Bank.findElement(By.name("password")).sendKeys("123456");
     		//Enter Text as shown in image
     		Bank.findElement(By.xpath("//input[@name=\"loginCaptchaValue\"]")).sendKeys("ped34");
     		// Select first radio button
     		assertTrue(Bank.findElement(By.id("capOption")).isSelected());
     		// select second radio button
     		Bank.findElement(By.xpath("//div[@id='captchaOptionDisplay']/label[3]/input")).click();
     		assertTrue(Bank.findElement(By.xpath("//div[@id='captchaOptionDisplay']/label[3]/input")).isSelected());
     		// Click Audio Play Button
     		Bank.findElement(By.id("loginAudioCaptcha")).click();
     		// Click new user register link
     		//Bank.findElement(By.linkText("New User ? Register here/Activate")).click();
     		//click Forgot User name / Login Password
     		//Bank.findElement(By.linkText("Forgot Username / Login Password")).click();
     		//Enable Virtual keyBoard
     		Bank.findElement(By.id("chkbox")).click();
     		assertTrue(Bank.findElement(By.id("chkbox")).isSelected());
     		//click on reset button
     		Bank.findElement(By.id("reset_btn")).click();
         }
         @AfterTest
         public void logof()
         {
        	 Bank.quit();
         }
    		 
        	 

		
		
		
		
		
		
		
		
		
		
		

	

}

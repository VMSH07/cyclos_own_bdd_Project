package definitionsteps;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import configrations.DriverClass;
import io.cucumber.java.en.*;
import pages.ObjectRepostry;
import pages.PayUserModule;

public class PayUserfun extends DriverClass {
	ObjectRepostry or;
	PayUserModule pum;
	@Given("User is successfully loggedin with the data {string} {string} and is on Dashboard page")
	public void user_is_successfully_loggedin_with_the_data_and_is_on_dashboard_page(String username, String password) throws Exception {
		//Load web page
		d.get(url);
		//Creating object for object repostry and PayUserModuleclass
		or=new ObjectRepostry(d);
		pum=new PayUserModule(d);
		//login data
		or.logindata(username, password);
   }
	@When("User clicks on pay user link")
	public void user_clicks_on_pay_user_link() {
	    //Click on Pay user link
		pum.lnkpayuser.click();
	}
	@When("User is navigated to payment page and types {string} and clicks on demo one suggestion")
	public void user_is_navigated_to_payment_page_and_types_and_clicks_on_demo_one_suggestion(String contact) {
		//Type value for suggestion  
	   pum.txtcontacts.sendKeys(contact);
	    //click suggestion
	    pum.lnkdemoone.click();
	} 
	@When("User enters {string} amount and clicks on next button")
	public void user_enters_amount_and_clicks_on_next_button(String amount) {
		//Type amount
		pum.txtamount.sendKeys(amount);
		//click on next
		pum.btnnext.click();
	}
	@When("User is navigated to Payment confirmation page and click on confirm button")
	public void user_is_navigated_to_payment_confirmation_page_and_click_on_confirm_button() throws Exception {
		//click on conform
		pum.btnconform.click();
		Thread.sleep(2000);
	}
	@Then("Verify Payment successfull message")
	public void verify_payment_successfull_message() {
		//local varible for success message
		WebElement paysuccess=pum.msgpaysuccess;
		if(paysuccess.equals(pum.msgpaysuccess))
		{
	    //Verify payment success message
		assertTrue(pum.msgpaysuccess.isDisplayed());
		//close browser
		//d.quit();
		}
		else
		{
			//alart
			Alert a=d.switchTo().alert();
			a.dismiss();
		}
	}
	
}

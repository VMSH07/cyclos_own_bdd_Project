package definations_classes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mouse_keyboard_select_events.Events;
import reusible_methods.ReusibleMethodForWait;
import reusible_methods.Synchronizations;


public class LoginPositiveAndNegitive extends CyclosDriverClass{
	
	//login page class
	static CyclosLoginLogoutPage clp;
	
	//store value sent to text field in to variable 
	String user; String password;

	@Given("user click on login from home page.")
	public void user_click_on_login_from_home_page() throws Exception {
		
		//login page class
	    clp=new CyclosLoginLogoutPage(d);
				
		//click on login button
		clp.btnLogin.click();
				
		//verify title of the page
		assertTrue(d.getTitle().contains("Login"));
	}
	
	@When("User enters valid\\/invalid\\/blank {string} and valid\\/invalid\\/blank {string}.")
	public void user_enters_valid_invalid_blank_and_valid_invalid_blank(String Uname, String pass) {
					
		//email text field and send values
		clp.txtUserName.sendKeys(Uname);
				
		//store value sent to text field in to variable by using getAttribute() method.
		user=Uname;
				
		//password field and send value
		clp.txtPassword.sendKeys(pass);
				
		//store value sent to text field in to variable by using getAttribute() method.
		password=pass;
				
	}
	@And("User clicks on submit button.")
	public void user_clicks_on_submit_button() {
		
		//click on submit
		clp.btnSubmit.click();
		
	}
	@Then("User verifies validation messages for negative cases and verify logout button presence for positive case.")
	public void user_verifies_validation_messages_for_negative_cases_and_verify_logout_button_presence_for_positive_case() {
	    
/*conditions to execute positive and negitive cases for login*/
		//User name and password are blank		
		if(user.equals("") && password.equals(""))
		{
			//verify error text for username
			assertEquals(clp.errUserName.getText(),"This field is required");
			
			//verify password error
			assertEquals(clp.errPassword.getText(),"This field is required");
			
		}
		
		//User name is blank and valid/invalid password	
		else if(user.equals("") && !(password.equals("")))
		{
			//verify error text for username
			assertEquals(clp.errUserName.getText(),"This field is required");
			
			//clear password using actions class
			Events.actions(d).keyDown(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,"a").keyUp(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,Keys.DELETE).build().perform();
			
		}
		
		//valid/invalid User name and password is blank	
		else if(!(user.equals("")) && password.equals(""))
		{
			//verify password error
			assertEquals(clp.errPassword.getText(),"This field is required");
			
			//clear user name by actions class
			Events.actions(d).keyDown(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,"a").keyUp(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,Keys.DELETE).build().perform();
			
		}
		
		//valid user name and password
		else  if(ReusibleMethodForWait.elementPresent(d, clp.btnLogOut))
		{
			//Log out 
			d.findElement(clp.btnLogOut).click();
			
			//Explicitly wait for element
			Synchronizations.explictly(d, ExpectedConditions.visibilityOf(clp.btnLogin));
			
			//click on login
			clp.btnLogin.click();
		}
		
/*invalid user name and invalid password OR valid user name and invalid password OR invalid user name and valid password*/
		else
		{
			//verify error popup and close
			assertEquals(clp.errNote.getText(),"The given name / password are incorrect. Please, try again.");
			
			//close popup
			clp.xNote.click();
			
			//clear password using actions class
			Events.actions(d).keyDown(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,"a").keyUp(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,Keys.DELETE).build().perform();
			//clear user name by actions class
            Events.actions(d).keyDown(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,"a").keyUp(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,Keys.DELETE).build().perform();
			
		}
		
	  }
}

package cyclos_pageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cyclos_config_files.CyclosDriverClass;



public class CyclosLoginLogoutPage{
	
	//login button
	@FindBy(id="login-link")public WebElement btnLogin;
	
	//user name text field
	@FindBy(css="input[autocomplete=\"username\"]")public WebElement txtUserName;
	
	//password text field
	@FindBy(xpath="//password-input/div/input")public WebElement txtPassword;
	
	//submit button
	@FindBy(xpath="//button[contains(.,'Submit')]")public WebElement btnSubmit;
	
	//User name error
	@FindBy(css=".principal .invalid-feedback")public WebElement errUserName;
	
	//password error
	@FindBy(css=".d-block .invalid-feedback")public WebElement errPassword;
	
	//logout button
	public By btnLogOut=By.id("logout-trigger");
	
	//error note message
	@FindBy(className="notification-message")public WebElement errNote;
	
	//close error note
	@FindBy(className="visually-hidden")public WebElement xNote;
	
	//constrictor to store pagefactory class inItElements method
	public CyclosLoginLogoutPage(WebDriver driver)
	{
		//page factory class and inItElements() method
		PageFactory.initElements(driver,this);
	}
	
	//login method
	public void cyclosLogin(WebDriver d,String uName,String password)
	{
		//click on login button
		btnLogin.click();
								
		//verify title of the page
		assertTrue(d.getTitle().contains("Login"));
																		
		//email text field and send values
		txtUserName.sendKeys(uName);
														
		//password field and send value
		txtPassword.sendKeys(password);
								
		//click on submit
		btnSubmit.click();
	}
	
	public void logout(WebDriver d)
	{
		//click on logout
		d.findElement(btnLogOut).click();
	}
}

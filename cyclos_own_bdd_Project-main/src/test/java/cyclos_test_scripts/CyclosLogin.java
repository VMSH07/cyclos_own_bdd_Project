package cyclos_test_scripts;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.*;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import excel_utils.ReadAndSetData;
import mouse_keyboard_select_events.Events;
import reusible_methods.ReusibleMethodForWait;
import reusible_methods.Synchronizations;

public class CyclosLogin extends CyclosDriverClass{
	
	//string variable to store excel path
	String excel="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\CyclonLogin.xlsx";
	//excel utils class
	ReadAndSetData re;
	
	@Test
	public void login() throws Exception
	{
		//login page class
		CyclosLoginLogoutPage clp=new CyclosLoginLogoutPage(d);
		
		//click on login button
		clp.btnLogin.click();
		
		//verify title of the page
		assertTrue(d.getTitle().contains("Login"));
		
/*Read data from excel*/
		re=new ReadAndSetData(excel,"Login Cases");
		
		//loop to iterate rows from excel
		for(int i=1;i<=re.getRows();i++)
		{
			
		//email text field and send values
		clp.txtUserName.sendKeys(re.getData(i,0));
		
		//store value sent to text field in to variable by using getAttribute() method.
		String uName=re.getData(i,0);
		
		//password field and send value
		clp.txtPassword.sendKeys(re.getData(i,1));
		
		//store value sent to text field in to variable by using getAttribute() method.
		String pass=re.getData(i,1);
		
		//click on submit
		clp.btnSubmit.click();
		
/*conditions to execute positive and negitive cases for login*/
		//User name and password are blank		
		if(uName.equals("") && pass.equals(""))
		{
			//verify error text for username
			assertEquals(clp.errUserName.getText(),"This field is required");
			
			//verify password error
			assertEquals(clp.errPassword.getText(),"This field is required");
			
			//set Pass || failed states
			re.setData(i, 2, "Fail", excel);
		}
		
		//User name is blank and valid/invalid password	
		else if(uName.equals("") && !(pass.equals("")))
		{
			//verify error text for username
			assertEquals(clp.errUserName.getText(),"This field is required");
			
			//clear password using actions class
			Events.actions(d).keyDown(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,"a").keyUp(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,Keys.DELETE).build().perform();
			
			//set Pass || failed states
			re.setData(i,2,"Fail",excel);
		}
		
		//valid/invalid User name and password is blank	
		else if(!(uName.equals("")) && pass.equals(""))
		{
			//verify password error
			assertEquals(clp.errPassword.getText(),"This field is required");
			
			//clear user name by actions class
			Events.actions(d).keyDown(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,"a").keyUp(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,Keys.DELETE).build().perform();
			
			//set Pass ||  states
			re.setData(i,2,"Fail",excel);
		}
		
		//valid user name and password
		else  if(ReusibleMethodForWait.elementPresent(d, By.id("logout-trigger")))
		{
			//Log out 
			d.findElement(clp.btnLogOut).click();
			
			//Explicitly wait for element
			Synchronizations.explictly(d, ExpectedConditions.visibilityOf(clp.btnLogin));
			
			//click on login
			clp.btnLogin.click();
			
			//set Pass || failed states
			re.setData(i,2,"pass",excel);
		}
		
/*invalid user name and invalid password OR valid user name and invalid password OR invalid user name and valid password*/
		else
		{
			//verify error popup and close
			assertEquals(clp.errNote.getText(),"The given name / password are incorrect. Please, try again.");
			
			//close popup
			clp.xNote.click();
			
			//clear password using actions class
			Events.actions(super.d).keyDown(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,"a").keyUp(clp.txtPassword,Keys.CONTROL).sendKeys(clp.txtPassword,Keys.DELETE).build().perform();
			//clear user name by actions class
            Events.actions(super.d).keyDown(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,"a").keyUp(clp.txtUserName,Keys.CONTROL).sendKeys(clp.txtUserName,Keys.DELETE).build().perform();
			
			//set Pass || failed states
			re.setData(i,2,"Fail",excel);
		}
	  }
		
	}
	
//	@Parameters("browser")
//	@BeforeTest
//	public void openCyclosWeb(String browser) throws Exception
//	{
//		openBrowser(browser);
//	}
//	
//	@AfterTest
//	public void close()
//	{
//	  //close browser method
//	  close();
//	} 

}

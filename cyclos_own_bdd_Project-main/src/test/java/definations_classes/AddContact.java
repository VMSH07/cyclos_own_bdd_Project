package definations_classes;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosContactsPage;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import excel_utils.ReadAndSetData;
import io.cucumber.java.en.*;
import reusible_methods.Synchronizations;

public class AddContact extends CyclosDriverClass{
	
	//instance variable to read data from directory sheet
	String excelLogin="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\CyclonLogin.xlsx";
		
    //instance variable to read data from directory sheet
	String excel="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\Directory_data.xlsx";
	
	CyclosContactsPage ccp;
	
	//abject of CyclosLoginLogoutPage
	static CyclosLoginLogoutPage clp;
	
	
	@Given("User logins succefully logins with valid user name {string} and password {string} .")
	public void user_logins_succefully_logins_with_valid_user_name_and_password(String user, String password) throws Exception {
		
		//object of CyclosLoginLogoutPage
		clp=new CyclosLoginLogoutPage(d);
						
		//call login method
		clp.cyclosLogin(d,user,password);
	}
	
	@When("User clicks on contacts.")
	public void user_clicks_on_contacts() {
	    
		ccp=new CyclosContactsPage(d);
		//click on contacts
		ccp.lnkContact.click();
		
	}
	
	@When("User navigates to contacts and clicks on add new button.")
	public void user_navigates_to_contacts_and_clicks_on_add_new_button() {
         
		ccp=new CyclosContactsPage(d);
		//verify contact list label on top
		assertEquals(ccp.lblContactList.getText(),"Contact list");
		
		//click on add new button
		ccp.btnAddNew.click();

	}
	
	@When("User searches for desired contact and selects from drop down.")
	public void user_searches_for_desired_contact_and_selects_from_drop_down() {
	    
		ccp=new CyclosContactsPage(d);
		
		//search for the contact
		ccp.txtContactSearch.sendKeys("Test");
		
		//select from dashboard
		List<WebElement> ddContacts= (List<WebElement>) ccp.ddContactSearch;
		
		//for each loop
		for(WebElement ddContact:ddContacts)
		{
//			if(ddContact.getText().equals("Testuser"))
//			{
				ddContact.click();
				break;
//			}
		}
	}
	
	@Then("User clicks on submit button and verifies success notifaction and contact added.")
	public void user_clicks_on_submit_button_and_verifies_success_notifaction_and_contact_added() throws Exception {
	   
		ccp=new CyclosContactsPage(d);
		
		//click on submit button
		ccp.btnSubmit.click();
		
		//fluent wait
		Synchronizations.fluentSingle(d, ExpectedConditions.elementToBeClickable(ccp.lnkDashboard));
		
		//click on dashboard
		ccp.lnkDashboard.click();
	}
}

package definations_classes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.ui.ExpectedConditions;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosDirectoryPage;
import io.cucumber.java.en.*;
import reusible_methods.Synchronizations;

public class DirectoryPayment extends CyclosDriverClass{
	
	//create variable to CyclosDirectoryPage
	static CyclosDirectoryPage cdp;
	//store contact details in variable
	String cName;
	
	@Given("User clicks on directory option from dashboard.")
	public void user_clicks_on_directory_option_from_dashboard() {
		//create variable to CyclosDirectoryPage
		cdp=new CyclosDirectoryPage(d);
				
		//verify presence of directory
		assertTrue(cdp.lnkDirectory.isDisplayed());
				
		//click on directory
		cdp.lnkDirectory.click();
				
		//verify presence of business directory
		assertTrue(cdp.lnkBusinessDirectory.isDisplayed());
	}
	
	@When("User searches for the contact from keywords text field {string} .")
	public void user_searches_for_the_contact_from_keywords_text_field(String keyWord) {
		//search keyword
		cdp.txtKeyWords.sendKeys(keyWord);
				
	}
	@When("User verfies the searched contact {string} and clicks on it.")
	public void user_verfies_the_searched_contact_and_clicks_on_it(String directoryContact) {
		//explicitly wait
		Synchronizations.explictly(d,ExpectedConditions.textToBePresentInElementLocated(cdp.contact,directoryContact));
						
		//verify contact name is same as searched name
		assertEquals(d.findElement(cdp.contact).getText(),directoryContact);
						
		//store contact details in variable
		cName=d.findElement(cdp.contact).getText();
						
		//Click on serached contact
		d.findElement(cdp.contact).click();
	}
	@When("User click on make payment link and navigates to payment to user and verify contact {string}.")
	public void user_click_on_make_payment_link_and_navigates_to_payment_to_user_and_verify_contact(String contact) {
		//verify make payment link
		assertTrue(cdp.lnkMakePayment.isDisplayed());
				
		//click on makePayment
		cdp.lnkMakePayment.click();
		
		//verify user name is same as selected contact name
		assertEquals(cdp.lblUser.getText(),cName);
	}
	@When("User enters amount {string} discription {string} and clicks on next button.")
	public void user_enters_amount_discription_and_clicks_on_next_button(String amount, String discription) {
		//Type ammount in to ammount text field
		cdp.txtAmount.sendKeys(amount);
				
		//set discription
		cdp.txtDiscription.sendKeys(discription);
				
		//click on next
		cdp.btnNext.click();
	}
	@When("User clicks on confirm button.")
	public void user_clicks_on_confirm_button() {
		//verify ammount to be sent
		assertTrue(cdp.lblAmount.isDisplayed());
				
		//click on conform
		cdp.btnConform.click();
	}
	@Then("User should be displayed with success message {string} or per day limit message {string}.")
	public void user_should_be_displayed_with_success_message_or_per_day_limit_message(String success, String limitMsg) {
		//if condition when success message shows
		if(cdp.notifaction.getText().equals(success))
		{
			assertEquals(cdp.notifaction.getText(),success);
					
			//click on new payment button
			cdp.btnNewPay.click();
		}
		//else condition when error shows up
		else
		{
			//explicitly wait
			Synchronizations.explictly(d,ExpectedConditions.textToBePresentInElement(cdp.notifaction,limitMsg));
					
			assertEquals(cdp.notifaction.getText(),limitMsg);
				
			//close alert
			cdp.clsNote.click();
		}
	}
}

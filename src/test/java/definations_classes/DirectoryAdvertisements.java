package definations_classes;

import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosDirectoryPage;
import io.cucumber.java.en.*;
import reusible_methods.Synchronizations;

public class DirectoryAdvertisements extends CyclosDriverClass{
	
	static CyclosDirectoryPage cdp;
	@When("User verifies and clicks on Advertisements.")
	public void user_verifies_and_clicks_on_advertisements() {
	    //initilize object
		cdp=new CyclosDirectoryPage(d);
		
		//verify text
		assertTrue(cdp.lnkAdvertisements.isDisplayed());
		
		//click on Advertisements
		cdp.lnkAdvertisements.click();
	}
	@When("User verfies and clicks on show advertisements button.")
	public void user_verfies_and_clicks_on_show_advertisements() {
	   
		//verify text
		assertTrue(cdp.btnShowadvertisements.isDisplayed());
				
		//click on show Show advertisements
		cdp.btnShowadvertisements.click();
		
	}
	@When("User searches for {string} verifies {string} advertisement and clicks on it.")
	public void user_searches_for_verifies_advertisement_and_clicks_on_it(String keyWord, String advertisement) {
		//search with keyword
		cdp.txtKeyWords.sendKeys(keyWord);
		
		//explicitly wait
		Synchronizations.explictly(d, ExpectedConditions.textToBePresentInElement(cdp.txtadvertisement, advertisement));
		
		//Verify searched advertisement
		assertEquals(cdp.txtadvertisement.getText(),advertisement);
		
		//click searched add
		cdp.txtadvertisement.click();
	}
	@When("User verifies title {string} and add to favorites.")
	public void user_verifies_title_and_add_to_favorites(String title) {
	   
		//verify title
		assertEquals(cdp.txtTitle.getText(),title);
		
		//click on add to fav
		cdp.btnAddOrRemoveFav.click();
	}
	@When("User verifies success message {string} and label change {string} .")
	public void user_verifies_success_message_and_label_change(String addNote, String removeLbl) {
		
		//explicitly wait
		Synchronizations.explictly(d, ExpectedConditions.textToBePresentInElement(cdp.noteTxt, addNote));
	   
		//Verify success message
		assertEquals(cdp.noteTxt.getText(),addNote);
		
		//verify label change for button
		assertEquals(cdp.btnAddOrRemoveFav.getText(),removeLbl);
	}
	@Then("User clicks on remove from favorites and verifies message {string} .")
	public void user_clicks_on_remove_from_favorites_and_verifies_message(String removeNote) {
	    
		//click on remove fav
		cdp.btnAddOrRemoveFav.click();
		
		//explicitly wait
		Synchronizations.explictly(d, ExpectedConditions.textToBePresentInElement(cdp.noteTxt, removeNote));
		
		//Verify success message
		assertEquals(cdp.noteTxt.getText(),removeNote);
		
		//click on dashboard
		cdp.lnkDashboard.click();
	}

}

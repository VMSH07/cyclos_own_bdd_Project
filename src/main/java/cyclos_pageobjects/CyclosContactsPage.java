package cyclos_pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusible_methods.Synchronizations;

public class CyclosContactsPage {
	
	//Contact link
	@FindBy(css=".quick-access-item-container:nth-child(3) > .quick-access-item")public WebElement lnkContact;
	
	//Verify contact list label
	@FindBy(xpath="//div[text()=' Contact list ']")public WebElement lblContactList;
	
	//add new button
	@FindBy(css="heading-action-button > .btn")public WebElement btnAddNew;
	
	//verify add new contact label from popup
	@FindBy(xpath="//h1[contains(.,'Add a new contact')]")public WebElement lblAddContact;
	
	//contact search text field
	@FindBy(css=".form-control")public WebElement txtContactSearch;
	
	//contact search suggestions
	@FindBy(css=".select-option")public List<WebElement> ddContactSearch;
	
	//Submit button
	@FindBy(css=".btn-primary:nth-child(1)")public WebElement btnSubmit;
	
	//added contact 
	@FindBy(css=".col-12:nth-child(1) .card-body")public WebElement lblContact;
	
	//dashboard
	@FindBy(id="menu_dashboard")public WebElement lnkDashboard;
	
	
	//constrictor to call PageFactory Class and inItElements method dynimicly
	public CyclosContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}

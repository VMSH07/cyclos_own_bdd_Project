package cyclos_pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cyclos_config_files.CyclosDriverClass;

public class CyclosDirectoryPage{
	
	//directory
	@FindBy(css="div .quick-access-container div[class^='quick-access-item-container']:nth-child(2)")public WebElement lnkDirectory;
	
	//business directory
	@FindBy(css=".card a[href=\"/ui/users/search\"]")public WebElement lnkBusinessDirectory;
	
	//text field keywords
	@FindBy(css=".label-value-container input[class^='form-control']")public WebElement txtKeyWords;
	
	//searched contact
	public By contact = By.cssSelector(".card-body:nth-child(2)");
	
	//link make payment
	@FindBy(xpath="//heading-action-button[1]/button[@class='btn']")public WebElement lnkMakePayment;
	
	//To user name
	@FindBy(css=".d-flex div[class=display-text]")public WebElement lblUser;
	
	//amount text field
	@FindBy(css=".d-flex input[class^=form-control]")public WebElement txtAmount;
	
	//set Discription
	@FindBy(css=".d-flex textarea[class^=form-control]")public WebElement txtDiscription;
	
	//next button
	@FindBy(css=".actions button[class^='btn']")public WebElement btnNext;
	
	//verify amount to be send
	@FindBy(css=".totalAmount div[class*='label-value-value']")public WebElement lblAmount;
	
	//conform button
	@FindBy(css=".btn-primary:nth-child(1)")public WebElement btnConform;
	
	//Notifaction
	@FindBy(className="notification-message")public WebElement notifaction;
	
	//new payment button
	@FindBy(css=".btn-primary:nth-child(1)")public WebElement btnNewPay;
	
	//close alert notifaction
	@FindBy(xpath="//button[@class=\"close btn-close\"]")public WebElement clsNote;
	
	//Link Advertisements
	@FindBy(css=".nav-item:nth-child(2) > .nav-item-text")public WebElement lnkAdvertisements;
	
	//Button Show advertisements
	@FindBy(css=".btn > span")public WebElement btnShowadvertisements;
	
	//advertisement text
	@FindBy(css=".card-text")public WebElement txtadvertisement;
	
	//Text title
	@FindBy(css=".label-on-side:nth-child(2) .d-flex")public WebElement txtTitle;
	
	//Add favorites
	@FindBy(css="heading-actions:nth-child(1) heading-action-button:nth-child(1) div")public WebElement btnAddOrRemoveFav;
	
	//Validation note
	@FindBy(xpath="//snack-bar")public WebElement noteTxt;
	
	//dashboard
	@FindBy(id="menu_dashboard")public WebElement lnkDashboard;
    
	
	//constrictor to store PageFactory class and inItElements method
	/**
	 * @param driver
	 */
	public CyclosDirectoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}

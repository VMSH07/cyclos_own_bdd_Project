package cyclos_pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cyclos_config_files.CyclosDriverClass;
import excel_utils.ReadAndSetData;
import mouse_keyboard_select_events.Events;
import reusible_methods.Synchronizations;

public class CyclosPaymentSchedulePage extends CyclosDriverClass{
	
	//click on banking
	@FindBy(id="menu_banking")public WebElement lnkBanking;

	//Banking label
	@FindBy(className="side-menu-title")public WebElement lblBanking;
	
	//Payment to user link
	@FindBy(css=".navbar a[href='/ui/banking/self/payment']")public WebElement lnkPayToUser;

	//To user text field
	public By txtUser= By.xpath("//user-field[contains(@class,'form-field ng-untouched')]/div/div/div/input");
	
	//Drop down elements
	@FindBy(css="a[class^='select-option']")public List<WebElement> ddElements;

	//Amount text field
	@FindBy(css=".d-flex input[class^=form-control]")public WebElement txtAmount;
		
	//Schedule drop down
	@FindBy(css=".label-value-container button[class*='custom-select']")public WebElement ddSchedule;

	//Select options
	@FindBy(css="a[class^='select-option']")public List<WebElement> sltOptions;
	
	//Due date
	@FindBy(xpath="//label-value/div/div/input")public WebElement txtDueDate;

	//Amount text field
	@FindBy(css=".d-flex textarea[class^=form-control]")public WebElement txtDiscription;
			
	//Button next
	@FindBy(css=".actions button[class^='btn']")public WebElement btnNext;

	//Label amount
	@FindBy(css=".totalAmount div[class*='label-value-value']")public WebElement lblAmount;
	
	//Button conform
	@FindBy(css=".btn-primary:nth-child(1)")public WebElement btnConform;

	
	//constrictor to store PageFactory class and inItElements method
	public CyclosPaymentSchedulePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//method schedulePayment
	public void schedulePay(String loginpath,String loginname, String schedulepath,String schedulename) throws Exception
	{
		//abject of CyclosLoginLogoutPage
		CyclosLoginLogoutPage clp=new CyclosLoginLogoutPage(d);
						
		/*read data from sheet*/
		rsd=new ReadAndSetData(loginpath,loginname);
						
		//call login method
		clp.cyclosLogin(d,rsd.getData(4, 0),rsd.getData(4, 1));
				
		//create object to CyclosPaymentSchedulePage class
		CyclosPaymentSchedulePage cps=new CyclosPaymentSchedulePage(d);
				
		//click on banking
		cps.lnkBanking.click();
				
		//verify bankaing label
		assertEquals(cps.lblBanking.getText(),"Banking");
				
				//click on payment to user
				cps.lnkPayToUser.click();
				
		        /*Read data from sheet*/
				rsd=new ReadAndSetData(schedulepath,schedulename);
				
				//explicitly wait
				Synchronizations.explictly(d,ExpectedConditions.visibilityOfElementLocated(cps.txtUser));
				
				//actions class to send keys
				Events.actions(d).sendKeys(d.findElement(cps.txtUser),rsd.getData(1,0)).build().perform();
				
				//store all the element drop down elements in list
				List<WebElement> dd=cps.ddElements;
				
				//iterate drop down elements to get desired username
				for(WebElement uName:dd)
				{   
//					System.out.println(uName.getText()+"--"+rsd.getData(2,0));
					//if condition to getname and check 
					if(uName.getText().equals(rsd.getData(2,0)))
					{
						//select name
						uName.click();
					  break;
					}
				}
				
				//Type amount in to ammount text field
				cps.txtAmount.sendKeys(rsd.getData(1, 1));
				
				//click on scheduling dropdown
				cps.ddSchedule.click();
				
				//store all the element drop down elements in list
				List<WebElement> dd1=cps.sltOptions;
						
				//iterate drop down elements to get desired username
				for(WebElement schedule:dd1)
				{
					//if condition to getname and check 
					if(schedule.getText().equals(rsd.getData(1, 2)))
					{
						//select name
						schedule.click();
						break;
					}
				}
				//get locat date
				LocalDate future = LocalDate.now().plusDays(2);
				//simple date formate
				DateTimeFormatter sdf= DateTimeFormatter.ofPattern("dd-MM-yyyy");
				
				String dt=future.format(sdf);
				
				//send keys in calander using action class
				Events.actions(d).sendKeys(cps.txtDueDate,dt).build().perform();
				
				//set discription
				cps.txtDiscription.sendKeys(rsd.getData(1, 2));
				
				//click on next
				cps.btnNext.click();
				
				//verify ammount to be sent
				assertTrue(cps.lblAmount.isDisplayed());
						
				//click on conform
				cps.btnConform.click();
				
				//create object to CyclosDirectoryPage
				CyclosDirectoryPage cdp=new CyclosDirectoryPage(d);
				
				//if condition when success message shows
				if(cdp.notifaction.getText().equals(rsd.getData(1, 4)))
				{
					assertEquals(cdp.notifaction.getText(),(rsd.getData(1, 4)));
					
					//click on new payment button
					cdp.btnNewPay.click();
				}
				//else condition when error shows up
				else
				{
					//explicitly wait
					Synchronizations.explictly(d,ExpectedConditions.textToBePresentInElement(cdp.notifaction,rsd.getData(2, 4)));
					
					cdp.notifaction.getText().equals(rsd.getData(2, 4));
					
					//close alert
					cdp.clsNote.click();
				}
			}
}

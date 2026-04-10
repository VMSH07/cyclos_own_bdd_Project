package cyclos_test_scripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosDirectoryPage;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import cyclos_pageobjects.CyclosPaymentSchedulePage;
import excel_utils.ReadAndSetData;
import mouse_keyboard_select_events.Events;
import reusible_methods.Synchronizations;

public class PaymentSchedule extends CyclosDriverClass{
	
	ReadAndSetData rsd;
	
	//instance variable to read data from login sheet
	String excelLogin="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\CyclonLogin.xlsx";
	
	//instance variable to read data from directory sheet
	String Excel="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\Scheduling.xlsx";
	
	@Test
	public void schedule() throws Exception
	{
		        //abject of CyclosLoginLogoutPage
				CyclosLoginLogoutPage clp=new CyclosLoginLogoutPage(d);
								
				/*read data from sheet*/
				rsd=new ReadAndSetData(excelLogin,"Login Cases");
								
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
				rsd=new ReadAndSetData(Excel,"SchedulePay");
						
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

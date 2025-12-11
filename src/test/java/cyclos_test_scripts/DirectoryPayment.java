package cyclos_test_scripts;

import static org.testng.Assert.*;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosDirectoryPage;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import excel_utils.ReadAndSetData;
import reusible_methods.Synchronizations;

public class DirectoryPayment extends CyclosDriverClass{
	
	//instance variable to read data from directory sheet
	String excelLogin="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\CyclonLogin.xlsx";
	
	//instance variable to read data from directory sheet
	String excel="E:\\Selenium web driver-utlities\\cyclos_own_bdd_Project\\src\\main\\resources\\Directory_data.xlsx";
	
	
	@Test
	public void marketPlacePayment() throws Exception
	{
		//abject of CyclosLoginLogoutPage
		CyclosLoginLogoutPage clp=new CyclosLoginLogoutPage(d);
		
/*read data from sheet*/
		rsd=new ReadAndSetData(excelLogin,"Login Cases");
		
		//call login method
		clp.cyclosLogin(d,rsd.getData(4, 0),rsd.getData(4, 1));
		
		//create variable to CyclosDirectoryPage
		CyclosDirectoryPage cdp=new CyclosDirectoryPage(d);
		
		//verify presence of directory
		assertTrue(cdp.lnkDirectory.isDisplayed());
		
		//click on directory
		cdp.lnkDirectory.click();
		
		//verify presence of business directory
		assertTrue(cdp.lnkBusinessDirectory.isDisplayed());
		
/*read data from sheet*/
		rsd=new ReadAndSetData(excel,"DirectoryPayment");
		
		//search keyword
		cdp.txtKeyWords.sendKeys(rsd.getData(1, 0));
		
		//explicitly wait
		Synchronizations.explictly(d,ExpectedConditions.textToBePresentInElementLocated(cdp.contact,rsd.getData(2,0)));
		
		//verify contact name is same as searched name
		assertEquals(d.findElement(cdp.contact).getText(),rsd.getData(2, 0));
		
		//store contact details in variable
		String cName=d.findElement(cdp.contact).getText();
		
		//Click on serached contact
		d.findElement(cdp.contact).click();
		
		//verify make payment link
		assertTrue(cdp.lnkMakePayment.isDisplayed());
		
		//click on makePayment
		cdp.lnkMakePayment.click();
		
		//verify user name is same as selected contact name
		assertEquals(cdp.lblUser.getText(),cName);
		
		//Type ammount in to ammount text field
		cdp.txtAmount.sendKeys(rsd.getData(1, 1));
		
		//set discription
		cdp.txtDiscription.sendKeys(rsd.getData(1, 2));
		
		//click on next
		cdp.btnNext.click();
		
		//verify ammount to be sent
		assertTrue(cdp.lblAmount.isDisplayed());
		
		//click on conform
		cdp.btnConform.click();
		
		//if condition when success message shows
		if(cdp.notifaction.getText().equals(rsd.getData(1, 3)))
		{
			assertEquals(cdp.notifaction.getText(),(rsd.getData(1, 3)));
			
			//click on new payment button
			cdp.btnNewPay.click();
		}
		//else condition when error shows up
		else
		{
			//explicitly wait
			Synchronizations.explictly(d,ExpectedConditions.textToBePresentInElement(cdp.notifaction,rsd.getData(2, 3)));
			
			assertEquals(cdp.notifaction.getText(),(rsd.getData(2, 3)));
			
			//close alert
			cdp.clsNote.click();
		}
		
		//call logout method
		clp.logout(d);
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

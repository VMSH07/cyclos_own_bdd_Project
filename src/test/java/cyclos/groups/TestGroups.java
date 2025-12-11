package cyclos.groups;

import org.testng.annotations.*;

import com.beust.jcommander.Parameter;

import cyclos_config_files.CyclosDriverClass;
import cyclos_pageobjects.CyclosLoginLogoutPage;
import cyclos_test_scripts.CyclosLogin;
import cyclos_test_scripts.DirectoryPayment;
import cyclos_test_scripts.PaymentSchedule;

public class TestGroups{
	
	//create object to driver class
	CyclosDriverClass cd;
	
	//create object to DirectoryPayment
	DirectoryPayment dp;
	
	//create object to DirectoryPayment
	PaymentSchedule ps;
	
	//create object to CyclosLogin
	CyclosLogin cl;
	
	//create object to logout class
	CyclosLoginLogoutPage clo;
	
	@Test(groups ="sanity",dependsOnGroups = "smoke")
	public void sanityGroup() throws Exception
	{
		//call schedule payment method
		ps.schedule();
	}
	
	@Test(groups ="smoke")
	public void smokeGroup() throws Exception
	{
		//call marketplacePayment test
		dp.marketPlacePayment();
		
		//call cyclos login method
		cl.login();
	}
	
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
    public void setup(String browser) throws Exception {
        cd = new CyclosDriverClass();
        clo =new CyclosLoginLogoutPage(cd.d);
        dp = new DirectoryPayment();
        ps = new PaymentSchedule();
        cl =new CyclosLogin();
        
        //call open browser method
        cd.openBrowser();
    }
	
	@AfterClass(alwaysRun = true)
	public void quit()
	{
		//call quit method from driver class
		cd.close();
	}
}

package definations_classes;
import cyclos_config_files.CyclosDriverClass;
import io.cucumber.java.*;

public class Hooks extends CyclosDriverClass{
	
/*Global hooks*/
	@BeforeAll
	public static void beforeAll() throws Exception
	{
		//open browser
		openBrowser();
	}
	@AfterAll
	public static void afterAll()
	{
		//close browser
		close();
	}
	
/*Scenario hooks*/
//	@Before
//	public void doSomethingBefore() throws Exception {
//		
//		//open browser
//		openBrowser();
//		
//	}
//	@After
//	public void doSomethingAfter(){
//		
//		//close browser
//		close();
//	}
	
	
/*Conditional hooks*/
//	@Before("@OpenClose")
//	public void beforeScenario() throws Exception
//	{
//		//open browser
//		openBrowser();
//	}
//	@After("@OpenClose")
//	public void afterScenario()
//	{
//		//close browser
//		close();
//	}

}

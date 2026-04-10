package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import cyclos_config_files.CyclosDriverClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = {"Feature Files"},
		glue={"definations_classes"},
		//dryRun = true,
        plugin = {
        		  "pretty",
                  "html:target/report/cucumber.html",
                  "json:target/report/cucumber.json"
                 },
	    tags=/*"@LoginPositiveNegitive*/"@Contacts or @Directory"

		)
 
public class TestRunner extends AbstractTestNGCucumberTests
{
//	 @BeforeClass(alwaysRun = true)
//	 @Parameters("cucumber.filter.tags")
//	 public void setTags(String tags) {
//	 System.setProperty("cucumber.filter.tags", tags);
//	 }
//	 
//	 @Override
//	 @DataProvider(parallel = true)
//	 public Object[][] scenarios() {
//	     return super.scenarios();
//	 }
//	 @AfterSuite
//		public static void afterSuite()
//		{
//			//close browser
//		    CyclosDriverClass.close();
//		}
}


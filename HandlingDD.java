 package package2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HandlingDD {

	public static void main(String[] args) throws Exception {
		//launch browser
		WebDriver handil=new ChromeDriver();
		//load web page
		handil.get("https://courses.letskodeit.com/practice");
		//add select class
		Select multidd=new Select(handil.findElement(By.id("multiple-select-example")));
		//select multiple Select by for loop
		for(int i=0;i<multidd.getOptions().size();i++)
		{
			multidd.selectByIndex(i);
		}
		//deselect all function
		multidd.deselectAll();
		
		handil.quit();

	}

}

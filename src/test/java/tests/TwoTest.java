package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base{
	public WebDriver driver ;
	@Test
	public void testTwo() throws IOException, InterruptedException
	{
		System.out.println("Changes by Madhav");
		System.out.println("Changes by Shruti");
		System.out.println("Changes by Madhav update");
		System.out.println("testTwo");
		   driver = initializeDriver();
		   driver.get("https://google.com");
		  Thread.sleep(2000);
		   driver.close();
	}

}

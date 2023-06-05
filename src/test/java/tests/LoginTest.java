package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import resources.Base;

public class LoginTest extends Base{
	public WebDriver driver;
	Logger log;
	@BeforeMethod
	public void openApplication() throws IOException
	{
		 log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigate to App URL");
	}
	
	@Test(dataProvider="getLoginData")
	public void login(String username,String password,String expected) throws IOException
	{
		 
		
		LandingPage landing = new LandingPage(driver);
		landing.myAccount().click();
		log.debug("Clicked on MyAccount dropdown");
		landing.loginOption().click();
		log.debug("Clicked on Login Option");
		LoginPage loginPage = new LoginPage(driver);
		//loginPage.emailAddress().sendKeys(prop.getProperty("email"));
		loginPage.emailAddress().sendKeys(username);
		log.debug("Enter email Address");
		//loginPage.password().sendKeys(prop.getProperty("password"));
		loginPage.password().sendKeys(password);
		log.debug("Enter Password");
		loginPage.loguinButton().click();
		log.debug("Clicked on Login Button");
		MyAccountPage map = new MyAccountPage(driver);
		String actual=null;
		//Assert.assertTrue(map.editAccountInfo().isDisplayed());
		try {
		if(map.editAccountInfo().isDisplayed()) {
			log.debug("Login is Successful");
			actual="Success";
		}
		}catch(Exception e) {
			log.debug("Login Failed");
			actual="Failure";
		}
		Assert.assertEquals(actual, expected);
		log.info("Login test got passed");
	}
	@AfterMethod
	public void teardown()
	{
		driver.close();
		log.debug("Browser got closed");
	}
    @DataProvider
	public Object[][] getLoginData()
	{
		Object[][] data = {{"din@gmail.com","din123","Success"},{"SampleTest@gmail.com","test@123","Failure"}};
		return data;
	}
}

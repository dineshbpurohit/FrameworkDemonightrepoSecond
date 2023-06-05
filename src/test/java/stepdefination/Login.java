package stepdefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import resources.Base;

public class Login extends Base{
	WebDriver driver;
	LoginPage loginPage;
	@Given("^open any browser$")
	public void open_any_browser() throws IOException {
		 driver = initializeDriver();
	}

	@Given("^Navigate to login page$")
	public void navigate_to_login_page() {
		driver.get(prop.getProperty("url"));
		LandingPage landing = new LandingPage(driver);
		landing.myAccount().click();
		landing.loginOption().click();
	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into fields$")
	public void user_enters_username_as_and_password_as_into_fields(String email, String password) {
		 loginPage = new LoginPage(driver);
		loginPage.emailAddress().sendKeys(email);
		loginPage.password().sendKeys(password);
	}

	@When("^User clicks on login button$")
	public void user_clicks_on_login_button() {
		loginPage.loguinButton().click();
	}

	@Then("^Verify user is able to successfully login$")
	public void verify_user_is_able_to_successfully_login() {
		MyAccountPage map = new MyAccountPage(driver);
		Assert.assertTrue(map.editAccountInfo().isDisplayed());
	}

   @After
   public void closeBrowser()
   {
	   driver.close();
   }


}

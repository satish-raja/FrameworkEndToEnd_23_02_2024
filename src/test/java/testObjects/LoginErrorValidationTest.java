package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;

public class LoginErrorValidationTest extends BaseTest{
	WebDriver driver ;
	String userName ="satishraj@gmail.com" ,  password = "Raja1234" ;
	
	@Test
	public void loginErrorValidation()   {
		loginPage.LoginApplication( userName, password);
		Assert.assertEquals(loginPage.loginErrorValidation(),"Incorrect email or password.","Login Error Message Validation Failed. ");
		System.out.println("Login Error Message Validation Completed");
	}
}
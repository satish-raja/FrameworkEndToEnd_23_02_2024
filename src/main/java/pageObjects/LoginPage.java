package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageBase.BasePage;

public class LoginPage extends BasePage{
	WebDriver driver ;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail ;
	
	@FindBy(id="userPassword")
	WebElement userPassword ;
	
	@FindBy(id="login")
	WebElement submit ;
	
	@FindBy(id ="toast-container")
	WebElement loginErrorMessage ;
	
//	By loginPageMessage = By.id("toast-container");
	By loginPageMessage = By.xpath("//div[@aria-label='Incorrect email or password.']");
	
	public ProductCataloguePage LoginApplication(String userName, String password)  {
		userEmail.sendKeys(userName);
		userPassword.sendKeys(password);
		submit.click();
	return new ProductCataloguePage(driver);
	}
	
	public void goTo() {
    	driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String loginErrorValidation() {
		waitForElementToAppear(loginPageMessage);	
		return loginErrorMessage.getText();
	}
}
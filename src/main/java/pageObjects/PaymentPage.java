package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageBase.BasePage;

public class PaymentPage extends BasePage{
	WebDriver driver ;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selCountry ;
	
	@FindBy(css = "*.ta-results button")
	List <WebElement> countries ;

	
	@FindBy(css = "a[class*='submit']")
	WebElement submit ;
	
	By submitButton = By.cssSelector("a[class*='submit']");
	By countryEle = By.cssSelector("*.ta-results");
	
	public OrderConfirmationPage selectCountryAndSubmit(String countryName ) {
			waitForElementToAppear(submitButton);			
			selCountry.sendKeys(countryName);
			waitForElementToAppear(countryEle);	
			List<WebElement> listOfWebElements = driver.findElements(By.cssSelector("*.ta-results button"));
			for (WebElement country : listOfWebElements) {
				if (country.getText().equalsIgnoreCase("india")) {
				country.click();
				break;
				}
			}
			submit.click();
			System.out.println("Payment Done Successfully");
			return new OrderConfirmationPage(driver);
	}
}
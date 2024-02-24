package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageBase.BasePage;

public class OrderConfirmationPage extends BasePage{
	WebDriver driver ;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage ;
	
	@FindBy(css = "label.ng-star-inserted")
	WebElement orderIdEle ;
	
	By orderConfirmationPageElement = By.cssSelector("h1.hero-primary");
	
	public String getOrderSubmissionMessage( ) {
		waitForElementToAppear(orderConfirmationPageElement);
		return confirmationMessage.getText();
	}
	
	public String getOrderId() {
		String orderId = orderIdEle.getText().replace("| ", "").replace(" |", "");
		signOut();
		return orderId;
	}
	
	
}

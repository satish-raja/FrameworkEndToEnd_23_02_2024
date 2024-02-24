package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageBase.BasePage;

public class CartPage extends BasePage{
	WebDriver driver ;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	By cartPage = By.xpath("//button[normalize-space()='Checkout']");
	
	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement checkOut ;
	
	public PaymentPage checkOut() {
		waitForElementToAppear(cartPage);	
		checkOut.click();
		System.out.println("CheckOut Done Successfully");
		return  new PaymentPage(driver);
	}
	
	public Boolean verifyProductdisplay(String productName) {
    Boolean match = driver.findElement(By.xpath("//div[@class='cartSection']//following::h3")).getText().equalsIgnoreCase(productName);
    System.out.println("Product: "+  productName  +"  Display Verfied");
	return match;
	}
    
}

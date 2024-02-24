package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageBase.BasePage;

public class ProductCataloguePage  extends BasePage{
	WebDriver driver ;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}


	@FindBy (xpath = "//button[@routerlink='/dashboard/cart']") 
	WebElement buttonAddToCart ;

	
	@FindBy(css =".logo-holder.logo-7" )
	WebElement productPage;
	
	By toastMessage = By.id("toast-container");
	By productCatalogue = By.tagName("app-dashboard");
	By toastSuccessMessage = By.xpath("//div[@aria-label='Login Successfully']");
	
	public CartPage addProductToCart(String productName) {
		waitForElementToAppear(toastSuccessMessage);
		waitForElementToDisappear(toastSuccessMessage);
	System.out.println("Successfully Login into Application");	
		waitForElementToAppear(productCatalogue);
		driver.findElement(By.xpath("//b[text()='" + productName + "']/parent::h5/following-sibling::button[2]")).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(toastMessage);
		goToCart();
		System.out.println("Successfullt added Product: "+ productName +" To Cart");
		return new CartPage(driver);		
	}
}
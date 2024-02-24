package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import pageBase.BasePage;

public class OrderHistoryPage extends BasePage{
	WebDriver driver ;
	boolean match ;
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	By yourOrdersEle = By.xpath("//h1[normalize-space()='Your Orders']");
	
	public boolean orderHistory(String orderId, String productName) {
		goToOrders() ;
		waitForElementToAppear(yourOrdersEle);
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
        	if (row.getText().contains(orderId) && row.getText().contains(productName)) {
        		match = true ;
        		break ;
        	} 
        }
        Assert.isTrue(match,"Order Not Found in Order History");
        signOut();
		return match ;		
	}	
}
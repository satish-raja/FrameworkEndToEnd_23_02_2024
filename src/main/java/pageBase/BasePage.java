package pageBase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrderHistoryPage;

public class BasePage {

	WebDriver driver ;
	public BasePage(WebDriver driver) {
		this.driver = driver ;
	}
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/cart']") 
	WebElement buttonAddToCart ;
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/myorders']") 
	WebElement buttonGoToMyOrders ;
	
	@FindBy(css = ".fa.fa-sign-out")
	WebElement signOutButtonEle ;	
	
	By loginPageMessage = By.id("toast-container");
	
	public void waitForElementToAppear( By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear( By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void goToCart() {
		buttonAddToCart.click();
	}
	
	public OrderHistoryPage goToOrders() {
		buttonGoToMyOrders.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage ;
	}
	
	public void signOut() {
		signOutButtonEle.click();
		waitForElementToAppear(loginPageMessage);	
	}
}

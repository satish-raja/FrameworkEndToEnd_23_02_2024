package testObjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrderHistoryPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCataloguePage;
import testComponents.BaseTest;

public class SubmitOrderTest  extends BaseTest{
	WebDriver driver ;
	String orderId ;
	String productName ;
	String [] [] orderData = new String[10] [10];
	int i = 0 ;
	int j = 0 ;
	
	@Test(dataProvider ="getData" ,dataProviderClass = BaseTest.class)
	public void submitOrder(HashMap<String, String > input) {
//	public void submitOrder(String userName, String password, String productName) {
		ProductCataloguePage productCatalogue = loginPage.LoginApplication(input.get("userId"),input.get("password"));
		CartPage cartPage = productCatalogue.addProductToCart(input.get("productName"));
		cartPage.verifyProductdisplay(input.get("productName"));
		PaymentPage paymentPage = 	cartPage.checkOut();
		OrderConfirmationPage orderConfirmationPage = paymentPage.selectCountryAndSubmit("India");
		String confirmation = orderConfirmationPage.getOrderSubmissionMessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		orderId= orderConfirmationPage.getOrderId();
		orderData[i]=new String[] { orderId, input.get("productName") };
		System.out.println("Successfully Submitted Order. Order ID :" + orderId);
		i++;
	}
	
	@Test(dependsOnMethods = "submitOrder",alwaysRun = true,dataProvider ="getData" ,dataProviderClass = BaseTest.class)
	public void orderHistoryValidation(HashMap<String, String > input) {
		ProductCataloguePage productCatalogue = loginPage.LoginApplication(input.get("userId"),input.get("password"));
		OrderHistoryPage orderHistoryPage = productCatalogue.goToOrders();
		  orderId = orderData[j][0];
		  productName = orderData[j][1];
		Assert.assertTrue(orderHistoryPage.orderHistory(orderId,productName));
		System.out.println("Order History Validation completed successfully");
		j++ ;
	}
}
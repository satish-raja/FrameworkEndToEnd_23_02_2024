package testComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.LoginPage;

public class BaseTest3 {
	
    public WebDriver driver;
	public LoginPage loginPage ;

    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public LoginPage setUp() throws Exception {
    	System.out.println("Step 1");
        ChromeOptions options = new ChromeOptions();
        System.out.println("Step 2");
        options.addArguments("start-maximized");
        System.out.println("Step 3");
        driver = new ChromeDriver(options);
        System.out.println("Step 4");
    	loginPage = new LoginPage(driver);
    	System.out.println("Step 5");
		loginPage.goTo();
		System.out.println("Step 6");
		return loginPage ;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
    	System.out.println("Step 7");
        driver.quit();
    }

}

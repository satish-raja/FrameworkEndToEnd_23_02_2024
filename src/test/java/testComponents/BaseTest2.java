package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LoginPage;


public class BaseTest2 {
	
    public WebDriver driver;
	public LoginPage loginPage ;
    public WebDriver initializeDriver(String browserName) throws IOException, URISyntaxException {
    	
    	Properties prop = new Properties() ;
    	File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\data\\testData.properties");
    	FileInputStream fis = new FileInputStream(file);
    	prop.load(fis);
    	 boolean headlessRun = prop.getProperty("HeadlessRun").equalsIgnoreCase("yes") ;
    	 boolean remoteRun  = prop.getProperty("RemoteRun").equalsIgnoreCase("yes");

             if (browserName.equalsIgnoreCase("chrome")) {
                 ChromeOptions options = new ChromeOptions();
                 if (headlessRun) {
                     options.addArguments("--headless");
                     options.addArguments("window-size=1920,1080");
                 } 
		          if (remoteRun) {
					driver = new RemoteWebDriver(new URI("http://localhost:4444").toURL(), options);
					} else {
		          driver = new ChromeDriver(options);
					}
             } else if (browserName.equalsIgnoreCase("firefox")) {
                 FirefoxOptions options = new FirefoxOptions();
                 if (headlessRun) {
                     options.addArguments("--headless");
                     options.addArguments("window-size=1920,1080");
                 }
		          if (remoteRun) {
					driver = new RemoteWebDriver(new URI("http://localhost:4444").toURL(), options);
					} else {
		                 driver = new FirefoxDriver(options);
					}

             } else if (browserName.equalsIgnoreCase("edge")) {
                 EdgeOptions options = new EdgeOptions();
                 if (headlessRun) {
                     options.addArguments("headless");
                     options.addArguments("window-size=1920,1080");
                 }
		          if (remoteRun) {
					driver = new RemoteWebDriver(new URI("http://localhost:4444").toURL(), options);
					} else {
		                 driver = new EdgeDriver(options);
					}


             } else {
                 throw new IllegalArgumentException("Invalid browser name: " + browserName);
             }
    	
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;	
       }

    
    @BeforeMethod
    public LoginPage launchApplication() throws IOException, URISyntaxException {
    	String browserName = "Firefox";
    	driver = initializeDriver(browserName);
    	loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage ;
    }
    
    @AfterMethod
    public void closeApplication() {
    	driver.quit();
    	System.out.println("Successfully Closed Application");
    }
    
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
	    String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//		return new Object [] [] { {"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"} , {"satishraja@gmail.com","Raja1234","ZARA COAT 3"} , {"chittyraja@gmail.com","Test1234","IPHONE 13 PRO"} } ;			
	    List<HashMap<String, String>> data = getJsonDataToMap(
	            System.getProperty("user.dir") + "\\src\\main\\java\\data\\UserCredetials.json");
	    return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
	
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
    
	public static ExtentReports getReportObject()
	{
		String path  = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Tools");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Satish Raja");
		return extent ;
	}
	
    
}

package package_ATUTestRecorder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import atu.testrecorder.ATUTestRecorder;


public class Demo {

	WebDriver driver;
	 ATUTestRecorder recorder;

	 @BeforeTest
	 public void setup() throws Exception {
	  DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	  Date date = new Date();
	  //Created object of ATUTestRecorder
	  //Provide path to store videos and file name format.
	  recorder = new ATUTestRecorder("C:\\VIDEOS\\","TestVideo-"+dateFormat.format(date),false);
	  //To start video recording.
	  recorder.start();  

	 }

	 @Test
	  public void f() {
			String baseUrl = "https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator#/";
	        System.out.println("Launching Google Chrome browser"); 
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daiva1\\Desktop\\configuration\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get(baseUrl);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //capture the current started page heading and compare it with expected one
	        String testTitle = "Online Currency Exchange | Paysera";
	        String originalTitle = driver.getTitle();
	        Assert.assertEquals(originalTitle, testTitle);
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        WebElement element = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/input")); 
	        element.click();
	        element.sendKeys("10000");
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[3]/input"));
	        String arnull = element1.getAttribute("value");
	        // Check whether input field Buy is blank
	        if(arnull.isEmpty())
	        {
	           System.out.println("Input field Buy is empty");
	        }
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        WebElement element3 = driver.findElement(By.xpath("/html/body/main/article/section[3]/div/div[3]/div/div/div[2]/div[1]/form/div[3]/input"));
	        element3.click();
	        element3.sendKeys("55");
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/input"));
	        String arnull1 = element2.getAttribute("value");
	        // Check whether input field Sell is blank
	        if(arnull1.isEmpty())
	        {
	           System.out.println("Input field Sell is empty");
	        }
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	  }
	 
	 @AfterTest
	 public void Close() throws Exception {
	  driver.quit();
	  //To stop video recording.
	  recorder.stop();;
	 }
	}

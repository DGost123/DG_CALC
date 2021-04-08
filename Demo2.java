package package_ATUTestRecorder2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import atu.testrecorder.ATUTestRecorder;


public class Demo2 {

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
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get(baseUrl);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        System.out.println("Langà jau maksimizavo");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //capture the current started page heading and compare it with expected one//
	        String testTitle = "Online Currency Exchange | Paysera";
	        String originalTitle = driver.getTitle();
	        Assert.assertEquals(originalTitle, testTitle);
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        //slenkame á puslapio pabaigà
	        WebElement element = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/input")); 
	        element.click();
	        System.out.println("paklikino");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        //paspaudþiame lokalizacijos mygtukà
	        WebDriverWait wait = new WebDriverWait(driver, 60);
	        WebElement elementcount =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div[2]/div/div/div[2]/div/span")));
	        js.executeScript("arguments[0].click();", elementcount);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //paspaudþiame ant  country reikðmiø sàraðo rodymà inicijuojanèio mygtuko
	        WebElement elementcount1 =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("countries-dropdown")));
	        js.executeScript("arguments[0].click();", elementcount1);
	        //pasirenkame ðalá
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        WebElement element5 = driver.findElement(By.xpath("//*[text() = '\n"
	        		+ "                                                                        Ukraine\n"
	        		+ "                                ']"));
	        js.executeScript("arguments[0].click();", element5);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //patikriname, ar valiutos kodas sutampa su pasirinktos ðalies valiutos kodu
	        String maksa="UAH";
	        WebElement elementvaliuta =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#currency-exchange-app > div > div > div.rate-table.ng-isolate-scope > div.rate-table-filter > form > div:nth-child(1) > div > div.ui-select-match.csp.ng-scope > span > span.ui-select-match-text.pull-left > span")));
	        String elementvaliutatxt = elementvaliuta.getText();
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        Assert.assertEquals(elementvaliutatxt, maksa);
	        System.out.println("paklikino English");
	        }
	 
	 @AfterTest
	 public void Close() throws Exception {
	  driver.quit();
	  //To stop video recording.
	  recorder.stop();;
	 }
	}


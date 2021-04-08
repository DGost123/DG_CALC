package package_ATUTestRecorder3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
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


public class Demo3 {

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
	       try {
			Thread.sleep(8000);
		    } catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	        //iðvalome SELL laukà ir ávedame naujà reikðmæ (EUR lieka pagal nutylëjimà
	        WebElement element = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/input")); 
	        element.click();
	        element.clear();
	        element.sendKeys("10000");
	        element.sendKeys(Keys.ENTER); 
	        try {
	    		Thread.sleep(8000);
	    	    } catch (InterruptedException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    	    }
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	        //paspausti Filter
	        //WebElement element1 = driver.findElement(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[4]/button")); 
	        //element1.click();
	        //palyginti Paysera amount i Swedbank amount
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	        WebDriverWait wait = new WebDriverWait(driver, 60);
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	        WebElement elementps =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#currency-exchange-app > div > div > div.rate-table.ng-isolate-scope > table > tbody > tr:nth-child(1) > td:nth-child(4) > span > span > span")));
	        String elemen = elementps.getText();
	        System.out.println(elemen);
	        String str = elemen.replaceFirst(",", "");
	        System.out.println(str);
	        String str1 = str.replace(".", ",");
	        System.out.println(str1);
	        WebElement elementsw =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#currency-exchange-app > div > div > div.rate-table.ng-isolate-scope > table > tbody > tr:nth-child(1) > td:nth-child(5) > span > span > span:nth-child(1)")));
	        String elemensw = elementsw.getText();
	        System.out.println( elemensw);
	        String strsw = elemensw.replaceFirst(",", "");
	        System.out.println(strsw);
	        String strsw1 = strsw.replace(".", ",");
	        System.out.println(strsw1);
	        try {
	        	 Assert.assertNotEquals(elemen, elemensw);
	        	 System.out.println("Paysera ir Swed pardavimo kainos skiriasi");
	        } catch (AssertionError e) {
	        	System.out.println("Paysera ir Swed pardavimo kainos vienodos");
	        }
	     double val = Double.valueOf(str);
	   	 System.out.println("String to double conversion using valueOf : " + val);
	     	 double valsw = Double.valueOf(strsw);
	        System.out.println("String to double conversion using valueOf : " + valsw);
	   	 double skirt = -1* (val-valsw);
		 //suapvaliname programiðkai apskaièiuotà skirtumà
	   	 skirt = Math.round(skirt*100)/100.0d;
	   	 System.out.format("The value of skirt is:  ");
	   	 System.out.println( skirt);
	   	 //randame svetainëje pateiktà skirtumà
	   	 WebElement elementski =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#currency-exchange-app > div > div > div.rate-table.ng-isolate-scope > table > tbody > tr:nth-child(1) > td:nth-child(5) > span > span > span.other-bank-loss.ng-binding.ng-scope")));
	   	 String sk = elementski.getText();
	   	 String sk1 = sk.replace("(", "");
	   	 String sk2 = sk1.replace(")", "");
	   	 System.out.println(sk2);
	   	 double sk2double = Double.valueOf(sk2);
	     //testui, kad gauti nesëkmingà rezultatà: skirt=(skirt+5)*2;
	   	 System.out.println(skirt);
	   	 Assert.assertEquals(skirt, sk2double);
	     System.out.println("Viso gero!");
	           }
	 @AfterTest
	 public void Close() throws Exception {
	  driver.quit();
	  //To stop video recording.
	  recorder.stop();;
	 }
	}
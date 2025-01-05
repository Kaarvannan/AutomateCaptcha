package Kaar.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public  WebDriver driver; // Static driver for non-thread-safe scenarios
	public final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	public  Properties pro;
	public Scenario scenario;
	private ExtentTest extentTest;
	private ExtentReports extent;
	public static String browser;

	public Logger logger = LogManager.getLogger(BaseClass.class);

	public WebDriver getDriver() {
		// Check if driver is already initialized in the current thread
		WebDriver driverInstance = threadLocalDriver.get();
		if (driverInstance == null) {
			// Get browser type from configuration (e.g., from properties file)
			String browser =System.setProperty("browser", "chrome") ; // Replace with logic to read browser from configuration
			driverInstance = createWebDriver(browser);
			threadLocalDriver.set(driverInstance);
		}
		return driverInstance;
	}

	public WebDriver createWebDriver(String browser) {
		browser = System.setProperty("browser", "Chrome");
		if (browser == null) {
			throw new IllegalArgumentException("Browser name cannot be null");
		}

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				logger.info("Launching Chrome Browser");
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver();
			case "edge":
				logger.info("Launching Edge Browser");
				WebDriverManager.edgedriver().setup();
				return new EdgeDriver();
			case "firefox":
				logger.info("Launching Firefox Browser");
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver();
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		} catch (Exception e) {
			logger.error("Error launching browser: ", e);
			throw new RuntimeException("Failed to launch browser: " + browser, e);
		}
	}

	public String properties(String key) {

		File file = new File("src\\test\\resources\\config.properties");

		FileReader read;

		try {

			read = new FileReader(file);

			pro = new Properties();

			pro.load(read);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pro.getProperty(key);



	}






	public byte[] takeScreenshot() {

		try {
			// Get the current driver instance from ThreadLocal
			WebDriver driverInstance = threadLocalDriver.get(); 

			if (driverInstance != null) {
				byte[] scrFile = ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.BYTES);
//				String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
				//	            String scenarioName = BaseClass.this.scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
//				String screenshotPath = "screenshots/" +  "_" + timestamp + ".png";
//				new File("screenshots").mkdirs();
//				FileUtils.copyFile(scrFile, new File(screenshotPath));
				//	            extentTest.addScreenCaptureFromPath(screenshotPath);
//				this.getScenario(scenario);
//				System.out.println("Screenshot saved at: " + screenshotPath);
	            return scrFile; 

			} else {
				logger.error("WebDriver instance is null. Cannot take screenshot.");
			}
		} catch (Exception e) {
			logger.error("Failed to take screenshot: " + e.getMessage(), e);
		}
		return null;
	}
	
	
	public byte[] takeScreenshotFile() {
		try {
			// Get the current driver instance from ThreadLocal
			WebDriver driverInstance = threadLocalDriver.get(); 

			if (driverInstance != null) { 
				byte[] scrFile = ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.BYTES);
				String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
				//	            String scenarioName = BaseClass.this.scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
				String screenshotPath = "screenshots/" +  "_" + timestamp + ".png";
				new File("screenshots").mkdirs();
				FileUtils.writeByteArrayToFile(new File(screenshotPath), scrFile); 

//			FileUtils.copyFile(scrFile, new File(screenshotPath));
				//	            extentTest.addScreenCaptureFromPath(screenshotPath);
//			this.getScenario(scenario);
				System.out.println("Screenshot saved at: " + screenshotPath);
	            return scrFile; 

			} else {
				logger.error("WebDriver instance is null. Cannot take screenshot.");
			}
		} catch (Exception e) {
			logger.error("Failed to take screenshot: " + e.getMessage(), e);
		}
		return null;
	}
	
	
	public void pressEnter() {
		
		Actions action = new Actions(this.threadLocalDriver.get());
		action.sendKeys(Keys.ENTER).perform();
		
		
	}
	
	
	public String getAttributeClass(WebElement element) {
		
		String value = element.getDomAttribute("class");
		
		
		return value;
		
		
	}
	
	public String getAttributeSrc(WebElement element) {
		
		String value = element.getDomAttribute("src");
		
		
		return value;
		
		
	}

	
	WebDriverWait wait = new WebDriverWait(this.threadLocalDriver.get(),Duration.ofSeconds(5));
	
	public void presenceOfWebElement(WebElement element) {
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	

	
	
	
}
package stepDefinition;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Kaar.test.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
public class commonStepDefinition {
	private static final Logger logger = LogManager.getLogger(stepDefinition.commonStepDefinition.class);

	private BaseClass base;
	private Scenario scenario;
	private ExtentTest extentTest;
	private ExtentReports extent;
	public String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
	public String screenshotPath = "screenshots/" +  "_" + timestamp + ".png";

	//	private final List<WebDriver> drivers = new ArrayList<>();

	// Default constructor (Fallback for when dependency injection isn't used)

	// Dependency injection constructor
	public commonStepDefinition(BaseClass base, ExtentReports extent) {
		this.base = base;
		this.extent = extent; // Initialize ExtentReports
	}

	@Before
	public void start(Scenario scenario) {
		this.scenario = scenario;

		// Ensure BaseClass is initialized
		if (this.base == null) {
			this.base = new BaseClass();
		}

		// Initialize WebDriver
		base.getDriver();
		base.driverInstance.manage().window().maximize();
		logger.info("Starting scenario: " + scenario.getName());
		extentTest = extent.createTest(scenario.getName()).assignCategory(base.browserName);

		//        System.out.println("Starting scenario: " + scenario.getName() );
	}

	@After
	public void closeBrowser() {
		logger.info(scenario.getStatus());


		//		if (base != null && base.driver.get() != null) {

		try {
			if (scenario.getStatus().equals(Status.SKIPPED)||scenario.getStatus().equals(Status.FAILED)||scenario.getStatus().equals(Status.UNDEFINED)) {

				//				base.takeScreenshot();

				//				extentTest.addScreenCaptureFromPath(screenshotPath);
				byte[] screenshot = base.takeScreenshot();
				if (screenshot != null) {
					//	                scenario.attach(screenshot, screenshotPath, screenshotPath); 
					//					extentTest.addScreenCaptureFromBase64String(screenshot.toString());
					scenario.attach(screenshot, "image/png", scenario.getName()); 
					//	                extentTest.fail("Scenario Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot)).build()); 

				}
				//	            base.takeScreenshotFile();
				//                extentTest.addScreenCaptureFromPath(screenshotPath,"image/png"); 


			} else {
				extentTest.pass("Scenario Passed");
				//				base.takeScreenshot();

				Set<String> k =base.driverInstance.getWindowHandles();

				for (String h:k) {
					base.driverInstance.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
					base.driverInstance.switchTo().window(h);

					byte[] screenshot = base.takeScreenshot();
					if (screenshot != null) {
						scenario.attach(screenshot, "image/png", scenario.getName()); 


					}

					//					extentTest.addScreenCaptureFromPath(screenshot.toString());
					//	                extentTest.pass("Screenshot for Passed Scenario", MediaEntityBuilder.createScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot)).build());
					//	                extentTest.addScreenCaptureFromPath(screenshotPath); 

				}
				//	            base.takeScreenshotFile();
				//                extentTest.addScreenCaptureFromPath(screenshotPath); 

				base.driverInstance.quit();
				System.out.println("closedddddddddddddddddddddddddddddddddddddddddddddddds");

			}
		} catch (Exception e) {
			logger.error("Error during After Scenario teardown: ", e);
		} finally {
			//			cleanUpDrivers();
			base.driverInstance.quit();
			extent.flush();
		}
	}

	//	private void cleanUpDrivers() {
	//		
	//		for (WebDriver driver : drivers) {
	//			try {
	//				if (driver != null) {
	//					driver.quit();
	//					base.getDriver().quit();
	//
	//				}
	//				else {
	//					
	//					base.getDriver().quit();
	//
	//				}
	//				
	//				
	//			} catch (WebDriverException e) {
	//				logger.error("Error while quitting WebDriver: ", e);
	//			}
	//		}
	//		drivers.clear();
	//	}
}



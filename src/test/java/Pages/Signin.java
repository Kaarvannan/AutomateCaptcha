package Pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import Kaar.test.BaseClass;
import Locators.loginPage;
import io.cucumber.java.Scenario;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

//import org.testng.asserts.SoftAssert;

public class Signin {

	public BaseClass base;
	public loginPage loginPage;
	double highPrice =0;
	int count =0;
	String cleanedValue;
	public Actions action;
	public Signin(BaseClass base) {
		this.base = base;
		loginPage = new loginPage(base); 
		action = new Actions(base.threadLocalDriver.get());
// Inject BaseClass dependency
	}

	public void launchUrl() throws InterruptedException, IOException, TesseractException {
		// Navigate to the URL
		base.threadLocalDriver.get().get(base.properties("Url")); // Use ThreadLocal driver
	}
	//	System.out.println("Title is "+title);



	// ... other methods to interact with login page elements ...

	public void enterEmail(String email, int count) {
		try {
			loginPage.getSummHello().click();
			loginPage.getEmail().click();
			//    	loginPage.getEmail().sendKeys(base.properties("Email"));
			loginPage.getEmail().sendKeys(email);
			//    	System.out.println("Enter the count " + count);
		}catch(Exception e) {

			this.extractText();
			
			loginPage.getSummHello().click();
			loginPage.getEmail().click();
			//    	loginPage.getEmail().sendKeys(base.properties("Email"));
			loginPage.getEmail().sendKeys(email);

		}
	}		

	public void enterEmail2() {
		try {
			loginPage.getSummHello().click();
			loginPage.getEmail().click();
			loginPage.getEmail().sendKeys(base.properties("Email"));
			//    	loginPage.getEmail().sendKeys(email);
			//    	System.out.println("Enter the count " + count);
		}catch(Exception e) {

			this.extractText();
			loginPage.getSummHello().click();
			loginPage.getEmail().click();
			loginPage.getEmail().sendKeys(base.properties("Email"));

		}
	}		

	public void extractText() {

		try {
			do{
				// Get CAPTCHA WebElement
				WebElement captchaElement = loginPage.getCaptcha();

				// Ensure CAPTCHA is visible
				base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				// Capture the CAPTCHA image
				File screenshot = captchaElement.getScreenshotAs(OutputType.FILE);
				String filePath = System.getProperty("user.dir") + "/captcha.png";
				File destination = new File(filePath);
				FileHandler.copy(screenshot, destination);

				// Perform OCR using Tesseract
				Tesseract tesseract = new Tesseract();
				tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Path to tessdata folder
				tesseract.setLanguage("eng");

				String extractedText = tesseract.doOCR(destination).trim().replace(" ", "");
				System.out.println("Extracted Text from CAPTCHA: " + extractedText);

				// Enter CAPTCHA text

				loginPage.getCaptchaEntry().click();
				//        loginPage.getCaptchaEntry().clear();
				loginPage.getCaptchaEntry().sendKeys(extractedText);
				loginPage.getCaptchaContinue().click();

				// Wait for the next page to load
				base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}while(loginPage.getCaptcha().isDisplayed());
			loginPage.getSearchBar().click();

			loginPage.getSearchBar().sendKeys("iPhone");
			base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			base.pressEnter();

		} catch (IOException | TesseractException ex) {
			System.out.println("Error during CAPTCHA handling: " + ex.getMessage());
			ex.printStackTrace();
		}



	}
	public void continueClick() {

		loginPage.getContinueButton().click();

	}

	public void searchIphone(){

		try {
			base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofMillis(20));

			loginPage.getSearchBar().click();

			loginPage.getSearchBar().sendKeys("iPhone");
			base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			base.pressEnter();
		}
		catch(Exception e) {
			this.extractText();
		}


	}



	public void find_costliest_iphone() {

		// find iPhone with highest price
		String value = base.getAttributeClass(loginPage.getNextButton());

//		SoftAssert softAssert = new SoftAssert();
		do {
			base.presenceOfWebElement(loginPage.getNextButton());

			value = base.getAttributeClass(loginPage.getNextButton());
			//			String value = base.getAttributeClass(loginPage.getNextButton());
			//			System.out.println("Class value is " + value);


			List<WebElement> price = base.threadLocalDriver.get().findElements(By.xpath("//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span[contains(text(),'iPhone')and contains(text(), 'GB' ) or contains(text(),'TB')]//following::a[3]/span"));
			int totalPhones =  price.size();
			double p=0;

			for(int i =0;i<totalPhones;i++)
			{
				action.scrollToElement(price.get(i)).perform();



				if(price.get(i).getText().equals("")|price.get(i).getText().contains(".*[a-zA-Z].*")) {

					continue;
				}
				if(price.get(i).getText().contains("₹")) {
					cleanedValue = price.get(i).getText().strip().replace("₹", "").replace(" ", "").replace("\n",".");
					//					System.out.println("Price after first if "+ cleanedValue);
					if(cleanedValue.contains(",")) {


						cleanedValue = cleanedValue.replace(",", "");
						//						System.out.println("Price after second if "+ cleanedValue);

					};
					//					if(cleanedValue.contains(".")) {
					//
					//
					//						cleanedValue = cleanedValue.split("\\.")[0].replace(" ", "");
					//						System.out.println("Price after third if "+ cleanedValue);
					//
					//					};
					//
					p = Double.parseDouble(cleanedValue);
				}

				if(p>highPrice) {

					highPrice=p;
					//					System.out.println("Inside if" );
				}


			}    
			System.out.println("Highest price is "+highPrice );
			action.moveToElement(loginPage.getNextButton()).click().perform();

			//			System.out.println("kissik " + count++);
			base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofMillis(20));


		}while(!value.contains("s-pagination-disabled ")); 

	}

	public void selectcostliestphone() {
		String previous = base.getAttributeClass(loginPage.getPreviousButton());
		double  a=0;

		do {
			previous = base.getAttributeClass(loginPage.getPreviousButton());

			//		try {
			List <WebElement> selectPrice = base.threadLocalDriver.get().findElements(By.xpath("//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span[contains(text(),'iPhone')and contains(text(), 'GB' ) or contains(text(),'TB')]//following::a[3]/span"));
			for(int j =0;j<selectPrice.size();j++)



			{
				action.scrollToElement(selectPrice.get(j)).perform();



				if(selectPrice.get(j).getText().equals("")|selectPrice.get(j).getText().matches(".*[a-zA-Z].*")) {

					continue;
				}

				if(selectPrice.get(j).getText().contains("₹")) {
					cleanedValue = selectPrice.get(j).getText().strip().replace("₹", "").replace("\n",".");
					//					System.out.println("Price after first if "+ cleanedValue);
					if(cleanedValue.contains(",")) {


						cleanedValue = cleanedValue.replace(",", "");
						//						System.out.println("Price after second if "+ cleanedValue);
						//
					};
					if(cleanedValue.contains(".")) {


						cleanedValue = cleanedValue.split("\\.")[0];
						//						System.out.println("Price after third if "+ cleanedValue);

					};

					a = Double.parseDouble(cleanedValue);

				}

				if(highPrice==a) {
					//					System.out.println("Match found");

					selectPrice.get(j).click();
					base.threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					Set<String> k =base.threadLocalDriver.get().getWindowHandles();
					for (String h:k) {

						base.threadLocalDriver.get().switchTo().window(h);

						//						System.out.println(h);    


					}
					break;

				}

			}    
			if(highPrice!=a) {
				action.moveToElement(loginPage.getPreviousButton()).click().perform();
				//				System.out.println("kissik Select " + count++);
				base.presenceOfWebElement(loginPage.getPreviousButton());

			}


		}while(highPrice!=a);



	}




}
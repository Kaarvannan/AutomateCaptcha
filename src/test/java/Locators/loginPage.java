package Locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kaar.test.BaseClass;


public class loginPage {
BaseClass base;

//String Browser = System.setProperty("browser","Chrome");

	public loginPage(BaseClass base) {
		
		this.base = base;


		PageFactory.initElements(base.getDriver(), this);
		
			}
	
	
	public BaseClass getBase() {
		return base;
	}


	public WebElement getEmail() {
		return email;
	}


	public WebElement getContinueButton() {
		return continueButton;
	}


	@FindBy(css = "#nav-link-accountList-nav-line-1")
	private WebElement summHello;
	
	
	@FindBy(xpath="(//input[@id = 'ap_email_login' and @class='a-input-text'or @class ='a-input-text a-span12 auth-autofocus auth-required-field auth-require-claim-validation'])")
	
	private WebElement email;	
	

	@FindBy(css ="span.a-button a-button-span12 a-button-primary")
	
	private WebElement continueButton;
	
	
	
	
	@FindBy(xpath="(//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span[contains(text(),'iPhone')]//following::a[3]/span[1]/span[@class='a-offscreen']")
	private WebElement phoneprice;
	
	@FindBy(css="input#twotabsearchtextbox")
	private WebElement searchBar;
	
	
	//Next button in pagination
	
	@FindBy(xpath ="((//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-button-accessibility s-pagination-separator'] | //span[@class='s-pagination-item s-pagination-next s-pagination-disabled ']))")
	
	private WebElement nextButton;
	
	// Previous button in pagination
	
	@FindBy(xpath ="((//a[@class=\"s-pagination-item s-pagination-previous s-pagination-button s-pagination-button-accessibility s-pagination-separator\"] | //span[@class=\"s-pagination-item s-pagination-previous s-pagination-disabled \"]))")
	
	private WebElement previousButton;
	
	@FindBy(css="#captchacharacters")
	private WebElement captchaEntry;



	@FindBy(xpath="//h4[contains(text(),'Type the characters you see in this image:')]//following::img[@src][1]")
	private WebElement captcha;
	
	@FindBy(xpath ="(//button[@type=\"submit\"])")
	private WebElement captchaContinue;

	
	public WebElement getNextButton() {
		return nextButton;
	}


	public WebElement getPreviousButton() {
		return previousButton;
	}


	public WebElement getSearchBar() {
		return searchBar;
	}


	public WebElement getPhoneprice() {
		return phoneprice;
	}

	public WebElement getCaptchaEntry() {
		return captchaEntry;
	}


	public WebElement getCaptcha() {
		return captcha;
	}


	public WebElement getCaptchaContinue() {
		return captchaContinue;
	}

	


	public WebElement getSummHello() {
		return summHello;
	}
	
	
	
	
	
}

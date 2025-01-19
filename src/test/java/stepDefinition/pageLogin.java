package stepDefinition;

import java.io.IOException;

import Kaar.test.BaseClass;
import Locators.loginPage;
import Pages.Signin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.TesseractException;

public class pageLogin {

    private final BaseClass base;
    private final loginPage login;
    private final Signin page;

    public pageLogin(BaseClass base) {
        this.base = base;
        this.login = new loginPage(base);
        this.page = new Signin(base);
    }

    @Given("Launch url")
    public void launchUrl() throws InterruptedException, IOException, TesseractException {
        page.launchUrl();
    }

    @When("Enter {string} count {int}")
    
    public void enterEmail(String email, int number) throws IOException, TesseractException {
        page.enterEmail(email,number);
    }

    
 @When("Enter email")
    
    public void enterEmail2() throws IOException, TesseractException {
        page.enterEmail2();
    }

    
    
    @Then("Click on continue")
    public void continueClick() {
        page.continueClick();
    }
    
    @When("Search iphone")
    public void searchIphone() throws IOException, TesseractException {
    	page.searchIphone();
    }

    
    @Then("Find costliest iphone")
    public void find_costliest_iphone() {
page.find_costliest_iphone();
    }
    
    @Then("Select costliest iPhone")
    public void Select_costliest_iPhone() {
page.selectcostliestphone();
    }


//    @Then("Select costliest phone")
//    public void select_costliest_phone() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

}
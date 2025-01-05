package RunnerClass;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import Kaar.test.BaseClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/report.html",
        		"rerun:target/failedrerun.txt",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
        		"pretty"},
        features = {"src/test/resources/features/Login.feature"}, // Update features as needed
        glue = {"stepDefinition"},
        dryRun = false, 
        monochrome = true, 
        publish = true
        
//        tags =  "@SSS"  

        

        // Adjust this value as needed

)

public class chromeRunnerTest {

	
       }

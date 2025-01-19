package RunnerClass;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Suite
@SelectPackages("stepDefinition")

@SelectClasspathResource("features/Login.feature") // Adjust the path as needed
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:") 
@Execution(ExecutionMode.CONCURRENT)

public class RunnerClass {
}

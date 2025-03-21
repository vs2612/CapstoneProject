package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Login.feature",
    glue = "steps",
    plugin = {"pretty", "html:target/cucumber-reports/login.html"}
)
public class LoginRunner extends AbstractTestNGCucumberTests {
}


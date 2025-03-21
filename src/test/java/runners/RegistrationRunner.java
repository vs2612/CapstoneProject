package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Register.feature",
    glue = "steps",
    plugin = {"pretty", "html:target/cucumber-reports/registration.html"}
)
public class RegistrationRunner extends AbstractTestNGCucumberTests {
}


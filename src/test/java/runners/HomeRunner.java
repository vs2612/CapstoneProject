package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Home.feature",
    glue = "steps",
    plugin = {"pretty", "json:target/home.json"}
)
public class HomeRunner extends AbstractTestNGCucumberTests {
}


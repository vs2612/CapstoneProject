package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Search.feature",
    glue = "steps",
    plugin = {"pretty", "html:target/search.html"}
)
public class SearchRunner extends AbstractTestNGCucumberTests {
}

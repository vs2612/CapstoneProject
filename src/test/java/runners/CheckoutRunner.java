package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Checkout.feature",
        glue = "steps",
        plugin = {"pretty", "json:target/checkout.json"}
)
public class CheckoutRunner extends AbstractTestNGCucumberTests {
}

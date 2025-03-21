package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/ProductDetails.feature",
    glue = "steps",
    plugin = {"pretty", "json:target/productdetails.json"}
)
public class ProductDetailsRunner extends AbstractTestNGCucumberTests {
}

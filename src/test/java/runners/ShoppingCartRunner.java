package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/ShoppingCart.feature",
    glue = "steps",
    plugin = {"pretty", "html:target/shoppingcart.json"}
)
public class ShoppingCartRunner extends AbstractTestNGCucumberTests {
}

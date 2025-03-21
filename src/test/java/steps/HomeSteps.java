package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class HomeSteps extends BaseTest {
    private HomePage homePage;

    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        driver.get("https://demo.opencart.com.gr");
        homePage = new HomePage(driver);
    }

    @Then("Recommended For You section should be displayed")
    public void recommended_for_you_section_should_be_displayed() {
        Assert.assertTrue(homePage.areFeaturedProductsDisplayed(), "Featured section not displayed!");
    }
    
    @Then("MacBookAir should be displayed")
    public void macbook_air_should_be_displayed() {
        Assert.assertTrue(homePage.isFeatured3Displayed(), "MacBookAir not displayed in Recommended section!");
    }

    @When("User searches for {string}")
    public void user_searches_for(String productName) {
        homePage.searchProduct(productName);
    }
}

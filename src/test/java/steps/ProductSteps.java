package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductDetailsPage;

public class ProductSteps extends BaseTest {
    private ProductDetailsPage productDetailsPage;

    @Given("User searches product on the home page")
    public void user_is_on_the_home_page() {
        driver.get("https://demo.opencart.com.gr");
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @When("User searches for {string} and opens product page")
    public void user_searches_for_and_opens_product_page(String productName) {
        productDetailsPage.searchAndClickProduct(productName);
    }

    @Then("Product title should be displayed")
    public void product_title_should_be_displayed() {
        Assert.assertFalse(productDetailsPage.getProductTitle().isEmpty(), "Product title is missing!");
    }

    @Then("Product description should be displayed")
    public void product_description_should_be_displayed() {
        Assert.assertFalse(productDetailsPage.getProductDescription().isEmpty(), "Product description is missing!");
    }

    @Then("Product price should be displayed")
    public void product_price_should_be_displayed() {
        Assert.assertFalse(productDetailsPage.getProductPrice().isEmpty(), "Product price is missing!");
    }

    @Then("Product images should be displayed")
    public void product_images_should_be_displayed() {
        Assert.assertTrue(productDetailsPage.areProductImagesDisplayed(), "Product images are missing!");
    }

    @Then("Product availability should be displayed")
    public void product_availability_should_be_displayed() {
        Assert.assertFalse(productDetailsPage.getProductAvailability().isEmpty(), "Product availability info is missing!");
    }
    
    @When("User adds the product to the wishlist")
    public void user_adds_the_product_to_the_wishlist() {
        productDetailsPage.addToWishlist();
    }

    @Then("Product should be added to the wishlist successfully")
    public void product_should_be_added_to_the_wishlist() {
        Assert.assertTrue(productDetailsPage.validateWishlist(), "Product was not added to the wishlist!");
    }
}

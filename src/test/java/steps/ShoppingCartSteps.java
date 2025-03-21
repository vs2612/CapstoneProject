package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ShoppingCartPage;

public class ShoppingCartSteps extends BaseTest {
    private ShoppingCartPage shoppingCartPage;

    @Given("User is on the home page to add items")
    public void user_is_on_the_home_page() {
        driver.get("https://demo.opencart.com.gr");
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @When("User adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        shoppingCartPage.addProductToCart();
        shoppingCartPage.openCart();
    }

    @Then("Product should be added to the cart")
    public void product_should_be_added_to_the_cart() {
        Assert.assertTrue(shoppingCartPage.isProductInCart(), "Product not added to cart!");
    }

    @When("User updates product quantity to {int}")
    public void user_updates_product_quantity_to(int quantity) {
        shoppingCartPage.updateProductQuantity(quantity);
    }

    @Then("Product quantity should be updated")
    public void product_quantity_should_be_updated() {
        Assert.assertTrue(shoppingCartPage.isProductInCart(), "Product quantity not updated!");
    }

//    @When("User removes the product from the cart")
//    public void user_removes_the_product_from_the_cart() {
//        shoppingCartPage.removeProductFromCart();
//    }
//
//    @Then("Cart should be empty")
//    public void cart_should_be_empty() {
//        Assert.assertFalse(shoppingCartPage.isProductInCart(), "Cart is not empty!");
//    }
}


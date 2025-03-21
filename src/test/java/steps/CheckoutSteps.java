package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddToCartPage;
import pages.CheckoutPage;

public class CheckoutSteps extends BaseTest {
    private AddToCartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("User is on the home page and logs in")
    public void user_is_on_home_page_and_logs_in() {
        driver.get("https://demo.opencart.com.gr");
        cartPage = new AddToCartPage(driver);
        cartPage.login("vs6686949@gmail.com", "vishalsingh");
    }
    
    @When("User visits home page")
    public void user_visits_home_page()
    {
    	cartPage.home();
    }

    @When("User adds MacBook to the cart")
    public void user_adds_macbook_to_cart() {
        cartPage.addItemToCart();
    }

    @When("User navigates to the shopping cart")
    public void user_navigates_to_cart() {
        cartPage.goToCart();
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @When("User fills in billing details with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void user_fills_billing_details(String fName, String lName, String company, String addr1, String addr2, String city, String pCode) {
        checkoutPage.enterBillingDetails(fName, lName, company, addr1, addr2, city, pCode);
    }

    @When("User agrees to the terms and conditions")
    public void user_agrees_to_terms() {
        checkoutPage.agreeToTerms();
    }

    @Then("User attempts to proceed with payment")
    public void user_attempts_payment() {
        boolean paymentSuccess = checkoutPage.proceedWithPayment();
        if (!paymentSuccess) {
            System.out.println("❌ Payment method required! Stopping test.");
        } else {
            System.out.println("✅ Proceeded to payment step successfully.");
        }
    }
}

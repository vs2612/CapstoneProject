package steps;


import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

public class SearchSteps extends BaseTest {
    private SearchPage searchPage;

    @When("User performs a product search for {string}")
    public void user_searches_for(String product) {
        searchPage = new SearchPage(driver);
        searchPage.searchForProduct(product);
    }

    @Then("User filters results by first category")
    public void user_filters_results_by_first_category() {
        searchPage.filterByCategory();
    }

    @Then("User sorts results by highest price to lowest")
    public void user_sorts_results_by_highest_price_to_lowest() {
        searchPage.sortByPrice();
    }

    @Then("User sorts results by highest rating to lowest")
    public void user_sorts_results_by_highest_rating_to_lowest() {
        searchPage.sortByRating();
    }
}


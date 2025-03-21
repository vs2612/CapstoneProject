package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators

    private By searchBox = By.xpath("//input[contains(@name, 'search') or @placeholder='Search']");

    private By categoryDropdown = By.xpath("//select[contains(@id, 'category') or contains(@class, 'category-select')]");
    private By firstCategoryOption = By.xpath("(//select[contains(@id, 'category')]//option)[1]");

    private By searchButton = By.id("button-search");

    private By sortDropdown = By.xpath("//select[contains(@id, 'sort')]");
    private By sortByPrice = By.xpath("//select[contains(@id, 'sort')]/option[contains(text(), 'Price')]");
    private By sortByRating = By.xpath("//select[contains(@id, 'sort')]/option[contains(text(), 'Rating')]");

    // Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to search for a product
    public void searchForProduct(String product) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(product + Keys.ENTER);
    }

    // Method to filter by category
    public void filterByCategory() {
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        category.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(firstCategoryOption));
        option.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Method to sort by highest price to lowest
    public void sortByPrice() {
        WebElement sort = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        sort.click();
        wait.until(ExpectedConditions.elementToBeClickable(sortByPrice)).click();
    }

    // Method to sort by highest rating to lowest
    public void sortByRating() {
        WebElement sort = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        sort.click();
        wait.until(ExpectedConditions.elementToBeClickable(sortByRating)).click();
    }
}


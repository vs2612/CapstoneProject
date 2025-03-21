package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBox = By.xpath("//*[@id=\"search\"]/input");
    private By featuredImage1 = By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a/img");
    private By featuredImage2 = By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/div[1]/a/img");
    private By featuredImage3 = By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div/div[1]/a/img"); // Fixed incorrect div index
    private By featuredImage4 = By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[1]/a/img"); // Fixed incorrect div index

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to validate all featured products before performing search
    public boolean areFeaturedProductsDisplayed() {
        return isFeatured1Displayed() && isFeatured2Displayed() && isFeatured3Displayed() && isFeatured4Displayed();
    }

    public boolean isFeatured1Displayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(featuredImage1)).isDisplayed();
    }

    public boolean isFeatured2Displayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(featuredImage2)).isDisplayed();
    }

    public boolean isFeatured3Displayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(featuredImage3)).isDisplayed();
    }

    public boolean isFeatured4Displayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(featuredImage4)).isDisplayed();
    }

    // Search for a product only after validating featured products
    public void searchProduct(String productName) {
        if (areFeaturedProductsDisplayed()) {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            searchInput.clear();
            searchInput.sendKeys(productName + Keys.ENTER);  // Pressing Enter instead of submit()
        } else {
            throw new RuntimeException("Featured products are not displayed, cannot proceed with search!");
        }
    }
}

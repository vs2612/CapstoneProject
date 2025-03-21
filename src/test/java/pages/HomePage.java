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
 

    private By searchBox = By.xpath("//input[contains(@name, 'search') or @placeholder='Search']");

    private By featuredImages = By.xpath("//div[contains(@class, 'product-layout')]//div[@class='image']/a/img");
    private By featuredImage1 = By.xpath("(//div[contains(@class, 'product-layout')]//div[@class='image']/a/img)[1]");
    private By featuredImage2 = By.xpath("(//div[contains(@class, 'product-layout')]//div[@class='image']/a/img)[2]");
    private By featuredImage3 = By.xpath("(//div[contains(@class, 'product-layout')]//div[@class='image']/a/img)[3]");
    private By featuredImage4 = By.xpath("(//div[contains(@class, 'product-layout')]//div[@class='image']/a/img)[4]");


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

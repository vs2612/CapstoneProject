package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (Search & Product Page)

    private By searchBox = By.xpath("//input[contains(@name, 'search') or @placeholder='Search']");

    private By productTitleLink = By.xpath("//div[contains(@class, 'caption')]//h4/a[contains(text(),'MacBook')]");
    private By productTitle = By.xpath("//div[@id='content']//h1");  

    private By productDescription = By.id("tab-description");

    private By productPrice = By.xpath("//div[@id='content']//h2[contains(@class, 'price') or contains(text(), '$')]");

    private By productImages = By.xpath("//div[@id='content']//ul/li/a/img");

    private By productAvailability = By.xpath("//div[@id='content']//ul/li[contains(text(), 'Availability')]");

    private By wishlistButton = By.xpath("//button[contains(@data-original-title, 'Add to Wish List') or contains(@onclick, 'wishlist.add')]");

    private By checkWishlist = By.id("wishlist-total");

    private By itemTable = By.xpath("//div[@id='content']//table[contains(@class, 'wishlist') or contains(@class, 'table')]");



    // Constructor
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // **Search for a product and click the first result**
    public void searchAndClickProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName + Keys.ENTER); // Press Enter to search

        // Click on the first product title that matches "MacBook"
        WebElement productTitleElement = wait.until(ExpectedConditions.elementToBeClickable(productTitleLink));
        productTitleElement.click();
    }

    // **Validate Product Title**
    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    // **Validate Product Description**
    public String getProductDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription)).getText();
    }

    // **Validate Product Price**
    public String getProductPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText();
    }

    // **Validate Product Images (Check if images exist)**
    public boolean areProductImagesDisplayed() {
        List<WebElement> images = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productImages));
        return !images.isEmpty(); // Return true if at least one image is found
    }

    // **Validate Product Availability**
    public String getProductAvailability() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productAvailability)).getText();
    }
    
    public void addToWishlist()
    {
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistButton)).click();
    	 
    }
    
    public boolean validateWishlist()
    {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(checkWishlist)).click();
    	WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTable));
    	
    	if(table.isDisplayed())
    	{
    		return true;
    	}
    	else
    		return false;
    }
    
    
}

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
    private By searchBox = By.xpath("//*[@id=\"search\"]/input");
    private By productTitleLink = By.xpath("//div[@class='caption']/h4/a[contains(text(),'MacBook')]");
    private By productTitle = By.xpath("//*[@id='content']/div/div[2]/h1");
    private By productDescription = By.xpath("//*[@id='tab-description']");
    private By productPrice = By.xpath("//*[@id='content']/div/div[2]/ul[2]/li/h2");
    private By productImages = By.xpath("//*[@id='content']/div/div[1]/ul/li/a/img");
    private By productAvailability = By.xpath("//*[@id='content']/div/div[2]/ul[1]/li[2]");
    private By wishlistButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]");
    private By checkWishlist = By.id("wishlist-total");
    private By itemTable = By.xpath("//*[@id=\"content\"]/div[1]");


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

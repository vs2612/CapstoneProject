package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addToCartButton = By.xpath("//button[contains(@onclick, 'cart.add')]");
    private By cartButton = By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span");
    private By cartForm = By.xpath("//*[@id=\"content\"]/form");
    private By quantityInput = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input");
    private By updateButton = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/span/button[1]");
    private By removeButton = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/span/button[2]");

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Add product to cart
    public void addProductToCart() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addButton.click();
    }

    // Open shopping cart
    public void openCart() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cart.click();
    }

    // Validate product is added
    public boolean isProductInCart() {
    	driver.navigate().refresh();
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(cartForm)).isDisplayed();
    }

    // Update product quantity
    public void updateProductQuantity(int quantity) {
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        
        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateBtn.click();
    }


//    public void removeProductFromCart() {
//        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
//        removeBtn.click();
//
//        // Click the update button after removing the product
//        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
//        updateBtn.click();
//
//        // Wait for the cart form to disappear (cart should be empty)
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartForm));
//    }

}


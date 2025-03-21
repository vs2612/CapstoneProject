package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
 // More resilient locators

    private By loginButton = By.xpath("//ul[contains(@class, 'nav')]/li/a[contains(text(), 'My Account')]");

    private By loginLink = By.xpath("//ul[contains(@class, 'nav')]/li//ul/li/a[contains(text(), 'Login')]");

    private By emailField = By.id("input-email");

    private By passwordField = By.id("input-password");

    private By submitLogin = By.xpath("//div[@id='content']//form//input[@type='submit' and @value='Login']");

    private By yourStore = By.xpath("//div[@id='logo']//h1/a[contains(text(), 'Your Store')]");

    private By addToCartButton = By.xpath("//div[@id='content']//div[contains(@class, 'product-thumb')]//button[contains(@onclick, 'cart.add')]");

    private By cartButton = By.xpath("//ul[contains(@class, 'top-links')]//a[contains(@href, 'checkout/cart')]");

    private By checkoutButton = By.xpath("//a[contains(text(), 'Checkout')]");


    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submitLogin)).click();
    }
    
    public void home() {
        wait.until(ExpectedConditions.elementToBeClickable(yourStore)).click();
    }

    public void addItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}

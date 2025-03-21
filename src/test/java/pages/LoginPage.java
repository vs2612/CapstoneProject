package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By myAccount = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]");
    private By loginLink = By.linkText("Login");
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input[1]");
    private By myAccountHeader = By.xpath("//h2[contains(text(),'My Account')]");
    private By loginErrorMessage = By.xpath("//*[@id='account-login']/div[1]");  // Error message
    private By forgottenPasswordLink = By.linkText("Forgotten Password");
    private By continueButton = By.xpath("//*[@id='content']/form/div/div[2]/input");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().deleteAllCookies();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Actions
    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();  // Click 'My Account'
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();   // Click 'Login'
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.click();  // Focus on email input
        emailElement.sendKeys(Keys.CONTROL + "a");  // Select all text
        emailElement.sendKeys(Keys.DELETE);  // Delete selected text
        emailElement.clear();  // Clear the input field
        emailElement.sendKeys(email);  // Enter email
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.click();  // Focus on password input
        passwordElement.sendKeys(Keys.CONTROL + "a");  // Select all text
        passwordElement.sendKeys(Keys.DELETE);  // Delete selected text
        passwordElement.clear();  // Clear the input field
        passwordElement.sendKeys(password);  // Enter password
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();  // Click 'Login'
    }

    public boolean isLoginSuccessful() {
        try {
            boolean urlCheck = wait.until(ExpectedConditions.urlContains("route=account/account"));
            WebElement accountHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeader));
            return urlCheck && accountHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickForgottenPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgottenPasswordLink)).click();
    }

    public void resetPassword(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public boolean isResetEmailSent() {
        try {
            WebElement resetConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            return resetConfirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

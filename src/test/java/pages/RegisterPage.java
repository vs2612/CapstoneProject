package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By myAccount = By.xpath("//a[contains(@href, 'route=account')]");
    private By registerLink = By.xpath("//a[contains(text(), 'Register')]");
    private By firstNameField = By.id("input-firstname");  // ID is stable, no change
    private By lastNameField = By.id("input-lastname");  // ID is stable, no change
    private By emailField = By.id("input-email");  // ID is stable, no change
    private By telephoneField = By.id("input-telephone");  // ID is stable, no change
    private By passwordField = By.id("input-password");  // ID is stable, no change
    private By confirmPasswordField = By.id("input-confirm");  // ID is stable, no change
    private By privacyPolicyCheckbox = By.xpath("//input[@type='checkbox' and contains(@name, 'agree')]");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");


    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().deleteAllCookies();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void navigateToRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        
        // Validate if the URL contains "route=account/register"
//        wait.until(ExpectedConditions.urlContains("route=account/register"));
    }


    public void enterFirstName(String firstName) {
        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }
    
    public void enterTelephone(String telephone)
    {
    	WebElement telephoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(telephoneField));
    	telephoneElement.clear();
    	telephoneElement.sendKeys(telephone);
    }
    
    public void enterConfirmPassword(String confirmPassword)
    {
    	WebElement confirmPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
    	confirmPasswordElement.clear();
    	confirmPasswordElement.sendKeys(confirmPassword);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void acceptPrivacyPolicy() {
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyCheckbox)).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
    
    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.urlContains("route=account/success"));
             
    }
    
    public boolean isDuplicateEmailErrorDisplayed() {
        try {
            // Check if the error element exists before waiting for its visibility
            if (driver.findElements(By.xpath("//*[@id=\"account-register\"]/div[1]")).size() > 0) {
                WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"account-register\"]/div[1]")));
                return errorElement.getText().contains("Warning: E-Mail Address is already registered!");
            }
            return false; // Element was not found, meaning no error message appeared
        } catch (Exception e) {
            return false; // In case of any timeout or issue, return false
        }
    }




}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By useNewAddressCheckbox = By.xpath("//input[@name='payment_address' and @value='new']");

 // Using 'By.name' where applicable (already optimal)
 private By firstName = By.name("firstname");
 private By lastName = By.name("lastname");
 private By company = By.name("company");
 private By address1 = By.name("address_1");
 private By address2 = By.name("address_2");
 private By city = By.name("city");
 private By postCode = By.name("postcode");
 private By countryDropdown = By.name("country_id");
 private By regionDropdown = By.name("zone_id");

 // Improved XPath using ID directly
 private By billingContinueButton = By.id("button-payment-address");

 // Making terms checkbox XPath more resilient
 private By termsCheckbox = By.xpath("//div[@id='collapse-payment-method']//input[@type='checkbox']");

 // Improved XPath using ID directly
 private By paymentContinueButton = By.id("button-payment-method");

 // Improved XPath with a better structure
 private By paymentWarning = By.xpath("//div[@id='collapse-payment-method']//div[contains(@class, 'alert')]");


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void enterBillingDetails(String fName, String lName, String comp, String addr1, String addr2, String cityName, String pCode) {
    	
    	WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(useNewAddressCheckbox));
    	
    	if(checkbox.isDisplayed())
    	{
    		
    		checkbox.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
            driver.findElement(lastName).sendKeys(lName);
            driver.findElement(company).sendKeys(comp);
            driver.findElement(address1).sendKeys(addr1);
            driver.findElement(address2).sendKeys(addr2);
            driver.findElement(city).sendKeys(cityName);
            driver.findElement(postCode).sendKeys(pCode);

            // Select Country
            WebElement country = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
            new Select(country).selectByVisibleText("United Kingdom");

            // Select Region/State (assuming first option)
            WebElement region = wait.until(ExpectedConditions.elementToBeClickable(regionDropdown));
            new Select(region).selectByIndex(1);  // Adjust index if needed

            // Click Continue
            wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton)).click();
    	}
    	else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
            driver.findElement(lastName).sendKeys(lName);
            driver.findElement(company).sendKeys(comp);
            driver.findElement(address1).sendKeys(addr1);
            driver.findElement(address2).sendKeys(addr2);
            driver.findElement(city).sendKeys(cityName);
            driver.findElement(postCode).sendKeys(pCode);

            // Select Country
            WebElement country = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
            new Select(country).selectByVisibleText("United Kingdom");

            // Select Region/State (assuming first option)
            WebElement region = wait.until(ExpectedConditions.elementToBeClickable(regionDropdown));
            new Select(region).selectByIndex(1);  // Adjust index if needed

            // Click Continue
            wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton)).click();
    	}
    	
    }

    public void agreeToTerms() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
    }

    public boolean proceedWithPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentContinueButton)).click();
        
        // Check if warning appears
        try {
            WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentWarning));
            return false;  // Warning means payment failed
        } catch (Exception e) {
            return true;  // No warning, proceed normally
        }
    }
}

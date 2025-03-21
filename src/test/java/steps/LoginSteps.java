package steps;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utility.ExcelUtils;

import java.io.IOException;

public class LoginSteps extends BaseTest {
    private LoginPage loginPage;
    private String filePath = "src/test/resources/LoginData.xlsx";

    
    @Before
    public void setup() {
        try {
            System.out.println("Initializing WebDriver...");
            initializeDriver("chrome");
            System.out.println("Driver initialized successfully.");
            driver.get("https://demo.opencart.com.gr");
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Given("User has registered and is on the login page")
    public void user_has_registered_and_is_on_the_login_page() {
        loginPage.navigateToLoginPage();
    }

    @When("User enters login credentials from Excel {int}")
    public void user_enters_login_credentials_from_excel(int rowIndex) throws IOException {
        ExcelUtils.loadExcel(filePath, "Sheet1");

        if (rowIndex > ExcelUtils.getRowCount()) {
            throw new IllegalArgumentException("Row index out of range!");
        }

        String email = ExcelUtils.getCellData(rowIndex, 1);
        String password = ExcelUtils.getCellData(rowIndex, 2);

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        if (loginPage.isLoginSuccessful()) {
            Assert.assertTrue(true, "Login successful");
        } else {
            Assert.fail("Login failed unexpectedly!");
        }
    }

    @Then("User should be redirected to reset password page and reset password")
    public void user_should_be_redirected_to_reset_password_page_and_reset_password() {
        if (loginPage.isLoginFailed()) {
            loginPage.clickForgottenPassword();
            loginPage.resetPassword(ExcelUtils.getCellData(2, 1)); // Use the same email from Excel
            Assert.assertTrue(loginPage.isResetEmailSent(), "Password reset email was not sent!");
        } else {
            Assert.fail("Login did not fail as expected!");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            closeDriver();
        } else {
            System.err.println("WebDriver is null. Skipping teardown.");
        }
    }

}

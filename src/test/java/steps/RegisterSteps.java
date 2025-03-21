package steps;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.RegisterPage;
import utility.ExcelUtils;

import java.io.IOException;

public class RegisterSteps extends BaseTest {
    private RegisterPage registerPage;
    private String filePath = "src/test/resources/RegistrationData.xlsx";

    @Before
    public void setup() {
        initializeDriver("chrome");
        driver.get("https://demo.opencart.com.gr");
        registerPage = new RegisterPage(driver);
    }

    @Given("User is on the registration page")
    public void user_is_on_the_registration_page() {
        registerPage.navigateToRegisterPage();
    }

    @When("User enters registration details from Excel {int}")
    public void user_enters_registration_details_from_excel(int rowIndex) throws IOException {
        ExcelUtils.loadExcel(filePath, "Sheet1");

        if (rowIndex > ExcelUtils.getRowCount()) {
            throw new IllegalArgumentException("Row index out of range!");
        }

        String firstName = ExcelUtils.getCellData(rowIndex, 0);
        String lastName = ExcelUtils.getCellData(rowIndex, 1);
        String email = ExcelUtils.getCellData(rowIndex, 2);
        String telephone = ExcelUtils.getCellData(rowIndex, 3);
        String password = ExcelUtils.getCellData(rowIndex, 4);
        String confirmPassword = ExcelUtils.getCellData(rowIndex, 5);

        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterTelephone(telephone);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
    }


    @When("User accepts the privacy policy and submits the form")
    public void user_accepts_the_privacy_policy_and_submits_the_form() {
        registerPage.acceptPrivacyPolicy();
        registerPage.clickContinue();
    }

    @Then("User validates the registration outcome")
    public void user_validates_the_registration_outcome() {
        if (registerPage.isDuplicateEmailErrorDisplayed()) {
            System.out.println("Duplicate email detected. Checking error message...");
            Assert.assertTrue(registerPage.isDuplicateEmailErrorDisplayed(), "Duplicate email error message was not displayed!");
        } else {
            System.out.println("No duplicate email detected. Checking successful registration...");
            Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");
        }
    }


    @After
    public void tearDown() {
    	driver.manage().deleteAllCookies();
        closeDriver();
    }
}

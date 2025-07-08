package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignUpPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidEmailRegistrationTest extends BaseTest {

    @Test
    public void invalidEmailRegistration() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");
        Thread.sleep(2000);
        signUpPage.enterFirstName("John");
        signUpPage.enterLastName("Doe");
        signUpPage.enterEmail("abc");
        signUpPage.enterPassword("Password123");
        signUpPage.enterPasswordConfirmation("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getEmailValidationMessage();
        assertTrue(errorMessage.contains("Please enter an email address"), "Error message does not contain expected text");
    }
}
package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignUpPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyRegistrationTest extends BaseTest {

    @Test
    public void emptyFirstName() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");
        Thread.sleep(2000);
        signUpPage.enterLastName("Doe");
        signUpPage.enterEmail("john.doe@example.com");
        signUpPage.enterPassword("Password123");
        signUpPage.enterPasswordConfirmation("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getFirstNameValidationMessage();
        assertEquals("Please fill out this field.", errorMessage);
    }

    @Test
    public void emptyLastName() {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");

        signUpPage.enterFirstName("John");
        signUpPage.enterEmail("john.doe@example.com");
        signUpPage.enterPassword("Password123");
        signUpPage.enterPasswordConfirmation("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getLastNameValidationMessage();
        assertEquals("Please fill out this field.", errorMessage);
    }

    @Test
    public void emptyEmail() {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");

        signUpPage.enterFirstName("John");
        signUpPage.enterLastName("Doe");
        signUpPage.enterPassword("Password123");
        signUpPage.enterPasswordConfirmation("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getEmailValidationMessage();
        assertEquals("Please fill out this field.", errorMessage);
    }

    @Test
    public void emptyPassword() {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");

        signUpPage.enterFirstName("John");
        signUpPage.enterLastName("Doe");
        signUpPage.enterEmail("john.doe@example.com");
        signUpPage.enterPasswordConfirmation("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getPasswordValidationMessage();
        assertEquals("Please fill out this field.", errorMessage);
    }

    @Test
    public void emptyPasswordConfirmation() {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:4000/sign_up");

        signUpPage.enterFirstName("John");
        signUpPage.enterLastName("Doe");
        signUpPage.enterEmail("john.doe@example.com");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSignUpButton();

        String errorMessage = signUpPage.getPasswordConfirmationValidationMessage();
        assertEquals("Please fill out this field.", errorMessage);
    }
}
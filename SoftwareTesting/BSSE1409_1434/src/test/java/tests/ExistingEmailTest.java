package tests;

import Pages.SignInPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import Pages.SignUpPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExistingEmailTest extends BaseTest {

    @Test
    public void existingEmail() {
        driver.manage().window().setSize(new Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterFirstName("john");
        signUpPage.enterLastName("john");
        signUpPage.enterEmail("john@pheonix-trello.com");
        signUpPage.enterPassword("john123");
        signUpPage.enterPasswordConfirmation("john123");
        signUpPage.clickSignUpButton();

        assertEquals("Email already taken", signUpPage.getErrorMessage());
    }
}
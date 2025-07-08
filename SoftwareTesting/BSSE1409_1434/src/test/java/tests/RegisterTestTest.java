package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.SignUpPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTestTest extends BaseTest {

    @Test
    public void registerTest() {
        driver.manage().window().setSize(new Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        assertTrue(signInPage.isCreateAccountLinkDisplayed());
        assertEquals("Trello tribute for educational purposes crafted with ♥ for Diacode by @bigardone", signInPage.getFooterText());
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.isFirstNameFieldDisplayed());
        assertTrue(signUpPage.isLastNameFieldDisplayed());
        assertTrue(signUpPage.isEmailFieldDisplayed());
        assertTrue(signUpPage.isPasswordFieldDisplayed());
        assertTrue(signUpPage.isPasswordConfirmationFieldDisplayed());
        assertTrue(signUpPage.isSignUpButtonDisplayed());
    }
}
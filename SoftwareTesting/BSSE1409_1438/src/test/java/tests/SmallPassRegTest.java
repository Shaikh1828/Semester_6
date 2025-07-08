package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.SignUpPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmallPassRegTest extends BaseTest {

    @Test
    public void smallPassReg() {
        driver.manage().window().setSize(new Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        assertTrue(signInPage.isCreateAccountLinkDisplayed());
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterFirstName("sami");
        signUpPage.enterLastName("sami");
        signUpPage.enterEmail("sami@gmail.com");
        signUpPage.enterPassword("1234");
        signUpPage.enterPasswordConfirmation("1234");
        signUpPage.clickSignUpButton();

        assertEquals("should be at least 5 character(s)", signUpPage.getErrorMessage());
    }
}
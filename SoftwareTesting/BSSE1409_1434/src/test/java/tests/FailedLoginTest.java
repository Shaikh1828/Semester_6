package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailedLoginTest extends BaseTest {

    @Test
    public void failedLogin() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        signInPage.enterPassword("1234");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        assertEquals("Invalid email or password", signInPage.getErrorMessage());

        signInPage.enterEmail("abc");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        assertEquals("Invalid email or password", signInPage.getErrorMessage());
    }
}
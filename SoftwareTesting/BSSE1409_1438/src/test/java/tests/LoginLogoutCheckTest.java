package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.HomePage;
import Pages.SignInPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginLogoutCheckTest extends BaseTest {

    @Test
    public void loginLogoutCheck() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(550, 692));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        HomePage homePage = new HomePage(driver);
        assertEquals("My boards", homePage.getMyBoardsHeaderText());
        assertTrue(homePage.areBoardsNavElementsPresent());
        assertTrue(homePage.areSignOutIconsPresent());
        assertTrue(homePage.areThirdSpanElementsPresent());
        assertTrue(homePage.areLogoElementsPresent());
        assertTrue(homePage.areAddNewBoardElementsPresent());

        homePage.clickSignOutLink();
        assertEquals("Sign in | Phoenix Trello", driver.getTitle());
    }
}
package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.HomePage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardPageTest extends BaseTest {

    @Test
    public void boardPage() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(795, 697));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        HomePage homePage = new HomePage(driver);
        assertEquals("My boards", homePage.getMyBoardsHeaderText());
        assertTrue(homePage.areBoardsNavElementsPresent());
        assertTrue(homePage.areLogoElementsPresent());
        assertEquals("John Doe", homePage.getUserNameText());
        assertTrue(homePage.areSignOutIconsPresent());
        assertTrue(homePage.areAddNewBoardElementsPresent());

        homePage.clickSignOutLink();
    }
}
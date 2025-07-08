package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.HomePage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyBoardCreationTest extends BaseTest {

    @Test
    public void emptyBoardCreation() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1854, 1041));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        HomePage homePage = new HomePage(driver);
        homePage.clickAddNewBoardButton();

        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickSubmitButton();

        assertEquals("Please fill out this field.", boardCreationPage.getBoardNameErrorMessage());
    }
}
package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.HomePage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBoardTest extends BaseTest {

    @Test
    public void createBoard() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(795, 697));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        HomePage homePage = new HomePage(driver);
        homePage.clickAddNewBoardButton();

        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.enterBoardName("NewBoardCreated");
        boardCreationPage.clickSubmitButton();

        assertEquals("My boards", boardCreationPage.getBoardHeaderText());

        homePage.clickBoardInNavigation("NewBoardCreated");
        assertTrue(homePage.isBoardPresentInNavigation("NewBoardCreated"));
    }
}
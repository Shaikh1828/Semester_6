package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewBoardsTest extends BaseTest {

    @Test
    public void viewBoards() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickBoardLink();

        assertTrue(boardCreationPage.isBoardsNavPresent(), "Boards navigation element is not present");
        boardCreationPage.clickBoardsNav();

        assertTrue(boardCreationPage.isViewAllBoardsLinkPresent(), "View all boards link is not present");
        boardCreationPage.clickViewAllBoardsLink();

        assertEquals("My boards", boardCreationPage.getHeaderText(), "Header text does not match expected value");
    }
}
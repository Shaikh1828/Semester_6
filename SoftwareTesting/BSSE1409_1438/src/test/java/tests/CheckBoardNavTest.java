package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckBoardNavTest extends BaseTest {

    @Test
    public void checkBoardNav() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickAddNewBoardButton();
        boardCreationPage.enterBoardName("BoardNav");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickBoardsNav();
        boardCreationPage.clickViewAllBoardsLink();
        boardCreationPage.clickBoardsNav();
        boardCreationPage.clickBoardLinkByName("BoardNav");

        assertEquals("BoardNav", boardCreationPage.getBoardHeaderText(), "Board header text does not match expected value");
    }
}
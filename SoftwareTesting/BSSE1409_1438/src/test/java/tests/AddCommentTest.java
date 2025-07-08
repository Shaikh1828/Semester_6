package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommentTest extends BaseTest {

    @Test
    public void addComment() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickAddNewBoardButton();
        boardCreationPage.enterBoardName("CheckComment");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericBoardLink();
        boardCreationPage.enterListName("list");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickAddNewCardLink();
        boardCreationPage.enterCardName("abc");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericCard();
        boardCreationPage.enterComment("This is my comment");
        boardCreationPage.clickSubmitButton();

        assertEquals("This is my comment", boardCreationPage.getCommentText(), "Comment text does not match expected value");
    }

    @Test
    public void addEmptyComment() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickAddNewBoardButton();
        boardCreationPage.enterBoardName("EmptyCommentTest");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericBoardLink();
        boardCreationPage.enterListName("list");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickAddNewCardLink();
        boardCreationPage.enterCardName("abc");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericCard();
        boardCreationPage.enterComment(""); // Empty comment
        boardCreationPage.clickSubmitButton();

        assertEquals("Please fill out this field.", boardCreationPage.getCommentErrorMessage(), "Error message does not match expected value");
    }
}
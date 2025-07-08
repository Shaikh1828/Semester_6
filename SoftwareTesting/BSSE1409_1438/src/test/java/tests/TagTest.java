package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagTest extends BaseTest {

    @Test
    public void tag() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickAddNewBoardButton();
        boardCreationPage.enterBoardName("TagTestBoard");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericBoardLink();
        boardCreationPage.enterListName("Tag");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickAddNewCardLink();
        boardCreationPage.enterCardName("abc");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickGenericCard();
        boardCreationPage.clickAddTagButton();
        boardCreationPage.clickGreenTag();
        boardCreationPage.clickRedTag();
        boardCreationPage.clickBlueTag();

        assertTrue(boardCreationPage.isTagPresent("green"), "Green tag is not present");
        assertTrue(boardCreationPage.isTagPresent("red"), "Red tag is not present");
        assertTrue(boardCreationPage.isTagPresent("blue"), "Blue tag is not present");

        boardCreationPage.clickTagsLink();
        boardCreationPage.clickTagByClass("red");
        Thread.sleep(1000);

        assertTrue(boardCreationPage.isTagPresent("green"), "Green tag should still be present");
        assertTrue(boardCreationPage.isTagPresent("blue"), "Blue tag should still be present");
    }
}
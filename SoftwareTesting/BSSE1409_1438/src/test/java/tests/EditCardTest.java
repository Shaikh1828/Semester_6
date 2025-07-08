package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCardTest extends BaseTest {

    @Test
    public void editCard() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickBoardLink();

        boardCreationPage.clickCard("5"); // Assuming card_5 exists
        assertTrue(boardCreationPage.isEditLinkPresent(), "Edit link is not present");
        boardCreationPage.clickEditLink();

        boardCreationPage.enterCardTitle("Sami123");
        boardCreationPage.enterCardDescription("Hello I am Sami");
        boardCreationPage.clickSaveButton();

        assertEquals("Sami123", boardCreationPage.getCardTitle(), "Card title does not match expected value");
        boardCreationPage.clickModalContent();
        assertEquals("Hello I am Sami", boardCreationPage.getCardDescription(), "Card description does not match expected value");
    }
}
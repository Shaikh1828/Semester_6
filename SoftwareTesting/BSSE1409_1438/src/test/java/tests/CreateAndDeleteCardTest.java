package tests;

import base.BaseTest;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateAndDeleteCardTest extends BaseTest {

    @Test
    public void createAndDeleteCard() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);

        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickBoardLink();
        assertTrue(boardCreationPage.areColumnHeadersPresent());

        String cardName = "Card to Delete";

        // Add new card
        boardCreationPage.clickAddNewCardLink();
        boardCreationPage.enterCardName(cardName);
        boardCreationPage.clickSubmitButton();

        // Confirm card is added
        List<String> allCards = boardCreationPage.getAllCardContentTexts();
        assertTrue(allCards.contains(cardName), "Card was not added successfully");

        // Delete the card
        boardCreationPage.deleteCard(cardName);
        Thread.sleep(1000); // Optional wait after delete for UI update

        // Confirm card is deleted
        allCards = boardCreationPage.getAllCardContentTexts();
        assertFalse(allCards.contains(cardName), "Card was not deleted successfully");
    }
}

package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCardTest extends BaseTest {

    @Test
    public void addCardWithNewCardCheck() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(550, 691));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickBoardLink();
        assertTrue(boardCreationPage.areColumnHeadersPresent());

        boardCreationPage.clickAddNewCardLink();
        boardCreationPage.enterCardName("New Card Added");
        boardCreationPage.clickSubmitButton();

        List<String> allCards = boardCreationPage.getAllCardContentTexts();
        assertTrue(allCards.contains("New Card Added"), "Newly added card not found");
    }
}
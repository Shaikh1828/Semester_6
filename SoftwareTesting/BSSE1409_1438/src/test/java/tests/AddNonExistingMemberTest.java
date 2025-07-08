package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddNonExistingMemberTest extends BaseTest {

    @Test
    public void addNonExistingMember() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickBoardLink();

        boardCreationPage.clickAddMemberIcon();
        boardCreationPage.enterMemberEmail("abcd@gm");
        boardCreationPage.submitMemberForm();

        assertEquals("User does not exist", boardCreationPage.getErrorMessage(), "Error message does not match expected value");
    }
}
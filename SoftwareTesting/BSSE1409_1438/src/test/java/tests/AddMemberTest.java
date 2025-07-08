package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import Pages.SignInPage;
import Pages.BoardCreationPage;
import org.openqa.selenium.Dimension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddMemberTest extends BaseTest {

    @Test
    public void addMember() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1000, 683));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
        signInPage.clickSignInButton();
        Thread.sleep(2000);
        BoardCreationPage boardCreationPage = new BoardCreationPage(driver);
        boardCreationPage.clickAddNewBoardButton();
        boardCreationPage.enterBoardName("AddMember");
        boardCreationPage.clickSubmitButton();

        boardCreationPage.clickAddMemberLink();
        boardCreationPage.enterMemberEmail("ab@gmail.com");
        boardCreationPage.submitMemberForm();

        assertTrue(boardCreationPage.isMemberGravatarPresent(), "Member gravatar is not present");

        boardCreationPage.clickAddMemberIcon();
        boardCreationPage.enterMemberEmail("ab@gmail.com");
        boardCreationPage.submitMemberForm();

        assertEquals("Error adding new member", boardCreationPage.getErrorMessage(), "Error message does not match expected value");
    }
}
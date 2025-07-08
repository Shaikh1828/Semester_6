import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.example.SignInPage;
import com.example.DashboardPage;
import com.example.BoardPage;

public class AllTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private BoardPage boardPage;
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        signInPage = new SignInPage(driver);
        dashboardPage = new DashboardPage(driver);
        boardPage = new BoardPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSignIn() {
        signInPage.open();
        signInPage.clickSignIn();
    }

    @Test
    public void testCreateBoard() {
        signInPage.open();
        signInPage.clickSignIn();
        dashboardPage.createBoard("test");
    }

    @Test
    public void testViewBoards() {
        signInPage.open();
        signInPage.clickSignIn();
        dashboardPage.navigateToBoardsPage();
    }
    @Test
    public void testCreateList() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.createList("Mylist2");
    }

    @Test
    public void testCreateCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.createCard("1", "mycard");
    }
    @Test
    public void testSignOut() {
        signInPage.open();
        signInPage.clickSignIn();
        dashboardPage.signOut();
    }
    @Test
    public void testSafeDeleteCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("4", "abc-board");
        boardPage.createCard("4", "tempCard");
        boardPage.deleteCard("4");
        boardPage.deleteCard("4");
        boardPage.createCard("4", "newCardAfter");
    }
    @Test
    public void testAddNullMember() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.addMemberToBoard("bsse@iit.du.ac.bd");
    }
    @Test
    public void testAddCommentToSpecificCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.addCommentToCard("mycard", "this is my first comment");
    }
    @Test
    public void testEditCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.editCard("mycard", "This is my updated card");
    }
    @Test
    public void testAddTagsToCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.addTagToCard("mycard", "red");  // or "blue", "red", etc.
    }
    @Test
    public void testDeleteTagsFromCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.deleteTagFromCard("mycard", "yellow");  // or "blue", "red" etc.
    }

    @Test
    public void testBlankListName() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.tryToAddBlankList();
    }
    @Test
    public void testBlankBoardName() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.tryToAddBlankBoard();
    }

    @Test
    public void testBlankCardName() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.createList("MyList");
        boardPage.tryToAddBlankCard("2");
    }

    @Test
    public void testBlankComment() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "test");
        boardPage.openFirstCard();
        boardPage.tryToAddBlankComment();
    }

}



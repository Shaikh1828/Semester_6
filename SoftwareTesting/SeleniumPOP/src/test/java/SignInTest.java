import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.example.SignInPage;
import com.example.DashboardPage;
import com.example.BoardPage;

public class SignInTest {
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
        dashboardPage.createBoard("abc board");
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
        boardPage.openBoard("1", "abc-board");
        boardPage.createList("Mylist2");
    }

    @Test
    public void testCreateCard() {
        signInPage.open();
        signInPage.clickSignIn();
        boardPage.openBoard("1", "abc-board");
        boardPage.createCard("3", "mycard");
    }
    @Test
    public void testSignOut() {
        signInPage.open();
        signInPage.clickSignIn();
        dashboardPage.signOut();
    }
}


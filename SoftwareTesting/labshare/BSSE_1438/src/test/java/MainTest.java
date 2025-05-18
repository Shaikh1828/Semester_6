import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

import pages.Home_Page;
import pages.Board_Page;
import pages.Board_Details;

public class MainTest {
    private WebDriver driver;
    private Home_Page homePage;
    private Board_Page boardsPage;
    private Board_Details boardDetailsPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(862, 1064));
        homePage = new Home_Page(driver);
        boardsPage = new Board_Page(driver);
        boardDetailsPage = new Board_Details(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void random() throws InterruptedException {
        driver.get("http://localhost:4000/");

        homePage.clickViewContainer();
        Thread.sleep(3000);
        homePage.clickSignOut();
        homePage.clickSignIn();
        Thread.sleep(3000);

        boardsPage.clickMyBoards();
        boardsPage.clickMyBoards();

        boardsPage.clickUserIcon();
        boardsPage.clickAddNewBoard();
        boardsPage.enterBoardName("hi demo");
        boardsPage.confirmBoardCreation();

        boardDetailsPage.clickBoardInner();
        boardDetailsPage.enterListName("hello");
        boardDetailsPage.confirmListCreation();
    }
}

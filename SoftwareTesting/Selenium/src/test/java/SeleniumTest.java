import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SeleniumTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  private JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void selenium() {
    driver.get("http://localhost:4000/sign_in");
    driver.manage().window().setSize(new Dimension(550, 692));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click the first button (assuming login)
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();

    // Wait for "add_new_board" button to appear and click
    WebElement addBoardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
    addBoardButton.click();

    // Wait and click on h3 > span
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h3 > span"))).click();

    // Wait and click on #boards_nav span
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#boards_nav span"))).click();
  }
}

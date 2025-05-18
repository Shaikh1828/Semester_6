import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.*;

public class Asdf2Test {
  private WebDriver driver;
  private WebDriverWait wait;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void asdf2() {
    driver.get("http://localhost:4000/sign_in");
    driver.manage().window().setSize(new Dimension(550, 693));

    // Wait and click the first button
    WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
    signInButton.click();

    // Wait and click the element with ID 1-asdf > .inner
    WebElement innerElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\31-asdf > .inner")));
    innerElement.click();

    // Wait and click the boards_nav span
    WebElement boardsNavSpan = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#boards_nav span")));
    boardsNavSpan.click();

    // Wait and click the "View all boards" link
    WebElement viewAllBoardsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all boards")));
    viewAllBoardsLink.click();
  }
}

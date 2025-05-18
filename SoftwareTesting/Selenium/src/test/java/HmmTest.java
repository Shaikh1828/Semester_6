import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//  Kaj kore naa

public class HmmTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private JavascriptExecutor js;

  @Before
  public void setUp() {
    // Set the path to geckodriver if it's not in your system PATH
    // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");

    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    js = (JavascriptExecutor) driver;

    // Open the target page
    driver.get("https://your-website-url.com"); // replace with your actual URL
    driver.manage().window().maximize();
  }

  @Test
  public void hmm() {
    // Example navigation steps — adjust as needed for your site

    // Wait for and click on boards nav (if needed)
    WebElement boardsNav = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#boards_nav span")));
    boardsNav.click();

    // Wait for and click the "Add New Board" button
    WebElement addBoardBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_new_board")));

    // Scroll into view if needed
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addBoardBtn);

    // Click the button
    addBoardBtn.click();

    // Add assertions or further steps as needed
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}

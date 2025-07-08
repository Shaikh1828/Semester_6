package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final WebDriver driver;

    // Web elements on the home page
    private final By myBoardsHeader = By.cssSelector("h3 > span");
    private final By boardsNavElements = By.cssSelector("#boards_nav span");
    private final By signOutIcons = By.cssSelector(".fa-sign-out");
    private final By thirdSpanElements = By.cssSelector("span:nth-child(3)");
    private final By logoElements = By.cssSelector(".logo");
    private final By addNewBoardElements = By.id("add_new_board");
    private final By signOutLink = By.cssSelector("#crawler-sign-out > span");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the home page
    public String getMyBoardsHeaderText() {
        WebElement myBoardsHeaderElement = driver.findElement(myBoardsHeader);
        return myBoardsHeaderElement.getText();
    }

    public boolean areBoardsNavElementsPresent() {
        List<WebElement> elements = driver.findElements(boardsNavElements);
        return !elements.isEmpty();
    }

    public boolean areSignOutIconsPresent() {
        List<WebElement> elements = driver.findElements(signOutIcons);
        return !elements.isEmpty();
    }

    public boolean areThirdSpanElementsPresent() {
        List<WebElement> elements = driver.findElements(thirdSpanElements);
        return !elements.isEmpty();
    }

    public String getUserNameText() {
        WebElement userNameElement = driver.findElement(thirdSpanElements);
        return userNameElement.getText();
    }

    public boolean areLogoElementsPresent() {
        List<WebElement> elements = driver.findElements(logoElements);
        return !elements.isEmpty();
    }

    public boolean areAddNewBoardElementsPresent() {
        List<WebElement> elements = driver.findElements(addNewBoardElements);
        return !elements.isEmpty();
    }

    public void clickAddNewBoardButton() {
        WebElement addNewBoardElement = driver.findElement(addNewBoardElements);
        addNewBoardElement.click();
    }

    public void clickBoardInNavigation(String boardName) {
        WebElement boardElement = driver.findElement(By.linkText(boardName));
        boardElement.click();
    }

    public boolean isBoardPresentInNavigation(String boardName) {
        List<WebElement> elements = driver.findElements(By.linkText(boardName));
        return !elements.isEmpty();
    }

    public void clickSignOutLink() {
        WebElement signOutLinkElement = driver.findElement(signOutLink);
        signOutLinkElement.click();
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Board_Page {
    private WebDriver driver;

    public Board_Page(WebDriver driver) {
        this.driver = driver;
    }

    private By myBoardsSpan = By.cssSelector("h3 > span");
    private By userIcon = By.cssSelector(".fa-user");
    private By addNewBoardBtn = By.id("add_new_board");
    private By boardNameInput = By.id("board_name");
    private By confirmButton = By.cssSelector("button");

    public void clickMyBoards() {
        driver.findElement(myBoardsSpan).click();
    }

    public void clickUserIcon() {
        driver.findElement(userIcon).click();
    }

    public void clickAddNewBoard() {
        driver.findElement(addNewBoardBtn).click();
    }

    public void enterBoardName(String name) {
        driver.findElement(boardNameInput).sendKeys(name);
    }

    public void confirmBoardCreation() {
        driver.findElement(confirmButton).click();
    }
}

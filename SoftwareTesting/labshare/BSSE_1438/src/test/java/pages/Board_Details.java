package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Board_Details {
    private WebDriver driver;

    public Board_Details(WebDriver driver) {
        this.driver = driver;
    }

    private By innerBoard = By.cssSelector(".inner");
    private By listNameInput = By.id("list_name");
    private By confirmListButton = By.cssSelector("button");

    public void clickBoardInner() {
        driver.findElement(innerBoard).click();
    }

    public void enterListName(String name) {
        driver.findElement(listNameInput).sendKeys(name);
    }

    public void confirmListCreation() {
        driver.findElement(confirmListButton).click();
    }
}


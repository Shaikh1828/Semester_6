package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BoardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openBoard(String boardId, String boardName) {
        // Example ID: "1-abc-board"
        String boardXPath = String.format("//*[@id='%s-%s']//div[contains(@class, 'inner')]", boardId, boardName);
        WebElement board = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(boardXPath)));
        board.click();
    }

    public void createList(String listName) {
        WebElement addNewList = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner")));
        addNewList.click();
        WebElement listNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name")));
        listNameField.sendKeys(listName);
        driver.findElement(By.cssSelector("button")).click();
    }

    public void createCard(String listId, String cardName) {
        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#list_" + listId + " .add-new")));
        addCardButton.click();
        WebElement cardNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_name")));
        cardNameInput.sendKeys(cardName);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }
}


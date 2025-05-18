package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addNewBoard = By.id("add_new_board");
    private By boardName = By.id("board_name");
    private By createBoardButton = By.cssSelector("button");
    private By boardsNav = By.cssSelector("#boards_nav span");
    private By viewAllBoards = By.linkText("View all boards");
    private By signOutButton = By.cssSelector("#crawler-sign-out > span");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void createBoard(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(addNewBoard)).click();
        driver.findElement(boardName).sendKeys(name);
        driver.findElement(createBoardButton).click();
    }

    public void navigateToBoardsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardsNav)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewAllBoards)).click();
    }

    public void signOut() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("crawler-sign-out")));
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
    }
}

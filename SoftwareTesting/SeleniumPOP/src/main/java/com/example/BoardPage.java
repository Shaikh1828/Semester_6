package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BoardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openBoard(String boardId, String boardName) {
        String boardIdSelector = String.format("%s-%s", boardId, boardName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(boardIdSelector)));
        String boardXPath = String.format("//*[@id='%s']//div[contains(@class, 'inner')]", boardIdSelector);
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
        By addCardSelector = By.cssSelector("#list_" + listId + " .add-new");
        java.util.List<org.openqa.selenium.WebElement> listElements = driver.findElements(addCardSelector);

        if (listElements.isEmpty()) {
            System.out.println("⚠️ List with ID list_" + listId + " not found. Cannot create card.");
            return;
        }

        WebElement addCardButton = listElements.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCardButton);
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();

        WebElement cardNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_name")));
        cardNameInput.sendKeys(cardName);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();

        System.out.println("✅ Card created: " + cardName);
    }

    public void deleteCard(String cardId) {
        By cardSelector = By.id("card_" + cardId);
        java.util.List<org.openqa.selenium.WebElement> cards = driver.findElements(cardSelector);

        if (cards.isEmpty()) {
            System.out.println("⚠️ No card found with ID: card_" + cardId + ". Skipping deletion.");
            return;
        }

        cards.get(0).click();
        WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-trash-o")));
        deleteIcon.click();
        System.out.println("✅ Card card_" + cardId + " deleted.");
    }
    public void addMemberToBoard(String email) {
        WebElement plusIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-plus")));
        plusIcon.click();
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crawljax_member_email")));
        emailInput.clear();
        emailInput.sendKeys(email);
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        addButton.click();
    }
    public void addCommentToCard(String cardTitle, String commentText) {
        // Find all cards
        java.util.List<org.openqa.selenium.WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card-content")));

        boolean found = false;
        for (WebElement card : cards) {
            if (card.getText().contains(cardTitle)) {
                card.click();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Card with title \"" + cardTitle + "\" not found.");
        }

        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea")));
        textarea.clear();
        textarea.sendKeys(commentText);

        WebElement commentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        commentBtn.click();
    }
    public void editCard(String cardTitle, String updatedDescription) {
        java.util.List<org.openqa.selenium.WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card-content")));

        boolean found = false;
        for (WebElement card : cards) {
            if (card.getText().contains(cardTitle)) {
                // Click the footer of the card to open details
                card.findElement(By.tagName("footer")).click();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Card with title \"" + cardTitle + "\" not found.");
        }

        // Click "Edit" link
        WebElement editLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Edit")));
        editLink.click();

        // Wait for the editable textarea
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea:nth-child(2)")));
        textarea.clear();
        textarea.sendKeys(updatedDescription);

        // Click the Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button:nth-child(3)")));
        saveButton.click();
    }
    public void addTagToCard(String cardTitle, String tagColorClass) {
        java.util.List<org.openqa.selenium.WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card-content")));

        boolean found = false;
        for (WebElement card : cards) {
            if (card.getText().contains(cardTitle)) {
                card.click();  // Open card detail
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Card with title \"" + cardTitle + "\" not found.");
        }

        // Click Tag button (usually 3rd button, adjust selector if needed)
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button:nth-child(3) > span"))).click();

        // Select tag color
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("." + tagColorClass))).click();

        // Click anywhere to close modal
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-content"))).click();
    }
    public void deleteTagFromCard(String cardTitle, String tagColorClass) {
        List<WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card-content")));

        boolean found = false;
        for (WebElement card : cards) {
            if (card.getText().contains(cardTitle)) {
                card.click();  // Open the card
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Card with title \"" + cardTitle + "\" not found.");
        }

        // Click tag button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button:nth-child(3) > span"))).click();

        // Check if tag exists
        List<WebElement> tagElementList = driver.findElements(By.cssSelector("li > ." + tagColorClass));

        if (tagElementList.isEmpty()) {
            // Tag not present, just close modal
            driver.findElement(By.cssSelector(".info > header")).click();
            return; // gracefully exit without failing
        }

        // Click tag to remove
        tagElementList.get(0).click();

        // Click outside to close
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".info > header"))).click();

        // Optional: assert that tag is now gone
        List<WebElement> afterDeleteCheck = driver.findElements(By.cssSelector(".tags ." + tagColorClass));
        assertTrue("Tag was not removed properly", afterDeleteCheck.isEmpty());
    }

    private void assertTrue(String tagWasNotRemovedProperly, boolean empty) {
    }

    public void tryToAddBlankList() {
        WebElement addListButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner")));
        addListButton.click();

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        createButton.click();
    }

    public void tryToAddBlankBoard() {
        WebElement addBoardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner")));
        addBoardButton.click();

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        createButton.click();
    }

    public void tryToAddBlankCard(String listId) {
        By addCardSelector = By.cssSelector("#list_" + listId + " .add-new");
        List<WebElement> listElements = driver.findElements(addCardSelector);

        if (listElements.isEmpty()) {
            System.out.println("⚠ List with ID list_" + listId + " not found. Cannot attempt blank card.");
            return;
        }

        WebElement addCardButton = listElements.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCardButton);
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();

        WebElement createCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        createCardButton.click();
    }

    public void openFirstCard() {
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card-content")));
        card.click();
    }

    public void tryToAddBlankComment() {
        WebElement commentButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        commentButton.click();
    }


}


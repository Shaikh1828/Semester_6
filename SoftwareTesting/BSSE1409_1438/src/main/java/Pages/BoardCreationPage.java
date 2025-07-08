package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BoardCreationPage {
    private final WebDriver driver;

    // Web elements on the board creation page
    private final By boardLink = By.cssSelector("#\\35-board1 > .inner");
    private final By columnHeaders = By.cssSelector("h4");
    private final By addNewCardLink = By.linkText("Add a new card...");
    private final By cardContent = By.cssSelector(".card-content");
    private final By cardNameInput = By.id("card_name");
    private final By submitButton = By.cssSelector("button");
    private final By cancelLink = By.linkText("cancel");
    private final By boardNameInput = By.id("board_name");
    private final By boardHeader = By.cssSelector("h3");
    private final By deleteCardButton = By.cssSelector(".delete");
    private final By editLink = By.linkText("Edit");
    private final By cardTitleInput = By.cssSelector("input.card-title");
    private final By cardDescriptionTextarea = By.cssSelector("textarea.card-description");
    private final By saveButton = By.cssSelector("button.save");
    private final By modalContent = By.cssSelector(".md-content");
    private final By cardDescription = By.cssSelector(".description-content");
    private final By addMemberIcon = By.cssSelector(".fa-plus");
    private final By memberEmailInput = By.id("crawljax_member_email");
    private final By errorMessage = By.cssSelector(".error");
    private final By memberGravatar = By.cssSelector("li:nth-child(2) > .react-gravatar");
    private final By addNewBoardButton = By.id("add_new_board");
    private final By addMemberLink = By.cssSelector("li > .add-new");
    private final By listNameInput = By.id("list_name");
    private final By commentTextarea = By.cssSelector("textarea");
    private final By commentText = By.cssSelector(".text");
    private final By addTagButton = By.cssSelector(".button:nth-child(3) > span");
    private final By greenTag = By.cssSelector(".green");
    private final By redTag = By.cssSelector(".red");
    private final By blueTag = By.cssSelector(".blue");
    private final By tagsLink = By.linkText("Tags");
    private final By tagElements = By.cssSelector(".tag");

    public BoardCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the board details
    public void clickBoardLink() {
        WebElement boardLinkElement = driver.findElement(boardLink);
        boardLinkElement.click();
    }

    public void clickBoardLinkById(String boardId) {
        By boardSelector = By.cssSelector("#" + boardId + " > .inner");
        WebElement boardLinkElement = driver.findElement(boardSelector);
        boardLinkElement.click();
    }

    public void clickGenericBoardLink() {
        By boardSelector = By.cssSelector(".inner");
        WebElement boardLinkElement = driver.findElement(boardSelector);
        boardLinkElement.click();
    }

    public boolean areColumnHeadersPresent() {
        List<WebElement> elements = driver.findElements(columnHeaders);
        return !elements.isEmpty();
    }

    public void clickAddNewCardLink() {
        WebElement addNewCardLinkElement = driver.findElement(addNewCardLink);
        addNewCardLinkElement.click();
    }

    public String getCardContentText() {
        WebElement cardContentElement = driver.findElement(cardContent);
        return cardContentElement.getText();
    }

    public List<String> getAllCardContentTexts() {
        List<WebElement> cardElements = driver.findElements(cardContent);
        return cardElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Methods to interact with the card creation form
    public void enterCardName(String cardName) {
        WebElement cardNameElement = driver.findElement(cardNameInput);
        cardNameElement.clear();
        cardNameElement.sendKeys(cardName);
    }

    public void clickSubmitButton() {
        WebElement submitButtonElement = driver.findElement(submitButton);
        submitButtonElement.click();
    }

    public boolean isCancelLinkPresent() {
        List<WebElement> elements = driver.findElements(cancelLink);
        return !elements.isEmpty();
    }

    public void clickCancelLink() {
        WebElement cancelLinkElement = driver.findElement(cancelLink);
        cancelLinkElement.click();
    }

    // Methods retained from previous BoardCreationPage
    public void enterBoardName(String boardName) {
        WebElement boardNameElement = driver.findElement(boardNameInput);
        boardNameElement.clear();
        boardNameElement.sendKeys(boardName);
    }

    public String getBoardHeaderText() {
        WebElement boardHeaderElement = driver.findElement(boardHeader);
        return boardHeaderElement.getText();
    }

    public String getBoardNameErrorMessage() {
        WebElement boardNameElement = driver.findElement(boardNameInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", boardNameElement);
    }

    public String getCardNameErrorMessage() {
        WebElement cardNameElement = driver.findElement(cardNameInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", cardNameElement);
    }

    // Method to delete a card by its content
    public void deleteCard(String cardName) {
        List<WebElement> cardElements = driver.findElements(cardContent);
        for (WebElement cardElement : cardElements) {
            if (cardElement.getText().equals(cardName)) {
                cardElement.click(); // Step 1: Open modal

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                try {
                    // Step 2: Wait for modal to appear
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".md-modal")));

                    // Step 3: Wait for delete button to be clickable
                    WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-modal .delete")));
                    deleteButton.click();

                    // Step 4: Wait until modal disappears (optional, included for robustness)
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".md-modal")));

                    // Step 5: Wait until the card is removed from the list
                    wait.until(ExpectedConditions.invisibilityOf(cardElement));
                    return;

                } catch (Exception e) {
                    throw new RuntimeException("Failed to delete card: " + cardName + ". Error: " + e.getMessage());
                }
            }
        }
        throw new RuntimeException("Card with name " + cardName + " not found");
    }

    // Methods for editing a card
    public void clickCard(String cardId) {
        By cardSelector = By.cssSelector("#card_" + cardId + " > .card-content");
        WebElement cardElement = driver.findElement(cardSelector);
        cardElement.click();
    }

    public void clickGenericCard() {
        WebElement cardElement = driver.findElement(cardContent);
        cardElement.click();
    }

    public boolean isEditLinkPresent() {
        List<WebElement> elements = driver.findElements(editLink);
        return !elements.isEmpty();
    }

    public void clickEditLink() {
        WebElement editLinkElement = driver.findElement(editLink);
        editLinkElement.click();
    }

    public void enterCardTitle(String title) {
        WebElement titleInput = driver.findElement(cardTitleInput);
        titleInput.clear();
        titleInput.sendKeys(title);
    }

    public void enterCardDescription(String description) {
        WebElement descriptionTextarea = driver.findElement(cardDescriptionTextarea);
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(description);
    }

    public void clickSaveButton() {
        WebElement saveButtonElement = driver.findElement(saveButton);
        saveButtonElement.click();
    }

    public String getCardTitle() {
        WebElement titleElement = driver.findElement(By.cssSelector(".info h3"));
        return titleElement.getText();
    }

    public String getCardDescription() {
        WebElement descriptionElement = driver.findElement(cardDescription);
        return descriptionElement.getText();
    }

    public void clickModalContent() {
        WebElement modalContentElement = driver.findElement(modalContent);
        modalContentElement.click();
    }

    // Navigation methods (merged from BoardsPage)
    public boolean isBoardsNavPresent() {
        List<WebElement> elements = driver.findElements(By.cssSelector("#boards_nav span"));
        return !elements.isEmpty();
    }

    public void clickBoardsNav() {
        WebElement boardsNavElement = driver.findElement(By.cssSelector("#boards_nav span"));
        boardsNavElement.click();
    }

    public boolean isViewAllBoardsLinkPresent() {
        List<WebElement> elements = driver.findElements(By.linkText("View all boards"));
        return !elements.isEmpty();
    }

    public void clickViewAllBoardsLink() {
        WebElement viewAllBoardsLinkElement = driver.findElement(By.linkText("View all boards"));
        viewAllBoardsLinkElement.click();
    }

    public String getHeaderText() {
        WebElement headerElement = driver.findElement(By.cssSelector("h3 > span"));
        return headerElement.getText();
    }

    // New method to click a board link by name
    public void clickBoardLinkByName(String boardName) {
        By boardLinkSelector = By.linkText(boardName);
        WebElement boardLinkElement = driver.findElement(boardLinkSelector);
        boardLinkElement.click();
    }

    // Methods for adding a member
    public void clickAddMemberIcon() {
        WebElement addMemberIconElement = driver.findElement(addMemberIcon);
        addMemberIconElement.click();
    }

    public void clickAddMemberLink() {
        WebElement addMemberLinkElement = driver.findElement(addMemberLink);
        addMemberLinkElement.click();
    }

    public void enterMemberEmail(String email) {
        WebElement emailInput = driver.findElement(memberEmailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void submitMemberForm() {
        WebElement submitButtonElement = driver.findElement(submitButton);
        submitButtonElement.click();
    }

    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.getText();
    }

    public boolean isMemberGravatarPresent() {
        List<WebElement> elements = driver.findElements(memberGravatar);
        return !elements.isEmpty();
    }

    public void clickAddNewBoardButton() {
        WebElement addNewBoardButtonElement = driver.findElement(addNewBoardButton);
        addNewBoardButtonElement.click();
    }

    public void enterListName(String listName) {
        WebElement listNameElement = driver.findElement(listNameInput);
        listNameElement.clear();
        listNameElement.sendKeys(listName);
    }

    public void enterComment(String comment) {
        WebElement commentTextareaElement = driver.findElement(commentTextarea);
        commentTextareaElement.clear();
        commentTextareaElement.sendKeys(comment);
    }

    public String getCommentText() {
        WebElement commentTextElement = driver.findElement(commentText);
        return commentTextElement.getText();
    }

    public String getCommentErrorMessage() {
        WebElement commentTextareaElement = driver.findElement(commentTextarea);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", commentTextareaElement);
    }

    // Tag management methods
    public void clickAddTagButton() {
        WebElement addTagButtonElement = driver.findElement(addTagButton);
        addTagButtonElement.click();
    }

    public void clickGreenTag() {
        WebElement greenTagElement = driver.findElement(greenTag);
        greenTagElement.click();
    }

    public void clickRedTag() {
        WebElement redTagElement = driver.findElement(redTag);
        redTagElement.click();
    }

    public void clickBlueTag() {
        WebElement blueTagElement = driver.findElement(blueTag);
        blueTagElement.click();
    }

    public void clickTagsLink() {
        WebElement tagsLinkElement = driver.findElement(tagsLink);
        tagsLinkElement.click();
    }

    public void clickTagByClass(String tagClass) {
        By tagSelector = By.cssSelector("." + tagClass + ":nth-child(1)");
        WebElement tagElement = driver.findElement(tagSelector);
        tagElement.click();
    }

    public boolean isTagPresent(String tagClass) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By tagSelector = By.cssSelector("." + tagClass);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(tagSelector));
            return false;
        } catch (Exception e) {
            List<WebElement> elements = driver.findElements(tagSelector);
            return !elements.isEmpty();
        }
    }

    public int getTagCount(String tagClass) {
        By tagSelector = By.cssSelector("." + tagClass);
        List<WebElement> elements = driver.findElements(tagSelector);
        return elements.size();
    }

    public String getTagBackgroundColor(String tagClass) {
        By tagSelector = By.cssSelector("." + tagClass + ":nth-child(1)");
        WebElement tagElement = driver.findElement(tagSelector);
        return tagElement.getCssValue("background-color");
    }

    public boolean isTagPresentWithColor(String tagClass, String expectedColor) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By tagSelector = By.cssSelector("." + tagClass);
        List<WebElement> elements = driver.findElements(tagSelector);
        if (elements.isEmpty()) {
            return false;
        }
        try {
            WebElement tagElement = driver.findElement(tagSelector);
            String actualColor = tagElement.getCssValue("background-color");
            return actualColor.equals(expectedColor);
        } catch (Exception e) {
            return false;
        }
    }
}
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardPage {
    WebDriver driver;

    public CardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By titleInput = By.cssSelector("input.card-title");         // Adjust selector based on actual input
    private By descriptionTextarea = By.cssSelector("textarea.card-description"); // Adjust as needed
    private By saveButton = By.cssSelector("button.save");              // Change if your save button is different
    private By descriptionContent = By.cssSelector(".description-content"); // Selector for saved description view

    public void editCardTitle(String newTitle) {
        WebElement title = driver.findElement(titleInput);
        title.clear();
        title.sendKeys(newTitle);
    }

    public void editCardDescription(String newDescription) {
        WebElement description = driver.findElement(descriptionTextarea);
        description.clear();
        description.sendKeys(newDescription);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public String getCardDescription() {
        return driver.findElement(descriptionContent).getText();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home_Page {
    private WebDriver driver;

    public Home_Page(WebDriver driver) {
        this.driver = driver;
    }

    private By viewContainer = By.cssSelector(".view-container");
    private By signOutBtn = By.cssSelector("#crawler-sign-out > span");
    private By signInButton = By.cssSelector("button");

    public void clickViewContainer() {
        driver.findElement(viewContainer).click();
    }

    public void clickSignOut() {
        driver.findElement(signOutBtn).click();
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
}

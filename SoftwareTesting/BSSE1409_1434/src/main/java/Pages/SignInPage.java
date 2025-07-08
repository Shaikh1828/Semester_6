package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {
    private final WebDriver driver;

    // Web elements on the sign-in page
    private final By emailInput = By.id("user_email");
    private final By passwordInput = By.id("user_password");
    private final By signInButton = By.cssSelector("button");
    private final By createAccountLink = By.linkText("Create new account");
    private final By errorMessage = By.cssSelector(".error");
    private final By footerText = By.cssSelector("small");

    public SignInPage(WebDriver driver)
    {
        this.driver = driver;
    }



    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInput);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickSignInButton() {
        WebElement signInButtonElement = driver.findElement(signInButton);
        signInButtonElement.click();
    }

    public void clickCreateAccountLink() {
        WebElement createAccountLinkElement = driver.findElement(createAccountLink);
        createAccountLinkElement.click();
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = driver.findElement(errorMessage);
        return errorMessageElement.getText();
    }

    public String getFooterText() {
        WebElement footerTextElement = driver.findElement(footerText);
        return footerTextElement.getText();
    }

    public boolean isCreateAccountLinkDisplayed() {
        WebElement createAccountLinkElement = driver.findElement(createAccountLink);
        return createAccountLinkElement.isDisplayed();
    }
}
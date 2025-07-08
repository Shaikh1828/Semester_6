package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private final WebDriver driver;

    // Web elements on the sign-up page
    private final By firstNameInput = By.id("user_first_name");
    private final By lastNameInput = By.id("user_last_name");
    private final By emailInput = By.id("user_email");
    private final By passwordInput = By.id("user_password");
    private final By passwordConfirmationInput = By.id("user_password_confirmation");
    private final By signUpButton = By.cssSelector("button");
    private final By errorMessage = By.cssSelector(".error");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the sign-up page
    public void enterFirstName(String firstName) {
        WebElement firstNameElement = driver.findElement(firstNameInput);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameInput);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
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

    public void enterPasswordConfirmation(String passwordConfirmation) {
        WebElement passwordConfirmationElement = driver.findElement(passwordConfirmationInput);
        passwordConfirmationElement.clear();
        passwordConfirmationElement.sendKeys(passwordConfirmation);
    }

    public void clickSignUpButton() {
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorMessageElement.getText();
    }

    public boolean isFirstNameFieldDisplayed() {
        WebElement firstNameElement = driver.findElement(firstNameInput);
        return firstNameElement.isDisplayed();
    }

    public boolean isLastNameFieldDisplayed() {
        WebElement lastNameElement = driver.findElement(lastNameInput);
        return lastNameElement.isDisplayed();
    }

    public boolean isEmailFieldDisplayed() {
        WebElement emailElement = driver.findElement(emailInput);
        return emailElement.isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        WebElement passwordElement = driver.findElement(passwordInput);
        return passwordElement.isDisplayed();
    }

    public boolean isPasswordConfirmationFieldDisplayed() {
        WebElement passwordConfirmationElement = driver.findElement(passwordConfirmationInput);
        return passwordConfirmationElement.isDisplayed();
    }

    public boolean isSignUpButtonDisplayed() {
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        return signUpButtonElement.isDisplayed();
    }

    public String getFirstNameValidationMessage() {
        WebElement firstNameElement = driver.findElement(firstNameInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", firstNameElement);
    }

    public String getEmailValidationMessage() {
        WebElement emailElement = driver.findElement(emailInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", emailElement);
    }

    public String getLastNameValidationMessage() {
        WebElement lastNameElement = driver.findElement(lastNameInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", lastNameElement);
    }

    public String getPasswordValidationMessage() {
        WebElement passwordElement = driver.findElement(passwordInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", passwordElement);
    }

    public String getPasswordConfirmationValidationMessage() {
        WebElement passwordConfirmationElement = driver.findElement(passwordConfirmationInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", passwordConfirmationElement);
    }
}
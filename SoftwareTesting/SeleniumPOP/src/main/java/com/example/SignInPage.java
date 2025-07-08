package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Basic UI elements
    private By signInButton = By.cssSelector("button[type='submit']");
    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By signUpLink = By.linkText("Create new account");
    private final By createAccountLink = By.linkText("Create new account");
    private final By errorMessage = By.cssSelector(".error");
    private final By footerText = By.cssSelector("small");

    // Try multiple possible error message selectors
    // (we'll dynamically test which ones actually exist)
    private By[] possibleErrorSelectors = {
            By.className("error-message"),
            By.className("alert"),
            By.className("alert-danger"),
            By.className("text-danger"),
            By.className("invalid-feedback"),
            By.cssSelector(".form-error"),
            By.cssSelector("[role='alert']"),
            By.cssSelector(".error"),
            By.cssSelector("p.error"),
            By.cssSelector(".field_with_errors"),
            By.cssSelector(".validation-error"),
            By.cssSelector(".help-block"),
            By.xpath("//div[contains(@class, 'error')]"),
            By.xpath("//p[contains(@class, 'error')]"),
            By.xpath("//span[contains(@class, 'error')]"),
            By.xpath("//div[contains(text(), 'Invalid')]"),
            By.xpath("//p[contains(text(), 'Invalid')]"),
            By.xpath("//div[contains(text(), 'error')]"),
            By.xpath("//p[contains(text(), 'error')]")
    };

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
        driver.manage().window().maximize();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
            System.out.println("Sign-in page loaded successfully");
        } catch (TimeoutException e) {
            System.err.println("Sign in button not found within timeout period");
            System.err.println("Current URL: " + driver.getCurrentUrl());
            System.err.println("Page title: " + driver.getTitle());
            dumpPageSource("open_timeout");
            throw e;
        }
    }

    public void clickSignIn() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
            button.click();
            System.out.println("Clicked sign-in button normally");
        } catch (Exception e) {
            System.out.println("Normal click failed, trying JavaScript click: " + e.getMessage());
            try {
                // If regular click fails, try JavaScript click
                WebElement button = driver.findElement(signInButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                System.out.println("JavaScript click completed");
            } catch (Exception jsError) {
                System.err.println("JavaScript click also failed: " + jsError.getMessage());
                dumpPageSource("click_error");
                throw jsError;
            }
        }
    }

    public void login(String email, String password) {
        try {
            System.out.println("Attempting login with email: " + email);

            WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailElement.clear();
            emailElement.sendKeys(email);
            System.out.println("Email entered");

            WebElement passwordElement = driver.findElement(passwordField);
            passwordElement.clear();
            passwordElement.sendKeys(password);
            System.out.println("Password entered");

            clickSignIn();

            // Give the page a moment to update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Login attempt completed");
        } catch (Exception e) {
            System.err.println("Failed to perform login: " + e.getMessage());
            dumpPageSource("login_error");
            throw e;
        }
    }

    public void clickSignInWithoutFilling() {
        try {
            System.out.println("Attempting to click sign-in without filling fields");

            // Make sure fields are empty
            try {
                WebElement email = driver.findElement(emailField);
                WebElement password = driver.findElement(passwordField);

                email.clear();
                System.out.println("Email field cleared");

                password.clear();
                System.out.println("Password field cleared");
            } catch (Exception e) {
                System.err.println("Error clearing fields: " + e.getMessage());
            }

            clickSignIn();

            // Give the page a moment to show validation errors
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Empty form submission completed");
        } catch (Exception e) {
            System.err.println("Failed to click sign in with empty fields: " + e.getMessage());
            dumpPageSource("empty_submission_error");
            throw new RuntimeException(e);
        }
    }

    public boolean isErrorMessageVisible() {
        System.out.println("Checking for error messages...");

        // First, print all form field validation states
        checkInputValidationState(emailField, "email");
        checkInputValidationState(passwordField, "password");

        // Try all our possible error selectors
        for (By selector : possibleErrorSelectors) {
            try {
                List<WebElement> elements = driver.findElements(selector);
                if (!elements.isEmpty()) {
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            String text = element.getText();
                            if (text != null && !text.trim().isEmpty()) {
                                System.out.println("✓ Found error message using " + selector + ": '" + text + "'");
                                return true;
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
        System.out.println("× No error messages found with any selector");
        dumpPageSource("no_errors_found");

        return false;
    }

    private void checkInputValidationState(By fieldSelector, String fieldName) {
        try {
            WebElement field = driver.findElement(fieldSelector);
            String classes = field.getAttribute("class");
            String ariaInvalid = field.getAttribute("aria-invalid");

            System.out.println(fieldName + " field classes: " + classes);
            System.out.println(fieldName + " field aria-invalid: " + ariaInvalid);

            try {
                WebElement parent = field.findElement(By.xpath("./.."));
                List<WebElement> siblings = parent.findElements(By.xpath("./*"));

                for (WebElement sibling : siblings) {
                    if (!sibling.equals(field)) {
                        String siblingText = sibling.getText();
                        if (siblingText != null && !siblingText.trim().isEmpty()) {
                            System.out.println("Sibling of " + fieldName + " field contains text: " + siblingText);
                        }
                    }
                }
            } catch (Exception e) {
                // If we can't find siblings, just continue
            }
        } catch (Exception e) {
            System.err.println("Could not check validation state for " + fieldName + ": " + e.getMessage());
        }
    }

    public void clickSignUpLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
            System.out.println("Clicked sign-up link");
        } catch (Exception e) {
            System.out.println("Normal click failed, trying JavaScript click: " + e.getMessage());
            try {
                // Try with JavaScript if normal click fails
                WebElement link = driver.findElement(signUpLink);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
                System.out.println("JavaScript click on sign-up link completed");
            } catch (Exception jsError) {
                System.err.println("JavaScript click on sign-up link also failed: " + jsError.getMessage());
                dumpPageSource("signup_click_error");
                throw jsError;
            }
        }
    }

    public void dumpPageSource(String context) {
        System.out.println("\n----- PAGE DUMP [" + context + "] -----");
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());

        try {

            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            System.out.println("Buttons found: " + buttons.size());
            for (WebElement button : buttons) {
                System.out.println(" - Button: " + button.getText() + " | Type: " +
                        button.getAttribute("type") + " | Class: " + button.getAttribute("class"));
            }

            // List all form elements
            List<WebElement> forms = driver.findElements(By.tagName("form"));
            System.out.println("Forms found: " + forms.size());
            for (WebElement form : forms) {
                System.out.println(" - Form action: " + form.getAttribute("action") +
                        " | Method: " + form.getAttribute("method") +
                        " | ID: " + form.getAttribute("id"));
            }


            String[] errorTerms = {"error", "alert", "warning", "danger", "invalid", "help"};
            for (String term : errorTerms) {
                List<WebElement> errorElements = driver.findElements(
                        By.xpath("//*[contains(@class, '" + term + "') or contains(@id, '" + term + "')]"));

                if (!errorElements.isEmpty()) {
                    System.out.println("Found " + errorElements.size() + " elements containing '" + term + "'");
                    for (WebElement element : errorElements) {
                        if (element.isDisplayed()) {
                            System.out.println(" - [" + element.getTagName() + "] Text: '" + element.getText() +
                                    "' | Class: " + element.getAttribute("class"));
                        }
                    }
                }
            }

            // Check if any <p> or <div> elements contain error messages
            List<WebElement> textElements = driver.findElements(By.xpath("//p | //div | //span"));
            for (WebElement element : textElements) {
                String text = element.getText();
                if (text != null && !text.isEmpty() &&
                        (text.toLowerCase().contains("error") ||
                                text.toLowerCase().contains("invalid") ||
                                text.toLowerCase().contains("required"))) {
                    System.out.println(" - Potential error text: '" + text +
                            "' | Tag: " + element.getTagName() +
                            " | Class: " + element.getAttribute("class"));
                }
            }

        } catch (Exception e) {
            System.err.println("Error while dumping page info: " + e.getMessage());
        }

        System.out.println("----- END PAGE DUMP [" + context + "] -----\n");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void analyzeErrorMessageStructure() {
        System.out.println("\n===== ANALYZING ERROR MESSAGE STRUCTURE =====");

        try {
            WebElement email = driver.findElement(emailField);
            WebElement password = driver.findElement(passwordField);
            email.clear();
            password.clear();
            String beforeHtml = driver.getPageSource();
            clickSignIn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String afterHtml = driver.getPageSource();
            if (!beforeHtml.equals(afterHtml)) {
                System.out.println("Page HTML changed after form submission");
                try {
                    java.io.File screenshot = ((org.openqa.selenium.TakesScreenshot)driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
                    System.out.println("Screenshot captured: " + screenshot.getAbsolutePath());
                } catch (Exception se) {
                    System.err.println("Could not capture screenshot: " + se.getMessage());
                }
            } else {
                System.out.println("Page HTML did not change after form submission - client-side validation may not be working");
            }
            checkHtml5Validation(email, "Email");
            checkHtml5Validation(password, "Password");

        } catch (Exception e) {
            System.err.println("Error analyzing error structure: " + e.getMessage());
        }

        System.out.println("===== END ERROR STRUCTURE ANALYSIS =====\n");
    }

    private void checkHtml5Validation(WebElement element, String fieldName) {
        System.out.println(fieldName + " field validation attributes:");
        System.out.println(" - Required: " + element.getAttribute("required"));
        System.out.println(" - Pattern: " + element.getAttribute("pattern"));
        System.out.println(" - Min length: " + element.getAttribute("minlength"));
        System.out.println(" - Validity state: " +
                ((JavascriptExecutor)driver).executeScript("return arguments[0].validity.valid", element));
    }
    public void clickCreateAccountLink() {
        WebElement createAccountLinkElement = driver.findElement(createAccountLink);
        createAccountLinkElement.click();
    }
    public String getFooterText() {
        WebElement footerTextElement = driver.findElement(footerText);
        return footerTextElement.getText();
    }
    public boolean isCreateAccountLinkDisplayed() {
        WebElement createAccountLinkElement = driver.findElement(createAccountLink);
        return createAccountLinkElement.isDisplayed();
    }
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailField);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickSignInButton() {
        WebElement signInButtonElement = driver.findElement(signInButton);
        signInButtonElement.click();
    }
    public String getErrorMessage() {
        WebElement errorMessageElement = driver.findElement(errorMessage);
        return errorMessageElement.getText();
    }
}
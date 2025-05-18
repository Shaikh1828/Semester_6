package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;

    private By signInButton = By.cssSelector("button");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
        driver.manage().window().maximize();
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
}

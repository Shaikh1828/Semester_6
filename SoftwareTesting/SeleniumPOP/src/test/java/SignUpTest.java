import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import com.example.SignUpPage;
import com.example.SignInPage;
public class SignUpTest {
    private WebDriver driver;
    private com.example.SignUpPage signUpPage;

    @Before
    public void setUp() {
        try {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            System.out.println("Firefox driver initialized successfully");
        } catch (Exception e) {
            System.out.println("Firefox driver failed to initialize: " + e.getMessage());
            System.out.println("Trying Chrome...");

            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            System.out.println("Chrome driver initialized successfully");
        }

        com.example.SignUpPage signUnPage = new com.example.SignUpPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver closed");
        }
    }
    @Test
    public void registerTest() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(749, 764));

        com.example.SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        assertTrue(signInPage.isCreateAccountLinkDisplayed());
        assertEquals("Trello tribute for educational purposes crafted with ♥ for Diacode by @bigardone", signInPage.getFooterText());
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.isFirstNameFieldDisplayed());
        assertTrue(signUpPage.isLastNameFieldDisplayed());
        assertTrue(signUpPage.isEmailFieldDisplayed());
        assertTrue(signUpPage.isPasswordFieldDisplayed());
        assertTrue(signUpPage.isPasswordConfirmationFieldDisplayed());
        assertTrue(signUpPage.isSignUpButtonDisplayed());
    }
    @Test
    public void existingEmail() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterFirstName("john");
        signUpPage.enterLastName("john");
        signUpPage.enterEmail("john@pheonix-trello.com");
        signUpPage.enterPassword("john123");
        signUpPage.enterPasswordConfirmation("john123");
        signUpPage.clickSignUpButton();

        assertEquals("Email already taken", signUpPage.getErrorMessage());
    }
    @Test
    public void smallPassReg() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(749, 764));

        SignInPage signInPage = new SignInPage(driver);
        driver.get("http://localhost:4000/sign_in");
//        signInPage.navigateTo();
        assertTrue(signInPage.isCreateAccountLinkDisplayed());
        signInPage.clickCreateAccountLink();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterFirstName("Hello");
        signUpPage.enterLastName("world");
        signUpPage.enterEmail("hello@gmail.com");
        signUpPage.enterPassword("1234");
        signUpPage.enterPasswordConfirmation("1234");
        signUpPage.clickSignUpButton();

        assertEquals("should be at least 5 character(s)", signUpPage.getErrorMessage());
}
}
package pages;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPageLocators;
import utils.CommonHelper;

import static utils.LoggingUtils.info;

public class LoginPage extends LoginPageLocators {
    private SelfHealingDriver driver;
    private CommonHelper helper;

    public LoginPage(SelfHealingDriver driver) {
        this.driver = driver;
    }



    // Login actions
    public void login(String email, String password) {
        clickLoginButton()
        .fillEmail(email)
        .fillPassword(password)
        .clickLoginSubmitButton()
        .validateLoginSuccess();
    }

    public LoginPage fillEmail(String email) {
        helper.sendKeys(emailInput,email);
        info("Email filled: " + email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        helper.sendKeys(passwordInput,password);
        info("Password filled: " + password);
        return this;
    }

    public LoginPage clickLoginButton(){
        helper.click(loginButton);
        info("Login button clicked");
        return this;
    }

    public LoginPage clickLoginSubmitButton(){
        helper.click(loginSubmitButton);
        info("Login submit button clicked");
        return this;
    }

    public void validateLoginSuccess() {
        Assertions.assertTrue(helper.isElementDisplayed(userAccountLinkText), "Unsuccessful login");
    }
}

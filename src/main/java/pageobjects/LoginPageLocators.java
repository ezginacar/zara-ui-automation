package pageobjects;

import org.openqa.selenium.By;

public class LoginPageLocators {

    protected By loginButton = By.xpath("//button[@data-qa-id='oauth-logon-button']");
    protected By emailInput = By.name("email");
    protected By passwordInput = By.name("password");
    protected By loginSubmitButton = By.cssSelector("button[data-qa-id='logon-form-submit']");
    protected By userAccountLinkText = By.cssSelector("[data-qa-id='layout-header-user-account']");

}

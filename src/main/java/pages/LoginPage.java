package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "https://www.saucedemo.com/";

    public LoginPage openSauceDemoWebPage() {
        driver.get(baseURL);
        return this;
    }

    By userNameEl = By.id("user-name");
    By passwordEl = By.id("password");
    By loginButtonEl = By.id("login-button");

    public LoginPage loginToPage(String username, String password) {
        enterText(userNameEl, username);
        enterText(passwordEl, password);
        click(loginButtonEl);
        return this;
    }

}

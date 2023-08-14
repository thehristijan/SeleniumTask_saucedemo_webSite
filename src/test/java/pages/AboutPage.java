package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutPage extends BasePage {

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    By acceptCookie = By.id("onetrust-accept-btn-handler");

    public void clickAcceptCookieButton() {

        javascriptClick(acceptCookie);

    }

}

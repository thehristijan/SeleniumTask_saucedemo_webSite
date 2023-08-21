package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AboutPage extends BasePage {

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    By acceptCookie = By.id("onetrust-accept-btn-handler");

    public void clickAcceptCookieButton() {

        javascriptClick(acceptCookie);

    }

    protected void javascriptClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);
    }

}

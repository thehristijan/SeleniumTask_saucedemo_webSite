package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitVisibility(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

    public void click(By element) {
        waitVisibility(element);
        driver.findElement(element).click();

    }

    public void enterText(By element, String text) {
        waitVisibility(element);
        driver.findElement(element).sendKeys(text);

    }

}

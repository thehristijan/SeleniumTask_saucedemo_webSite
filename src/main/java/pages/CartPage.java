package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By removeFromCartButton = By.xpath(".//button[contains(text(), 'Remove')]");

    public void removeItem() {
        click(removeFromCartButton);
    }

    public void checkCartIsEmpty() {
        try {
            boolean removeButton = driver.findElement(removeFromCartButton).isDisplayed();
            if (removeButton == true) {
                click(removeFromCartButton);
            }
        } catch (NoSuchElementException e) {
            logger.info("Cart is empty!");
        }
    }
}

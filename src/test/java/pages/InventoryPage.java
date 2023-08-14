package pages;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    List<Item> sortedByPrice = new ArrayList<>();

    By dropdownMenu = By.id("react-burger-menu-btn");
    By about = By.id("about_sidebar_link");
    By cartButton = By.id("shopping_cart_container");
    By addToCartButton = By.xpath(".//button[contains(text(), 'Add to cart')]");
    By dropdownFilter = By.className("product_sort_container");
    WebElement dropdownElement = driver.findElement(dropdownFilter);
    By inventoryItems = By.className("inventory_item");

    public List<WebElement> inventoryItemList() {
        waitVisibility(inventoryItems);
        return driver.findElements(inventoryItems);
    }

    public InventoryPage checkLoginIsSuccessfulAndMenuBarIsPresent() {
        waitVisibility(dropdownMenu);
        return this;
    }

    public int countNumberOfItemsPresent() {
        waitVisibility(addToCartButton);
        int itemsCount = inventoryItemList().size();
        return itemsCount;
    }

    public void sortByPrIceLowToHigh() {
        List<WebElement> inventoryItemList = inventoryItemList();
        for (WebElement product : inventoryItemList) {
            Item item = new Item(product.findElement(By.className("inventory_item_name")).getText(),
                                 product.findElement(By.className("inventory_item_price")).getText());
            sortedByPrice.add(item);
        }
        Collections.sort(sortedByPrice);
    }

    public void saveToFile() {
        int indexNumber = 1;
        try (FileWriter writer = new FileWriter("items.txt")) {
            for (Item item : sortedByPrice) {
                writer.write(indexNumber + ". " + item + System.lineSeparator());
                indexNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortLowToHighByFilter() {

        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Price (low to high)");

    }

    public InventoryPage clickAbout() {
        click(dropdownMenu);
        waitVisibility(about);
        click(about);
        return this;
    }

    public InventoryPage selectRandomItemsAndAddToCart() {

        List<WebElement> inventoryItemList = inventoryItemList();
        int itemCount = inventoryItemList.size();
        Random random = new Random();
        int randomIndex = random.nextInt(itemCount);
        WebElement randomItem = inventoryItemList.get(randomIndex);
        WebElement addToCartButton = randomItem.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        return this;
    }

    public InventoryPage checkoutCart() {
        click(cartButton);
        return this;
    }

}

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class SaucedemoExerciseFlowTests  extends BaseTests {

    @Test
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openSauceDemoWebPage();
        loginPage.loginToPage("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.checkLoginIsSuccessfulAndMenuBarIsPresent();
    }

    @Test(dependsOnMethods = "login")
    public void verifyNumberOfItemsInInventory() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.countNumberOfItemsPresent(), 6);
    }

    @Test(dependsOnMethods = "verifyNumberOfItemsInInventory")
    public void selectAboutAndAcceptCookies() throws InterruptedException {
        InventoryPage inventoryPage = new InventoryPage(driver);
        AboutPage aboutPage = new AboutPage(driver);
        inventoryPage.clickAbout();
        aboutPage.clickAcceptCookieButton();
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "selectAboutAndAcceptCookies")
    public void saveItemsInFileAndSortThemByPrice() throws InterruptedException {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortByPrIceLowToHigh();
        inventoryPage.saveToFile();
        inventoryPage.sortLowToHighByFilter();
    }

    @Test(dependsOnMethods = "saveItemsInFileAndSortThemByPrice")
    public void selectItemByRandom() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectRandomItemsAndAddToCart();
    }

    @Test(dependsOnMethods = "selectItemByRandom")
    public void checkoutCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.checkoutCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem();
        cartPage.checkCartIsEmpty();
    }
}

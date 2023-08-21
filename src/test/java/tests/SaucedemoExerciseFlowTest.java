package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class SaucedemoExerciseFlowTest extends BaseTest {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    AboutPage aboutPage;
    CartPage cartPage;


    @BeforeClass
    public void initialization() {

        loginPage = new LoginPage(driver);
        aboutPage = new AboutPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

    }

    @Test
    public void login() {
        loginPage.openSauceDemoWebPage();
        loginPage.loginToPage("standard_user", "secret_sauce");
        inventoryPage.checkLoginIsSuccessfulAndMenuBarIsPresent();
    }

    @Test(dependsOnMethods = "login")
    public void verifyNumberOfItemsInInventory() {
        Assert.assertEquals(inventoryPage.countNumberOfItemsPresent(), 6);
    }

    @Test(dependsOnMethods = "verifyNumberOfItemsInInventory")
    public void selectAboutAndAcceptCookies(){
        inventoryPage.clickAbout();
        aboutPage.clickAcceptCookieButton();
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "selectAboutAndAcceptCookies")
    public void saveItemsInFileAndSortThemByPrice(){
        inventoryPage.sortByPrIceLowToHigh();
        inventoryPage.saveToFile();
        inventoryPage.sortLowToHighByFilter();
    }

    @Test(dependsOnMethods = "saveItemsInFileAndSortThemByPrice")
    public void selectItemByRandom() {
        inventoryPage.selectRandomItemsAndAddToCart();
    }

    @Test(dependsOnMethods = "selectItemByRandom")
    public void checkoutCart() {
        inventoryPage.checkoutCart();
        cartPage.removeItem();
        cartPage.checkCartIsEmpty();
    }
}
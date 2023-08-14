package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTests {

    public WebDriver driver;
    private String firefoxdriver = "webdriver.gecko.driver";
    private String driverFullPath = "/Users/v-hdespotovski/GitProjects/Selenium_FrontEnd/src/test/drivers/geckodriver";


    @BeforeClass
    public void setup() {
        System.setProperty(firefoxdriver, driverFullPath);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

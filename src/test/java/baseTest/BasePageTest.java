package baseTest;


import com.aventstack.extentreports.ExtentTest;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import utils.ConfigReaders;
import utils.DriversFactory;

@Listeners(listeners.TestListener.class)
public class BasePageTest {
    ConfigReaders config;
    public static WebDriver driver;
    public static Logger logger = LogManager.getLogger(BasePageTest.class);

    @BeforeClass
    public void initializeDriver() {
        config = new ConfigReaders();
        driver = DriversFactory.getDriver(config.getBrowser());
        driver.get(config.getURL());
        driver.manage().window().maximize();

    }
    public ExtentTest getTest() {
        return TestListener.test.get();
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }

        } catch (Exception e) {
            System.out.println("Error during driver teardown: " + e.getMessage());
        }
    }


}
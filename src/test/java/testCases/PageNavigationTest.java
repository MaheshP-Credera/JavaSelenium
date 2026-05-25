package testCases;

import baseTest.BasePageTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckOutPage;
import pages.ProductPage;
import utils.ConfigReaders;
import utils.UIActions;

public class PageNavigationTest extends BasePageTest {
    ProductPage pp;
    CartPage cp;
    UIActions actions;
    CheckOutPage checkout;
    ConfigReaders configs;

    @BeforeMethod
    public void setUp() {
        pp = new ProductPage(driver);
        actions = new UIActions(driver);
        cp = new CartPage(driver);
        checkout = new CheckOutPage(driver);
        configs = new ConfigReaders();
    }

    @Test
    public void navigationTest() throws InterruptedException {

            String baseURL = configs.getURL();
            System.out.println("Base Page URL: " + baseURL);
            pp.addToCartFirstProduct();
            logger.info("First product Added to cart");
            TestListener.test.get().info("First product Added to cart");
            cp.openCart();
            logger.info("Cart is opened");
            TestListener.test.get().info("Cart is opened");
            cp.clickOnProceedToCheckout();
            logger.info("Proceeded to checkout");
            TestListener.test.get().info("Proceeded to checkout");
            Thread.sleep(3000);
            actions.browserBack();
            logger.info("Navigated back to previous page");
            TestListener.test.get().info("Navigated back to previous page");
            Thread.sleep(3000);
            String currentURL = driver.getCurrentUrl();
            System.out.println("Current Page URL: " + currentURL);
            Assert.assertEquals(baseURL, currentURL, "Both page URLs are not matching");
            actions.refreshPage();
            logger.info("Page got Refreshed");
            TestListener.test.get().info("Page got Refreshed");
            actions.browserForward();
            logger.info("Navigated to next page");
            TestListener.test.get().info("Navigated to next page");
            System.out.println("✓ Main URL assertion passed");

    }


}

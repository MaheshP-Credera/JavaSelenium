package testCases;

import baseTest.BasePageTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductPage;
import utils.ScreenshotUtil;
import utils.UIActions;

import java.io.IOException;
import java.lang.reflect.Method;


public class SearchProductTest extends BasePageTest {
    ProductPage productPage;
    UIActions uiActions;

    @BeforeMethod
    public void setUp(Method method) {
        productPage = new ProductPage(driver);
        uiActions = new UIActions(driver);

    }

    @Test
    public void testSearchProduct() throws InterruptedException, IOException {
        // enter product name first, then click search
        logger.info("Test Search Product testcase is started");
        TestListener.test.get().info("Test Search Product testcase is started");
        productPage.enterProductName();


        logger.info("Product name is entered in search box");
        TestListener.test.get().info("Product name is entered in search box");
        productPage.clickSearchButton();
        // fetch results and check
        productPage.getProductNameFromResults();


        Assert.assertTrue(productPage.isSearchProductDisplayed(), "Product not displayed in search results");
        String path = ScreenshotUtil.captureScreenshot(driver,"Search Product Test");
        TestListener.test.get().addScreenCaptureFromPath(path);
        System.out.println("Assertion passed: Product is displayed in search results");
        if(productPage.isSearchProductDisplayed()) {
            System.out.println("Product appeared successfully");
            logger.info("Product is appearing in search results and successfully fetched");
            TestListener.test.get().info("Product is appearing in search results and successfully fetched");
        } else {
            System.out.println("Product not found in search results");
        }


    }

}

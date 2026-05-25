package testCases;

import baseTest.BasePageTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckOutPage;
import pages.ProductPage;
import utils.UIActions;

public class PlaceOrderTest  extends BasePageTest {
    ProductPage pp;
    CartPage cp;
    UIActions actions;
    CheckOutPage checkout;

    @BeforeMethod
    public void setUp() {
        pp = new ProductPage(driver);
        actions = new UIActions(driver);
        cp = new CartPage(driver);
        checkout = new CheckOutPage(driver);
    }

    @Test
    public void placeOrder(){
        pp.addToCartFirstProduct();
        logger.info("One product Added to cart");
        TestListener.test.get().info("One product Added to cart");
        cp.openCart();
        logger.info("Cart is opened");
        TestListener.test.get().info("Cart is opened");
        cp.clickOnProceedToCheckout();
        logger.info("Proceeded to checkout");
        TestListener.test.get().info("Proceeded to checkout");
        checkout.checkProductPriceInCheckout();
        Assert.assertTrue(checkout.productNameMatching(), "Product name in checkout does not match the product added to cart");
        logger.info("Product name is matching in checkout");
        TestListener.test.get().info("Product name is matching in checkout");
        Assert.assertTrue(checkout.productPriceMatching(), "Product price in checkout does not match the product price added to cart");
        logger.info("Product price is matching in checkout");
        TestListener.test.get().info("Product price is matching in checkout");
        checkout.clickOnPlaceOrder();
        logger.info("Click on Place order");
        TestListener.test.get().info("Click on Place order");
        checkout.selectCountryFromDropdown();
        logger.info("Country selected from dropdown");
        TestListener.test.get().info("Country selected from dropdown");
        checkout.clickCheckBox();
        logger.info("Click on Check Box");
        TestListener.test.get().info("Click on Check Box");
        checkout.clickOnProceed();
        logger.info("Click on Proceed");
        TestListener.test.get().info("Click on Proceed");
        Assert.assertTrue(checkout.isOrderPlacedSuccessfully(), "Order was not placed successfully");
        logger.info("Order was placed successfully");
        TestListener.test.get().info("Order was placed successfully");
        Assert.assertTrue(driver.getPageSource().contains("Thank you"));

    }


}

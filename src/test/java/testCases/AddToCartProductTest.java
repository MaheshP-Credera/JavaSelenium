package testCases;

import baseTest.BasePageTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.UIActions;

public class AddToCartProductTest extends BasePageTest {
    UIActions uiActions;
    CartPage cartPage;
    @BeforeMethod
    public void setUp() {
        uiActions = new UIActions(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void addProductToCart(){
        cartPage.clickOnAddToCart();
        logger.info("First product Added to cart");
        TestListener.test.get().info("First product Added to cart");
        cartPage.openCart();
        logger.info("Cart is opened");
        TestListener.test.get().info("Cart is opened");
        Assert.assertTrue(cartPage.checkItemNameInCart(), "Product name in cart does not match expected");
        logger.info("Product name is matching in Cart");
        TestListener.test.get().info("Product name is matching in Cart");
        System.out.println("Assertion is passed and Item is added to cart");
        Assert.assertTrue(cartPage.checkItemCountInCart(), "Item count in cart does not match expected");
        logger.info("Product price is matching in checkout");
        TestListener.test.get().info("Product price is matching in checkout");
        System.out.println("Assertion is passed and Item count is matching");

    }




}

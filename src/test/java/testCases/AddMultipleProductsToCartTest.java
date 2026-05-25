package testCases;

import baseTest.BasePageTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import utils.UIActions;

public class AddMultipleProductsToCartTest extends BasePageTest {
ProductPage pp;
CartPage cp;
UIActions actions;

    @BeforeMethod
    public void setUp() {
        pp = new ProductPage(driver);
        actions = new UIActions(driver);
        cp = new CartPage(driver);
    }

    @Test
    public void addMultipleProductsToCart()  {
        // Add products to cart
        pp.addToCartFirstProduct();
        logger.info("First product Added to cart");
        TestListener.test.get().info("First product Added to cart");
        pp.addToCartSecondProduct();
        logger.info("Second product Added to cart");
        TestListener.test.get().info("Second product Added to cart");
        pp.addToCartThirdProduct();
        logger.info("Third product Added to cart");
        TestListener.test.get().info("Third product Added to cart");
        // Open cart and verify both products are added
        cp.openCart();
        logger.info("Cart is opened");
        TestListener.test.get().info("Cart is opened");
        cp.checkMultipleItemPriceInCart();
        logger.info("All added products price is matching in checkout");
        TestListener.test.get().info("All added products price is matching in checkout");
        Assert.assertEquals(cp.multipleItemsPrice(), cp.getTotalPriceInCart(), "Total price of multiple items in cart is not matching");
        System.out.println("Assertion is passed: Multiple Items price is matched");
    }
}
